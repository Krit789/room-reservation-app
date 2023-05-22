package net.itkmitl.room.db;

import net.itkmitl.room.libs.jarukrit.ConfigManager;
import net.itkmitl.room.libs.peeranat.FewDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;

public class LaewTaeDB {

    private static FewQuery fq;

    public static FewQuery getDB() throws Exception {
        if (fq == null){
            fq = new FewQuery(FewDB.getConnection(ConfigManager.getConnectionConfig()));
        }

        if (!fq.connection.isValid(0)){
            fq = null;
            fq = new FewQuery(FewDB.getConnection(ConfigManager.getConnectionConfig()));
        }
        return fq;
    }

    public static FewQuery getDBwithoutDB() throws Exception {
        return new FewQuery(FewDB.getConnectionWithoutDB(ConfigManager.getConnectionConfig()));
    }
}
