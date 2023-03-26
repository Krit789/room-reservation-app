package net.itkmitl.room.libs.peeranat;

import net.itkmitl.room.libs.jarukrit.ConfigManager;
import net.itkmitl.room.libs.peeranat.query.FewMySQLBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

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

    public static Connection getConnection(ArrayList<String> CM) {
        if (CM.size() == 4){
            return getConnection(CM.get(0), CM.get(1), CM.get(2), CM.get(3));
        }
        System.out.println("Connection Config length is invalid!");
        return null;
    }

    
}
