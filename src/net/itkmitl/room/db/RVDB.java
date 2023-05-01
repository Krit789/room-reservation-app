package net.itkmitl.room.db;

import net.itkmitl.room.libs.jarukrit.ConfigManager;
import net.itkmitl.room.libs.peeranat.FewDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;

public class RVDB {

    public static FewQuery getDB() {
        return new FewQuery(FewDB.getConnection(new ConfigManager().getConnectionConfig()));
    }

    public static FewQuery getDBwithoutDB() {
        return new FewQuery(FewDB.getConnectionWithoutDB(new ConfigManager().getConnectionConfig()));
    }
}
