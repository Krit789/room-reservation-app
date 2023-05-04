package net.itkmitl.room.portal.components;

import net.itkmitl.room.enums.EnumUserRole;
import net.itkmitl.room.libs.peeranat.util.FewFile;
import net.itkmitl.room.libs.phatsanphon.entity.User;

public class FakeUser {
    public static User getUser() {
        try {
            User fakeUser = new User();
            fakeUser.setId(999);
            fakeUser.setFirstname("Dummy");
            fakeUser.setLastname("User");
            fakeUser.setEmail("dummy@email.com");
            fakeUser.setRole(EnumUserRole.STUDENT);
            fakeUser.setActive(true);
            return fakeUser;
        } catch (Exception e) {

        }
        return null;
    }

    public static User getAdmin() {
        try {
            User fakeUser = new User();
            fakeUser.setId(999);
            fakeUser.setFirstname("Dummy");
            fakeUser.setLastname("User");
            fakeUser.setEmail("dummy@email.com");
            fakeUser.setRole(EnumUserRole.ADMIN);
            fakeUser.setActive(true);
            return fakeUser;
        } catch (Exception e) {

        }
        return null;
    }
}
