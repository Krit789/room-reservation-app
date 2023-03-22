package net.itkmitl.room.libs.peeranat;

import net.itkmitl.room.libs.peeranat.query.FewMySQLBuilder;

import java.sql.Connection;
import java.sql.DriverManager;

public class FewDB {

    private static Connection connection;

    public static Connection getConnection(String server, String table, String username, String password) {
        if (connection != null) return connection;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + server + "/" + table,
                    username,
                    password
            );
        } catch (Exception e) {
            System.out.println("ERROR: Unable to connect to database (" + e.getMessage() + ")");
        }
        return connection;
    }

    
}
