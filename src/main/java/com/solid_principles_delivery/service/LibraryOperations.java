package com.solid_principles_delivery.service;

// Interface Segregation Principle: Separate interfaces for different operations
public interface LibraryOperations {
    boolean borrowItem(String itemId, String userId);
    boolean returnItem(String itemId, String userId);
    boolean reserveItem(String itemId, String userId);
}
