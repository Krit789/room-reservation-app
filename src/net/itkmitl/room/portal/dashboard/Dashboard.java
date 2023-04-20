package net.itkmitl.room.portal.dashboard;

import net.itkmitl.room.portal.Portal;

public class Dashboard implements Portal {
    public static void main(String[] args) {
        Dashboard dashboard = new Dashboard();
        dashboard.start();
    }

    @Override
    public void start() {
        DashboardView view = new DashboardView();
        DashboardController controller = new DashboardController(view);

        controller.start();
    }
}
