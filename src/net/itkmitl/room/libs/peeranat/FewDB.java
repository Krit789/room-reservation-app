package net.itkmitl.room.libs.peeranat;

import net.itkmitl.room.libs.peeranat.query.FewMySQLBuilder;

import java.sql.Connection;
import java.sql.DriverManager;

public class FewDB {

    private static Connection connection;

    public static Connection getConnection(String server, String database, String username, String password) {
        if (connection != null) return connection;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + server + "/" + database,
                    username,
                    password
            );
        } catch (Exception e) {
            System.out.println("ERROR: Unable to connect to database (" + e.getMessage() + ")");
        }
        return connection;
    }

    
}
