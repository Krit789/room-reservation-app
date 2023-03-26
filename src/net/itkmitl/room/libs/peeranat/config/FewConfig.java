package net.itkmitl.room.libs.peeranat.config;

import net.itkmitl.room.libs.peeranat.util.FewFile;
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
        try {
            InputStream inputStream = new FileInputStream(file);
            this.yaml = new Yaml();
            this.data = this.yaml.load(inputStream);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        //If could load default config
        if (this.data == null) {
            this.data = new HashMap<>();
        }
    }

    public void dumpInfo() {
        for (Map.Entry<String, Object> values : data.entrySet()) {
            System.out.println(values.getKey() + ": " + values.getValue());
        }
    }

    public Object getRaw(String key) {
        return this.data.get(key);
    }

    public String asString(String key) {
        if (this.data.get(key) == null) {
            return null;
        }
        try {
            return this.data.get(key).toString();
        } catch (Exception e) {
            throw new RuntimeException(key + " is not a String, the correct data type is " + data.get(key).getClass().getName());
        }
    }

    public String asString(String key, String defaultValue) {
        try {
            String value = this.data.get(key).toString();
            return value != null ? value:defaultValue;
        } catch (Exception e) {
            throw new RuntimeException(key + " is not a String, the correct data type is " + data.get(key).getClass().getName());
        }
    }

    public Double asDouble(String key) {
        if (this.data.get(key) == null) {
            return null;
        }
        try {
            return (Double) this.data.get(key);
        } catch (Exception e) {
            throw new RuntimeException(key + " is not a String, the correct data type is " + data.get(key).getClass().getName());
        }
    }

    public Double asDouble(String key, Double defaultValue) {
        try {
            Double value = (Double) this.data.get(key);
            return value != null ? value:defaultValue;
        } catch (Exception e) {
            throw new RuntimeException(key + " is not a String, the correct data type is " + data.get(key).getClass().getName());
        }
    }

    public Integer asInt(String key) {
        if (this.data.get(key) == null) {
            return null;
        }
        try {
            return (Integer) this.data.get(key);
        } catch (Exception e) {
            throw new RuntimeException(key + " is not a Integer, the correct data type is " + data.get(key).getClass().getName());
        }
    }

    public Integer asInt(String key, Integer defaultValue) {
        try {
            Integer value = (Integer) this.data.get(key);
            return value != null ? value:defaultValue;
        } catch (Exception e) {
            throw new RuntimeException(key + " is not a Integer, the correct data type is " + data.get(key).getClass().getName());
        }
    }

    public Boolean asBoolean(String key) {
        if (this.data.get(key) == null) {
            return null;
        }
        try {
            return (Boolean) this.data.get(key);
        } catch (Exception e) {
            throw new RuntimeException(key + " is not a Boolean, the correct data type is " + data.get(key).getClass().getName());
        }
    }

    public Boolean asBoolean(String key, Boolean defaultValue) {
        try {
            Boolean value = (Boolean) this.data.get(key);
            return value != null ? value:defaultValue;
        } catch (Exception e) {
            throw new RuntimeException(key + " is not a Boolean, the correct data type is " + data.get(key).getClass().getName());
        }
    }

    public Float asFloat(String key) {
        if (this.data.get(key) == null) {
            return null;
        }
        try {
            return (Float) this.data.get(key);
        } catch (Exception e) {
            throw new RuntimeException(key + " is not a Float, the correct data type is " + data.get(key).getClass().getName());
        }
    }

    public Float asFloat(String key, Float defaultValue) {
        try {
            Float value = (Float) this.data.get(key);
            return value != null ? value:defaultValue;
        } catch (Exception e) {
            throw new RuntimeException(key + " is not a Float, the correct data type is " + data.get(key).getClass().getName());
        }
    }

    public <T> List<T> asList(String key, Class<T> clazz) {
        if (this.data.get(key) == null) {
            return null;
        }
        try {
            return (List<T>) this.data.get(key);
        } catch (Exception e) {
            throw new RuntimeException(key + " is not a List<"+clazz.getName()+">, the correct data type is " + data.get(key).getClass().getName());
        }
    }

    public boolean isArray(String key) {
        if (getRaw(key) instanceof String[]) {
            return true;
        }
        return false;
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
            FileWriter writer = new FileWriter(this.file);
            this.yaml.dump(this.data, writer);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        reloadConfig();
        return this;
    }

    public File getFile() {
        return file;
    }
}
