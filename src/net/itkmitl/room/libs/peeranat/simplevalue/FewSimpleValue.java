package net.itkmitl.room.libs.peeranat.simplevalue;

import java.util.List;

public class FewSimpleValue {

    private final Object val;

    public FewSimpleValue(Object val) {
        this.val = val;
    }

    public Object getRaw() {
        return val;
    }

    public String asString() {
        return asString("");
    }

    public String asString(String defaultValue) {
        try {
            String value = this.getRaw().toString();
            return value != null ? value : defaultValue;
        } catch (Exception e) {
            throw new RuntimeException("It's not a String, the correct data type is " + val.getClass().getName());
        }
    }

    public Double asDouble() {
        return asDouble(0.0);
    }

    public Double asDouble(Double defaultValue) {
        try {
            Double value = (Double) getRaw();
            return value != null ? value : defaultValue;
        } catch (Exception e) {
            throw new RuntimeException("It's not a String, the correct data type is " + val.getClass().getName());
        }
    }

    public Integer asInt() {
        return asInt(0);
    }

    public Integer asInt(Integer defaultValue) {
        try {
            Integer value = (Integer) getRaw();
            return value != null ? value : defaultValue;
        } catch (Exception e) {
            throw new RuntimeException("It's not a String, the correct data type is " + val.getClass().getName());
        }
    }

    public Boolean asBoolean() {
        return asBoolean(false);
    }

    public Boolean asBoolean(Boolean defaultValue) {
        try {
            Boolean value = (Boolean) getRaw();
            return value != null ? value : defaultValue;
        } catch (Exception e) {
            throw new RuntimeException("It's not a String, the correct data type is " + val.getClass().getName());
        }
    }

    public Float asFloat() {
        return asFloat(0.0f);
    }

    public Float asFloat(Float defaultValue) {
        try {
            Float value = (Float) getRaw();
            return value != null ? value : defaultValue;
        } catch (Exception e) {
            throw new RuntimeException("It's not a String, the correct data type is " + val.getClass().getName());
        }
    }

    public Long asLong() {
        return asLong((long) 0);
    }

    public Long asLong(Long defaultValue) {
        try {
            Long value = (Long) getRaw();
            return value != null ? value : defaultValue;
        } catch (Exception e) {
            throw new RuntimeException("It's not a String, the correct data type is " + val.getClass().getName());
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> asList(Class<T> clazz) {
        try {
            return (List<T>) val;
        } catch (Exception e) {
            throw new RuntimeException("It's not a List<" + clazz.getName() + ">, the correct data type is " + this.val.getClass().getName());
        }
    }

    public boolean isArray() {
        return getRaw() instanceof String[];
    }

}
