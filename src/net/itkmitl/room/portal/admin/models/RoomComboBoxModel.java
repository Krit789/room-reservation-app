package net.itkmitl.room.portal.admin.models;

import net.itkmitl.room.libs.phatsanphon.entity.Room;

public class RoomComboBoxModel {
    private final Room room;

    public RoomComboBoxModel(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return String.format("%d: %s, FL. %s, %s (%d)", room.getId(), room.getName(), room.getFloor(), room.getBuilding(), room.getCapacity());
    }

    public Room getRoom() {
        return room;
    }
}
