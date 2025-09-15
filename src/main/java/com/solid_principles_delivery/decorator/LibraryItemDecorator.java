package com.solid_principles_delivery.decorator;

import com.solid_principles_delivery.model.LibraryItem;
import lombok.Getter;

@Getter
public abstract class LibraryItemDecorator extends LibraryItem {
    protected LibraryItem decoratedItem;

    public LibraryItemDecorator(LibraryItem item) {
        super(item.getId(), item.getTitle());
        this.decoratedItem = item;
    }

    @Override
    public int getMaxBorrowDays() {
        return decoratedItem.getMaxBorrowDays();
    }
}
