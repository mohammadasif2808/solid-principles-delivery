package com.solid_principles_delivery.model;

import lombok.Getter;

@Getter
public abstract class LibraryItem {
    private final String id;
    private final String title;
    private boolean isAvailable;

    protected LibraryItem(String id, String title) {
        this.id = id;
        this.title = title;
        this.isAvailable = true;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public abstract int getMaxBorrowDays();
}
