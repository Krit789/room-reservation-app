package net.itkmitl.room.libs.peeranat.query;

public class FewMySQLValue {

    private Object val;

    public FewMySQLValue(Object val) {
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

    public Object getRaw() {
        return this.val;
    }

}
