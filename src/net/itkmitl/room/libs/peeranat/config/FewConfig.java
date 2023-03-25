package net.itkmitl.room.libs.peeranat.config;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.*;

public class FewConfig {

    private File file;
    private Yaml yaml;
    private HashMap<String, Object> data;

    public FewConfig(File file) {
        this.file = file;
        this.data = new HashMap<>();
        try {
            InputStream inputStream = new FileInputStream(file);
            this.yaml = new Yaml();
            this.data = this.yaml.load(inputStream);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void dumpInfo() {
        for (Map.Entry<String, Object> values : data.entrySet()) {
            System.out.println(values.getKey() + ": " + values.getValue());
        }
    }

    public String asString(String key) {
        return this.data.get(key).toString();
    }

    public double asDouble(String key) {
        return Double.parseDouble(asString(key));
    }

    public float asFloat(String key) {
        return Float.parseFloat(asString(key));
    }
    public boolean asBoolean(String key) {
        return Boolean.parseBoolean(asString(key));
    }
    public int asInt(String key) {
        return Integer.parseInt(asString(key));
    }

    public FewConfig reloadConfig() {
        try {
            this.data = this.yaml.load(new FileInputStream(this.file));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return this;
    }

    public FewConfig set(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public FewConfig saveConfig() {
        try {
            FileWriter writer = new FileWriter(file);
            yaml.dump(this.data, writer);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        reloadConfig();
        return this;
    }

}
