package net.itkmitl.room.portal.admin.models;

import net.itkmitl.room.libs.peeranat.config.FewConfig;

import java.io.File;

public class PreferenceWindowModel {
    private static File configFile = new File("config.yml");
    private String username, password;
    private String sqlAddress, sqlDBName;
    private int sqlPort = 3306;
    private int timeout;
    private boolean neverTimeout;

    public static File getConfigFile() {
        return configFile;
    }

    public static void setConfigFile(File configFile) {
        PreferenceWindowModel.configFile = configFile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSqlAddress() {
        return sqlAddress;
    }

    public void setSqlAddress(String sqlAddress) {
        this.sqlAddress = sqlAddress;
    }

    public int getSqlPort() {
        return sqlPort;
    }

    public void setSqlPort(int sqlPort) {
        this.sqlPort = sqlPort;
    }

    public String getSqlDBName() {
        return sqlDBName;
    }

    public void setSqlDBName(String sqlDBName) {
        this.sqlDBName = sqlDBName;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isNeverTimeout() {
        return neverTimeout;
    }

    public void setNeverTimeout(boolean neverTimeout) {
        this.neverTimeout = neverTimeout;
    }

    public void writeToConfig() {
        FewConfig config = new FewConfig(configFile);
        config.set("username", getUsername());
        config.set("password", getPassword());
        config.set("database", getSqlDBName());
        config.set("ip", getSqlAddress());
        config.set("port", getSqlPort());
        config.set("timeout_time", getTimeout());
        config.set("never_timeout", isNeverTimeout());
        config.saveConfig();
        System.out.println("Config Saved Successfully!");
    }

    public void loadFromConfig() {
        try {
            FewConfig config = new FewConfig(configFile);
            setUsername(config.getValue("username").asString());
            setPassword(config.getValue("password").asString());
            setSqlDBName(config.getValue("database").asString());
            setSqlAddress(config.getValue("ip").asString());
            setSqlPort(config.getValue("port").asInt());
            setTimeout(config.getValue("timeout_time").asInt());
            setNeverTimeout(config.getValue("never_timeout").asBoolean());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
