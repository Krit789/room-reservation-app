package net.itkmitl.room;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.libs.peeranat.query.FewMySQLValue;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.query.FewSelectMySQL;

import java.sql.SQLException;

public class ApplicationMain {

    public static void main(String[] args) throws SQLException {
        FewSelectMySQL select = new FewSelectMySQL();
        select.table("biography");
        select.limit(2);
        select.select("uid", "uprefix", "ufirstname", "ulastname");

        FewQuery query = RVDB.getDB();
        query.query(select);

        while (query.nextBind()) {
            System.out.println("=========================");
            System.out.println(query.getValue("uid").asInt());
            System.out.println(query.getValue("uprefix").asString());
            System.out.println(query.getValue("ufirstname").asString());
            System.out.println(query.getValue("ulastname").asString());
            System.out.println("=========================");
        }
    }

}
