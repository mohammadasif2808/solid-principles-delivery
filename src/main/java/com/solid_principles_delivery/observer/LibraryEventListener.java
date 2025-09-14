package com.solid_principles_delivery.observer;

// Observer Pattern Interface
public interface LibraryEventListener {
    void onItemBorrowed(String itemId, String userId);
    void onItemReturned(String itemId, String userId);
    void onItemReserved(String itemId, String userId);
}
