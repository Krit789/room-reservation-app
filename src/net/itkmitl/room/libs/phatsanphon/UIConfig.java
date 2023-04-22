package net.itkmitl.room.libs.phatsanphon;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;

public class UIConfig {
    public static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            return;
        } catch (Exception ignored) {
        }

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (Exception ignored) {

        }
    }
}
