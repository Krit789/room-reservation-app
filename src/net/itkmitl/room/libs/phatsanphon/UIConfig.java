package net.itkmitl.room.libs.phatsanphon;

import javax.swing.UIManager;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class UIConfig {
    public static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
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
