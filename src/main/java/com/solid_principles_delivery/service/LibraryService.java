package com.solid_principles_delivery.service;

import com.solid_principles_delivery.observer.LibraryEventListener;
import com.solid_principles_delivery.singleton.LibraryDatabase;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

// Implementation of LibraryOperations demonstrating Observer Pattern and DIP
public class LibraryService implements LibraryOperations {
    private final LibraryDatabase database;
    @Getter
    private final List<LibraryEventListener> listeners;

    public LibraryService() {
        this.database = LibraryDatabase.getInstance();
        this.listeners = new ArrayList<>();
    }

    public void addListener(LibraryEventListener listener) {
        listeners.add(listener);
    }

    public void removeListener(LibraryEventListener listener) {
        listeners.remove(listener);
    }

    @Override
    public boolean borrowItem(String itemId, String userId) {
        if (database.isItemAvailable(itemId)) {
            database.getItem(itemId).setAvailable(false);
            notifyItemBorrowed(itemId, userId);
            return true;
        }
        return false;
    }

    @Override
    public boolean returnItem(String itemId, String userId) {
        database.getItem(itemId).setAvailable(true);
        notifyItemReturned(itemId, userId);
        return true;
    }

    @Override
    public boolean reserveItem(String itemId, String userId) {
        if (database.isItemAvailable(itemId)) {
            notifyItemReserved(itemId, userId);
            return true;
        }
        return false;
    }

    private void notifyItemBorrowed(String itemId, String userId) {
        listeners.forEach(listener -> listener.onItemBorrowed(itemId, userId));
    }

    private void notifyItemReturned(String itemId, String userId) {
        listeners.forEach(listener -> listener.onItemReturned(itemId, userId));
    }

    private void notifyItemReserved(String itemId, String userId) {
        listeners.forEach(listener -> listener.onItemReserved(itemId, userId));
    }
}
