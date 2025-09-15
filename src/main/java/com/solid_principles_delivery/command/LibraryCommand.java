package com.solid_principles_delivery.command;

public interface LibraryCommand {
    boolean execute();
    boolean undo();
}
