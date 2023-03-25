package net.itkmitl.room;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.libs.peeranat.query.FewInsertMySQL;
import net.itkmitl.room.libs.peeranat.query.FewMySQLValue;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.query.FewSelectMySQL;

import java.sql.SQLException;

public class ApplicationMain {

    public static void main(String[] args) throws SQLException {
        FewQuery query = RVDB.getDB();
        FewInsertMySQL insertMySQL = new FewInsertMySQL();
        insertMySQL.insert("key", 0);
        insertMySQL.insert("p_name", "Hello world");
        query.query(insertMySQL);
    }

}
