package com.solid_principles_delivery.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Book extends LibraryItem {
    private final String author;
    private final String isbn;
    private final int pages;
    private final String genre;

    @Builder
    public Book(String id, String title, String author, String isbn, int pages, String genre) {
        super(id, title);
        this.author = author;
        this.isbn = isbn;
        this.pages = pages;
        this.genre = genre;
    }

    @Override
    public int getMaxBorrowDays() {
        return 14; // Standard borrowing period for books
    }
}
