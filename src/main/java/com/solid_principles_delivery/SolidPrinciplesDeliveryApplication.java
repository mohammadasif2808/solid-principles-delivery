package com.solid_principles_delivery;

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
			// Demonstrate Factory Pattern
			LibraryItem book = LibraryItemFactory.createBook(
				"B001",
				"Design Patterns",
				"Gang of Four",
				"123-456-789",
				395,
				"Computer Science"
			);

			// Demonstrate Singleton Pattern
			LibraryDatabase.getInstance().addItem(book);

			// Demonstrate Observer Pattern
			LibraryService libraryService = new LibraryService();

			// Create an anonymous implementation of LibraryEventListener
			libraryService.addListener(new LibraryEventListener() {
				@Override
				public void onItemBorrowed(String itemId, String userId) {
					System.out.println("Event: Item " + itemId + " borrowed by user " + userId);
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

			// Demonstrate the complete flow
			System.out.println("\nDemonstrating Library Management System:");
			System.out.println("----------------------------------------");

			String userId = "U001";
			String bookId = "B001";

			// Borrow the book
			libraryService.borrowItem(bookId, userId);

			// Try to reserve (should fail as it's borrowed)
			boolean reserveResult = libraryService.reserveItem(bookId, "U002");
			System.out.println("Reserve attempt result: " + reserveResult);

			// Return the book
			libraryService.returnItem(bookId, userId);

			// Now reservation should succeed
			reserveResult = libraryService.reserveItem(bookId, "U002");
			System.out.println("Reserve attempt result after return: " + reserveResult);
		};
	}
}
