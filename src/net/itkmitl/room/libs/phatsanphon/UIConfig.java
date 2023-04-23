package net.itkmitl.room.libs.phatsanphon;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;

public class UIConfig {
    public static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            System.setProperty( "apple.laf.useScreenMenuBar", "true" );
            System.setProperty( "apple.awt.application.name", "Laew Tae Hong Management" );
            System.setProperty( "apple.awt.application.appearance", "system" );
            return;
        } catch (Exception ignored) {
        }

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (Exception ignored) {

        }
    }
}
