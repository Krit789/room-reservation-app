package net.itkmitl.room.libs.phatsanphon.model;

import net.itkmitl.room.enums.*;
import net.itkmitl.room.libs.peeranat.query.FewQuery;

public class Room {

    private int id;
    private String name;
    private int capacity;
    private String floor;
    private String building;
    private EnumRoomState state;

    public Room(FewQuery query) {
        processObject(query);
    }

    public Room() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public EnumRoomState getState() {
        return state;
    }

    public void setState(EnumRoomState state) {
        this.state = state;
    }

    private void processObject(FewQuery query) {
        this.setId(query.getValue("id").asInt());
        this.setName(query.getValue("name").asString());
        this.setCapacity(query.getValue("capacity").asInt());
        this.setFloor(query.getValue("floor").asString());
        this.setBuilding(query.getValue("building").asString());
        this.setState(EnumRoomState.valueOf(query.getValue("state").asString()));
    }
}
