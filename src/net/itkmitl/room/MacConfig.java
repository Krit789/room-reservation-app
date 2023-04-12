package net.itkmitl.room;

import java.awt.*;

public class MacConfig {
    public static void menuBar() {
        menuBar("Laew Tae Hong");
    }

    public static void menuBar(String appName) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.application.name", appName);
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", appName);
        }
    }
}
