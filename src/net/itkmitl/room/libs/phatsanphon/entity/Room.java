package net.itkmitl.room.libs.phatsanphon.entity;

import net.itkmitl.room.enums.EnumRoomState;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.date.DateTime;

public class Room extends Entity {

    private int id;
    private String name;
    private int capacity;
    private String floor;
    private String building;

    private DateTime openTime;
    private DateTime closeTime;

    private EnumRoomState state;

    public Room(FewQuery query) throws Exception {
        super(query);
    }

    public Room() throws Exception {

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

    public DateTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(DateTime openTime) {
        this.openTime = openTime;
    }

    public void setOpenTime(long openTime) {
        this.setOpenTime(new DateTime(openTime));
    }

    public DateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(DateTime closeTime) {
        this.closeTime = closeTime;
    }

    public void setCloseTime(long closeTime) {
        this.setCloseTime(new DateTime(closeTime));
    }

    public EnumRoomState getState() {
        return state;
    }

    public void setState(EnumRoomState state) {
        this.state = state;
    }

    @Override
    public void processQuery(FewQuery query) {
        this.setId(query.getValue("id").asInt());
        this.setName(query.getValue("name").asString());
        this.setCapacity(query.getValue("capacity").asInt());
        this.setFloor(query.getValue("floor").asString());
        this.setBuilding(query.getValue("building").asString());
        this.setOpenTime(query.getValue("open_time").asLong());
        this.setCloseTime(query.getValue("close_time").asLong());
        this.setState(EnumRoomState.valueOf(query.getValue("state").asString()));
    }
}
