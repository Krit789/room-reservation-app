package net.itkmitl.room.libs.peeranat.query;

public enum FewMySQLCompare {

    EQUAL("="),
    NOT_EQUAL("!="),
    LIKE("LIKE"),
    MORE_THAN(">"),
    MORE_THAN_EQUAL(">="),
    LESS_THAN("<"),
    LESS_THAN_EQUAL("<="),
    IS_NULL("IS NULL"),
    IS_NOT_NULL("IS NOT NULL"),
    ;

    private final String value;

    FewMySQLCompare(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }
}
