package com.solid_principles_delivery.singleton;

import java.util.HashMap;
import java.util.Map;
import com.solid_principles_delivery.model.LibraryItem;

// Singleton Pattern Implementation
public class LibraryDatabase {
    private static LibraryDatabase instance;
    private Map<String, LibraryItem> items;

    private LibraryDatabase() {
        items = new HashMap<>();
    }

    public static synchronized LibraryDatabase getInstance() {
        if (instance == null) {
            instance = new LibraryDatabase();
        }
        return instance;
    }

    public void addItem(LibraryItem item) {
        items.put(item.getId(), item);
    }

    public LibraryItem getItem(String id) {
        return items.get(id);
    }

    public boolean isItemAvailable(String id) {
        LibraryItem item = items.get(id);
        return item != null && item.isAvailable();
    }
}
