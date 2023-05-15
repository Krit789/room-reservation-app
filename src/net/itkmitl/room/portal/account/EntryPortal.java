package net.itkmitl.room.portal.account;

import net.itkmitl.room.portal.Portal;

import javax.swing.*;

public class EntryPortal extends Portal {
    private final EntryController controller;

    public EntryPortal() {
        super();
        EntryView view = new EntryView();
        this.controller = new EntryController(view);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                EntryPortal entryPortal = new EntryPortal();
                entryPortal.run();
            }
        });
    }

    @Override
    public void run() {
        controller.start();
    }
}
