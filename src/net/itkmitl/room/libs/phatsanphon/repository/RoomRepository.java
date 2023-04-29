package net.itkmitl.room.libs.phatsanphon.repository;

import net.itkmitl.room.libs.peeranat.query.*;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.libs.phatsanphon.entity.User;

import java.util.ArrayList;

public class RoomRepository extends Repository<Room> {
    public RoomRepository(final FewQuery query) {
        super(Room.class, query);
    }

    public ArrayList<Room> getRooms() {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*").table("room");

        return this.maps(this.getQuery().query(select));
    }

    public ArrayList<Room> getRooms(int limit) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*").table("room");
        select.limit(limit);

        return this.maps(this.getQuery().query(select));
    }

    public ArrayList<Room> getRooms(String building, String floor) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("building", building)
                .where("floor", floor)
                .select("*")
                .table("room");

        return this.maps(this.getQuery().query(select));
    }

    public ArrayList<Room> getRoomsBy(int prop, String search) {
        String query = null;
        switch (prop) {
            case 0: // ID
                query = String.format("SELECT * FROM `room` WHERE id='%s'", search);
                break;
            case 1: // Name
                query = String.format("SELECT * FROM `room` WHERE name LIKE '%%%s%%'", search);
                break;
            case 2: // Building
                query = String.format("SELECT * FROM `room` WHERE building LIKE '%%%s%%'", search);
                break;
            case 3: // State
                query = String.format("SELECT * FROM `room` WHERE state LIKE '%%%s%%'", search);
                break;
        }
        return this.maps(this.getQuery().unsafeQuery(query));
    }

    public ArrayList<Room> getRoomsByBuilding(String building) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("building", building)
                .select("*")
                .table("room");

        return this.maps(this.getQuery().query(select));
    }

    public ArrayList<Room> getRoomsByFloor(String floor) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("floor", floor)
                .select("*")
                .table("room");

        return this.maps(this.getQuery().query(select));
    }

    public Room getRoomById(int id) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*")
                .where("id", id)
                .limit(1)
                .table("room");

        return this.map(this.getQuery().query(select));
    }

    public void createRoom(Room room) {
        FewInsertMySQL insert = new FewInsertMySQL();

        insert.insert("name", room.getName())
                .insert("building", room.getBuilding())
                .insert("capacity", room.getCapacity())
                .insert("close_time", room.getCloseTime().toString())
                .insert("open_time", room.getOpenTime().toString())
                .insert("floor", room.getFloor())
                .insert("state", room.getState())
                .table("room");

        this.getQuery().query(insert);
    }

    public void deleteRoomById(int id) {
        FewDeleteMySQL delete = new FewDeleteMySQL();

        delete.table("room");
        delete.where("id", id);

        this.getQuery().query(delete);
    }

    public void updateRoom(Room room) {
        FewUpdateMySQL update = new FewUpdateMySQL();

        if (room.getId() == 0) {
            throw new RuntimeException("Please provided an id to update user");
        }

        update.where("id", room.getId())
                .set("name", room.getName())
                .set("building", room.getBuilding())
                .set("capacity", room.getCapacity())
                .set("floor", room.getFloor())
                .set("close_time", room.getCloseTime().toString())
                .set("open_time", room.getOpenTime().toString())
                .set("state", room.getState())
                .table("room");
        this.getQuery().query(update);
    }
}
