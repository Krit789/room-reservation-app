package net.itkmitl.room.enums;

public enum EnumUserRole {

    STUDENT(1, "นักศึกษา"), // Can reserve room and can do all what user think
    STAFF(10, "เจ้าหน้าที่"), // Can check log who reserve
    ADMIN(99, "บร๊ะเจ้า"); // Can access config and back office

    private int level;
    private String name;

    EnumUserRole(int level, String name) {
        this.level = level;
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public static EnumUserRole searchRoleByLevel(int level) {
        for (EnumUserRole role : EnumUserRole.values()) {
            if (role.getLevel() == level) {
                return role;
            }
        }
        //Default student
        return STUDENT;
    }
}
