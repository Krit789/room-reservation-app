package net.itkmitl.room.libs.peeranat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.enums.EnumDBSchema;

public class FewDB {

    private static Connection connection;

    public static Connection getConnection(String server, String database, String username, String password) throws Exception {
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
            throw e;
        }
        return connection;
    }

    public static Connection getConnection(ArrayList<String> CM) throws Exception {
        if (CM.size() == 4){
            return getConnection(CM.get(0), CM.get(1), CM.get(2), CM.get(3));
        }
        System.out.println("Connection Config length is invalid!");
        return null;
    }

    public static Connection getConnectionWithoutDB(String server, String username, String password) throws Exception {
        if (connection != null) return connection;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + server,
                    username,
                    password
            );
        } catch (Exception e) {
            System.out.println("ERROR: Unable to connect to database (" + e.getMessage() + ")");
            throw e;
        }
        return connection;
    }

    public static Connection getConnectionWithoutDB(ArrayList<String> CM) throws Exception {
        if (CM.size() == 4){
            return getConnectionWithoutDB(CM.get(0), CM.get(2), CM.get(3));
        }
        System.out.println("Connection Config length is invalid!");
        return null;
    }


    public static void createTable(EnumDBSchema schema) throws Exception{
        LaewTaeDB.getDB().unsafeQuery(schema.getRaw());
    }

    public static void createDatabase(String databaseName) throws Exception {
        LaewTaeDB.getDBwithoutDB().unsafeQuery(String.format("CREATE DATABASE IF NOT EXISTS %s;", databaseName));
    }
}
