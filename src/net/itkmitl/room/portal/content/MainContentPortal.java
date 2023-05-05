package net.itkmitl.room.portal.content;

import net.itkmitl.room.libs.store.AppStore;
import net.itkmitl.room.portal.Portal;
import net.itkmitl.room.portal.components.FakeUser;

public class MainContentPortal extends Portal {
    private final MainContentController controller;
    private AppStore store = AppStore.getAppStore();

    public MainContentPortal() {
        super();

        store.dispatch("user", FakeUser.getUser());

        MainContentView view = new MainContentView();
        this.controller = new MainContentController(view);
    }

    public static void main(String[] args) {
        MainContentPortal mainContent = new MainContentPortal();
        mainContent.run();
    }

    @Override
    public void run() {
        controller.start();
    }
}
