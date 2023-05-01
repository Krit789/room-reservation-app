package net.itkmitl.room.libs.phatsanphon.repository;

import java.util.ArrayList;

import net.itkmitl.room.libs.peeranat.query.FewDeleteMySQL;
import net.itkmitl.room.libs.peeranat.query.FewInsertMySQL;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.query.FewSelectMySQL;
import net.itkmitl.room.libs.peeranat.query.FewUpdateMySQL;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.libs.phatsanphon.repository.enums.RoomQuery;

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

    public ArrayList<Room> getRooms(RoomQuery queryBy, String query) {
        if (queryBy == RoomQuery.ID) {
            Room data = this.getRoomById(Integer.parseInt(query));

            ArrayList<Room> objects = new ArrayList<>();
            objects.add(data);

            return objects;
        } else if (queryBy == RoomQuery.NAME) {
            return this.getRoomsByName(query);
        } else if (queryBy == RoomQuery.BUILDING) {
            return this.getRoomsByBuilding(query);
        } else if (queryBy == RoomQuery.STATE) {
            return this.getRoomsByState(query);
        }

        return null;
    }

    public ArrayList<Room> getRoomsByBuilding(String building) {
        String query = String.format(
                "SELECT * FROM `room` WHERE building LIKE '%%%s%%'", building
        );

        return this.maps(this.getQuery().unsafeQuery(query));
    }

    public ArrayList<Room> getRoomsByName(String name) {
        String query = String.format(
                String.format("SELECT * FROM `room` WHERE name LIKE '%%%s%%'", name)
        );

        return this.maps(this.getQuery().unsafeQuery(query));
    }

    public ArrayList<Room> getRoomsByState(String state) {
        String query = String.format(
                String.format("SELECT * FROM `room` WHERE state LIKE '%%%s%%'", state)
        );

        return this.maps(this.getQuery().unsafeQuery(query));
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
