package net.itkmitl.room.db;

import net.itkmitl.room.libs.jarukrit.ConfigManager;
import net.itkmitl.room.libs.peeranat.FewDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;

public class LaewTaeDB {

    public static FewQuery getDB() throws Exception {
        return new FewQuery(FewDB.getConnection(ConfigManager.getConnectionConfig()));
    }

    public static FewQuery getDBwithoutDB() throws Exception {
        return new FewQuery(FewDB.getConnectionWithoutDB(ConfigManager.getConnectionConfig()));
    }
}
