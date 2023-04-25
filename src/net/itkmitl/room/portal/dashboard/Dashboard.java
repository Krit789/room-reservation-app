package net.itkmitl.room.portal.dashboard;

import net.itkmitl.room.portal.Portal;

public class Dashboard extends Portal {
    private final DashboardController controller;

    public Dashboard() {
        super();

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
