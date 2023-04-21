package net.itkmitl.room.portal.dashboard;

import com.formdev.flatlaf.FlatIntelliJLaf;
import net.itkmitl.room.MacConfig;
import net.itkmitl.room.portal.Portal;

import javax.swing.*;

public class Dashboard implements Portal {
    public static void main(String[] args) {
        try {
            MacConfig.menuBar("Laew Tae Hong Management");
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (Exception ignored) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            }
        } catch (Exception ignored) {}
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
