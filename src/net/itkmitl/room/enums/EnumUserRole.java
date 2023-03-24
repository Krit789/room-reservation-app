package net.itkmitl.room.enums;

public enum EnumUserRole {

    STUDENT("นักศึกษา"), // Can reserve room and can do all what user think
    STAFF("เจ้าหน้า"), // Can check log who reserve
    ADMIN("บร๊ะเจ้า"); // Can access config and back office

    private String name;

    EnumUserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
