package net.itkmitl.room.portal.dashboard;

import net.itkmitl.room.libs.store.AppStore;
import net.itkmitl.room.portal.Portal;
import net.itkmitl.room.portal.components.FakeUser;

public class Dashboard extends Portal {
    private final DashboardController controller;
    private AppStore store = AppStore.getAppStore();

    public Dashboard() {
        super();

        store.dispatch("user", FakeUser.getUser());

        DashboardView view = new DashboardView();
        this.controller = new DashboardController(view);
    }

    public static void main(String[] args) {
        Dashboard dashboard = new Dashboard();
        dashboard.run();
    }

    @Override
    public void run() {
        controller.start();
    }
}
