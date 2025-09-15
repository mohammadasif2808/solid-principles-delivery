package com.solid_principles_delivery.decorator;

import com.solid_principles_delivery.model.LibraryItem;

public class ReferenceOnlyDecorator extends LibraryItemDecorator {
    public ReferenceOnlyDecorator(LibraryItem item) {
        super(item);
    }

    @Override
    public int getMaxBorrowDays() {
        return 0; // Reference only items cannot be borrowed
    }

    @Override
    public boolean isAvailable() {
        return true; // Always available for in-library use
    }

    public String getUsageRestriction() {
        return "This item can only be used within the library premises";
    }
}
