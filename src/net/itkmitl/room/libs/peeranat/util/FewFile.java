package net.itkmitl.room.libs.peeranat.util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

public class FewFile {

    public static File getFile(String path) {
        try {
            return new File(ClassLoader.getSystemResource(path).toURI());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }


    public static Font getFont(String path) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, getResource(path).openStream());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }

    public static ImageIcon getImage(String path) {
        return new ImageIcon(getResource(path));
    }

    public static URL getResource(String path) {
        return ClassLoader.getSystemResource(path);
    }

    public static boolean isExist(String path) {
        return getResource(path) != null;
    }

}
