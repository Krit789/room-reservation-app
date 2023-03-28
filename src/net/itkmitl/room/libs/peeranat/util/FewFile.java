package net.itkmitl.room.libs.peeranat.util;

import java.awt.*;
import java.io.File;
import java.net.*;

import javax.swing.*;

public class FewFile {

    public static File getFile(String path) {
        try {
            return new File(ClassLoader.getSystemResource(path).toURI());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }

    public static Image getImage(String path) {
        return new ImageIcon("resource/" + path).getImage();
    }

    public static URL getResource(String path) {
        return ClassLoader.getSystemResource(path);
    }

    public static boolean isExist(String path) {
        return getResource(path) != null;
    }

}
