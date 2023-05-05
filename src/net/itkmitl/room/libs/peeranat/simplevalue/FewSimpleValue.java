package net.itkmitl.room.libs.peeranat.simplevalue;

public class FewSimpleValue {

    private Object val;

    public FewSimpleValue(Object val) {
        this.val = val;
    }

    public String asString() {
        return this.val.toString();
    }

    public int asInt() {
        return Integer.parseInt(asString());
    }

    public double asDouble() {
        return Double.parseDouble(asString());
    }

    public boolean asBoolean() {
        return Boolean.parseBoolean(asString());
    }

    public float asFloat() {
        return Float.parseFloat(asString());
    }

    public long asLong() {
        return Long.parseLong(asString());
    }

    public Object getRaw() {
        return this.val;
    }

}
