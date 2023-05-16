package net.itkmitl.room.portal.content;

import net.itkmitl.room.libs.store.AppStore;
import net.itkmitl.room.portal.Portal;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.admin.controllers.AuthController;
import net.itkmitl.room.portal.components.FakeUser;

import javax.swing.*;

public class MainContentPortal extends Portal {
    private final MainContentController controller;
    private AppStore store = AppStore.getAppStore();

    public MainContentPortal() {
        super();

        if(store.select("user") == null) {
            store.dispatch("user", FakeUser.getAdmin());
        }else{
            store.dispatch("user", store.select("user"));
        }

        MainContentView view = new MainContentView();
        this.controller = new MainContentController(view);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainContentPortal mainContent = new MainContentPortal();
                mainContent.run();
            }
        });
    }

    @Override
    public void run() {
        controller.start();
    }
}
