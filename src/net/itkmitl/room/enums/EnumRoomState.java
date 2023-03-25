package net.itkmitl.room.enums;

public enum EnumRoomState {

    AVAILABLE("ห้องว่าง"),
    UNAVAILABLE("ห้องไม่ว่าง / ห้องเต็ม"),
    MAINTENANCE("ปรับปรุง"),
    ;

    private String name;
    EnumRoomState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
