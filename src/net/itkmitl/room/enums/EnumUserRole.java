package net.itkmitl.room.enums;

public enum EnumUserRole {

    STUDENT(1, "นักศึกษา"), // Can reserve room and do all things that user can think of
    STAFF(10, "เจ้าหน้าที่"), // Can check reserver logs
    ADMIN(99, "บร๊ะเจ้า"); // Can access config and back office

    private final int level;
    private final String name;

    EnumUserRole(int level, String name) {
        this.level = level;
        this.name = name;
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

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
