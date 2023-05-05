package net.itkmitl.room.portal.components.entity;

import net.itkmitl.room.libs.phatsanphon.entity.Room;

import java.util.ArrayList;
import java.util.HashMap;

public class Floor {
    private HashMap<String, Room> rooms = new HashMap<String, Room>();
    private String name;
    public Floor(){
        this("");
    }
    public Floor(String name){
        this.name = name;
    }
    public void addRoom(Room room){
        this.rooms.put(room.getName(), room);
    }
    public HashMap<String, Room> getRooms(){
        return this.rooms;
    }
    public Room getRoomFromName(String name){
        return rooms.get(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
