package com.solid_principles_delivery.command;

import com.solid_principles_delivery.model.LibraryItem;
import com.solid_principles_delivery.service.LibraryService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BorrowItemCommand implements LibraryCommand {
    private final LibraryService libraryService;
    private final String itemId;
    private final String userId;
    private boolean executed = false;

    @Override
    public boolean execute() {
        if (!executed && libraryService.borrowItem(itemId, userId)) {
            executed = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean undo() {
        if (executed && libraryService.returnItem(itemId, userId)) {
            executed = false;
            return true;
        }
        return false;
    }
}
