package net.itkmitl.room.libs.jarukrit;

import net.itkmitl.room.libs.peeranat.config.FewConfig;

import java.io.File;
import java.util.ArrayList;

public class ConfigManager {
	
    public static void saveConnection(String database, String ip, int port, String username, String password) {
        FewConfig config = new FewConfig(new File("test.yml"));
        config.set("database", database);
        config.set("ip", ip);
        config.set("port", port);
        config.set("username", username);
        config.set("password", password);
        config.saveConfig();
    }

    public static void saveConnection(String database, String ip, String port, String username, String password) {
        saveConnection(database, ip, Integer.parseInt(port), username, password);
    }

    public static void saveConnection(String database, String ip, String username, String password) {
        saveConnection(database, ip, "3306", username, password);
    }

    public static ArrayList<String> getConnectionConfig() {
    	FewConfig config = new FewConfig(new File("test.yml"));
        ArrayList<String> myConnection = new ArrayList<>();
        myConnection.add(config.asString("ip") + ":" + config.asString("port"));
        myConnection.add(config.asString("database"));
        myConnection.add(config.asString("username"));
        myConnection.add(config.asString("password"));
        return myConnection;
    }
    
}
