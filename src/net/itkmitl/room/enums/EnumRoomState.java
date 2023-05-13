package net.itkmitl.room.enums;

public enum EnumRoomState {

    AVAILABLE("Available"),
    UNAVAILABLE("Unavailable"),
    MAINTENANCE("Maintenance"),
    ;

    private String name;
    EnumRoomState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
