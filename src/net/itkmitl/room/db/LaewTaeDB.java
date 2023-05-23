package net.itkmitl.room.db;

import net.itkmitl.room.libs.jarukrit.ConfigManager;
import net.itkmitl.room.libs.peeranat.FewDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;

public class LaewTaeDB {

    private static FewQuery fq;

    public synchronized static FewQuery getDB() throws Exception {
        if (fq == null) {
            fq = new FewQuery(FewDB.getConnection(ConfigManager.getConnectionConfig()));
        }
        if (!fq.connection.isValid(0) || fq.connection.isClosed()) {
            fq.connection.close();
            System.out.println("Invalid/Closed Connection! Reconnecting...");
            fq = null;
            fq = new FewQuery(FewDB.getConnection(ConfigManager.getConnectionConfig()));
        }
        return fq;
    }

    public synchronized static FewQuery getDBwithoutDB() throws Exception {
        return new FewQuery(FewDB.getConnectionWithoutDB(ConfigManager.getConnectionConfig()));
    }
}
