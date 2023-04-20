package net.itkmitl.room.portal;

import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.dashboard.DashboardView;

public abstract class Controller {
    protected abstract void start();

    protected abstract void initialize();

    protected abstract void initializeListener();

    protected void changeFrame(View current, Portal destination) {
        current.dispose();
        destination.start();
    }
}
