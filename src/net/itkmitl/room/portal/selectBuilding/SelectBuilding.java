package net.itkmitl.room.portal.selectBuilding;

import net.itkmitl.room.portal.Portal;
import net.itkmitl.room.portal.dashboard.Dashboard;

public class SelectBuilding extends Portal {
    private final SelectBuildingController controller;

    public SelectBuilding() {
        super();

        SelectBuildingView view = new SelectBuildingView();
        this.controller = new SelectBuildingController(view);
    }

    public static void main(String[] args) {
        SelectBuilding selectBuilding = new SelectBuilding();
        selectBuilding.run();
    }

    @Override
    public void run() {
        controller.start();
    }
}
