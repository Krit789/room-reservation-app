package net.itkmitl.room;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.entity.*;
import net.itkmitl.room.libs.phatsanphon.repository.*;
import java.util.ArrayList;

public class ApplicationMain {

    public static void main(String[] args) {
        FewQuery db = RVDB.getDB();
        UserRepository userRepository = new UserRepository(db);

        User user = userRepository.getUserById(2);
        System.out.println(user.getFirstname() + " " + user.getLastname() + " " + user.getCreatedOn().toString());
    }
}
