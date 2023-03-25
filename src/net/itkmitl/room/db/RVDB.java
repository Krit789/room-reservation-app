package net.itkmitl.room.db;

import net.itkmitl.room.libs.peeranat.FewDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;

public class RVDB {

    public static FewQuery getDB() {
        return new FewQuery(FewDB.getConnection("49.228.131.109:3357", "room_reservation","test_user","Pu57HDrrXgvVLn"));
    }
}
