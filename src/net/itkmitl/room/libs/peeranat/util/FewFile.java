package net.itkmitl.room.libs.peeranat.util;

import java.net.*;

public class FewFile {

    public static URL getResource(String path) {
        ClassLoader cl = FewFile.class.getClassLoader();
        return cl.getResource(path);
    }

    public static boolean isExist(String path) {
        return getResource(path) != null;
    }

}
