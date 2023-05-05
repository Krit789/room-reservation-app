package net.itkmitl.room.libs.peeranat.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class FewPassword {

    public static String getSalt(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public static boolean checkPassword(String password, String salt) {
        return BCrypt.verifyer().verify(password.toCharArray(), salt).verified;
    }
    
    public static String getText(char[] password) {
    	return String.valueOf(password);
    }
}
