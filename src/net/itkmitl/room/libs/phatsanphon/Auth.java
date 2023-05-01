package net.itkmitl.room.libs.phatsanphon;

import net.itkmitl.room.libs.peeranat.util.FewPassword;

public class Auth {
    public static boolean compareHash(String password, String passwordHash) {
        String salt = FewPassword.getSalt(password);
        return FewPassword.checkPassword(passwordHash, salt);
    }
}
