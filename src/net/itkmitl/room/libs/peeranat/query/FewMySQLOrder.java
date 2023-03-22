package net.itkmitl.room.libs.peeranat.query;

public enum FewMySQLOrder {

    ASC("ASC"),
    DESC("DESC"),
    ;

    FewMySQLOrder(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return this.value;
    }

}
