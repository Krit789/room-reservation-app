package net.itkmitl.room.libs.peeranat.query;

public enum FewMySQLOperator {

    AND("AND"),
    OR("OR"),
    ;

    private final String value;

    FewMySQLOperator(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
