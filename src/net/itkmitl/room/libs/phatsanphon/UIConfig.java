package net.itkmitl.room.libs.phatsanphon;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;

public class UIConfig {
    public static void setLookAndFeel() {
        setLookAndFeel("Laew Tae Hong");
    }

    public static void setLookAndFeel(String text) {
        try {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.application.name", text);
            System.setProperty("apple.awt.application.appearance", "system");
            UIManager.setLookAndFeel(new FlatMacLightLaf());
            return;
        } catch (Exception ignored) {
        }

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (Exception ignored) {

        }
    }
}
