package com.solid_principles_delivery.factory;

import com.solid_principles_delivery.model.Book;
import com.solid_principles_delivery.model.LibraryItem;

// Factory Pattern Implementation
public class LibraryItemFactory {
    public static LibraryItem createBook(String id, String title, String author, String isbn, int pages, String genre) {
        return Book.builder()
                .author(author)
                .title(title)
                .id(id)
                .isbn(isbn)
                .pages(pages)
                .genre(genre)
                .build();
    }
}
