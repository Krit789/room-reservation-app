package net.itkmitl.room.portal.selectBuilding;

import net.itkmitl.room.portal.Controller;
import net.itkmitl.room.portal.dashboard.DashboardView;

public class SelectBuildingController extends Controller {
    private final SelectBuildingView view;

    public SelectBuildingController(SelectBuildingView view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.initialize();
    }

    @Override
    public void initialize() {
        this.initializeListener();
    }

    @Override
    public void initializeListener() {
    }
}
