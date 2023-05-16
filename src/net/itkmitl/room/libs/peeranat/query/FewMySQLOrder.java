package net.itkmitl.room.libs.peeranat.query;

public enum FewMySQLOrder {

    ASC("ASC"),
    DESC("DESC"),
    ;

    private final String value;

    FewMySQLOrder(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
