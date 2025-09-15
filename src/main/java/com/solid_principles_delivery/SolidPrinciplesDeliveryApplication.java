package com.solid_principles_delivery;

import com.solid_principles_delivery.command.BorrowItemCommand;
import com.solid_principles_delivery.command.LibraryCommand;
import com.solid_principles_delivery.decorator.ReferenceOnlyDecorator;
import com.solid_principles_delivery.factory.LibraryItemFactory;
import com.solid_principles_delivery.model.LibraryItem;
import com.solid_principles_delivery.observer.LibraryEventListener;
import com.solid_principles_delivery.service.LibraryService;
import com.solid_principles_delivery.singleton.LibraryDatabase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SolidPrinciplesDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolidPrinciplesDeliveryApplication.class, args);
    }

    @Bean
    public CommandLineRunner demonstratePatterns() {
        return args -> {
            System.out.println("\nDemonstrating Library Management System:");
            System.out.println("----------------------------------------");

            // Create LibraryService
            LibraryService libraryService = new LibraryService();

            // Create and set up event listener
            setupEventListener(libraryService);

            // Demonstrate Factory Pattern with regular book
            LibraryItem regularBook = LibraryItemFactory.createBook(
                "B001",
                "Design Patterns",
                "Gang of Four",
                "123-456-789",
                395,
                "Computer Science"
            );

            // Demonstrate Decorator Pattern - Create a reference-only version of the book
            LibraryItem referenceBook = new ReferenceOnlyDecorator(
                LibraryItemFactory.createBook(
                    "B002",
                    "Clean Code",
                    "Robert C. Martin",
                    "987-654-321",
                    464,
                    "Software Engineering"
                )
            );

            // Add books to library database (Singleton)
            LibraryDatabase.getInstance().addItem(regularBook);
            LibraryDatabase.getInstance().addItem(referenceBook);

            // Demonstrate Command Pattern
            String userId = "U001";

            // Create and execute borrow command for regular book
            System.out.println("\nTesting Regular Book:");
            LibraryCommand borrowCommand = new BorrowItemCommand(libraryService, "B001", userId);
            libraryService.executeCommand(borrowCommand);

            // Try to borrow reference book (should fail due to decorator)
            System.out.println("\nTesting Reference Book:");
            LibraryCommand borrowReferenceCommand = new BorrowItemCommand(libraryService, "B002", userId);
            libraryService.executeCommand(borrowReferenceCommand);

            // Demonstrate command undo
            System.out.println("\nDemonstrating Undo Operation:");
            libraryService.undoLastCommand();
        };
    }

    private void setupEventListener(LibraryService libraryService) {
        libraryService.addListener(new LibraryEventListener() {
            @Override
            public void onItemBorrowed(String itemId, String userId) {
                System.out.println("Event: Item " + itemId + " borrowed by user " + userId);
                if (itemId.equals("B002")) {
                    System.out.println("Warning: This is a reference-only item!");
                }
            }

            @Override
            public void onItemReturned(String itemId, String userId) {
                System.out.println("Event: Item " + itemId + " returned by user " + userId);
            }

            @Override
            public void onItemReserved(String itemId, String userId) {
                System.out.println("Event: Item " + itemId + " reserved by user " + userId);
            }
        });
    }
}
