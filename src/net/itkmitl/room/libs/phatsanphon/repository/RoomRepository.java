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

    public ArrayList<Room> getRooms() throws Exception {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*").table("room");

        return this.maps(this.getQuery().query(select));
    }

    public ArrayList<Room> getRooms(int limit) throws Exception {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*").table("room");
        select.limit(limit);

        return this.maps(this.getQuery().query(select));
    }

    public ArrayList<Room> getRooms(String building, String floor) throws Exception {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("building", building)
                .where("floor", floor)
                .select("*")
                .table("room");

        return this.maps(this.getQuery().query(select));
    }

    public ArrayList<Room> getRooms(RoomQuery queryBy, String query) throws Exception {
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

    public ArrayList<Room> getRoomsByBuilding(String building) throws Exception {
        String query = String.format(
                "SELECT * FROM `room` WHERE building LIKE '%%%s%%'", building
        );

        return this.maps(this.getQuery().unsafeQuery(query));
    }

    public ArrayList<Room> getRoomsByName(String name) throws Exception {
        String query = String.format(
                String.format("SELECT * FROM `room` WHERE name LIKE '%%%s%%'", name)
        );

        return this.maps(this.getQuery().unsafeQuery(query));
    }

    public ArrayList<Room> getRoomsByState(String state) throws Exception {
        String query = String.format(
                String.format("SELECT * FROM `room` WHERE state LIKE '%%%s%%'", state)
        );

        return this.maps(this.getQuery().unsafeQuery(query));
    }

    public ArrayList<Room> getRoomsByFloor(String floor)  throws Exception{
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("floor", floor)
                .select("*")
                .table("room");

        return this.maps(this.getQuery().query(select));
    }

    public Room getRoomById(int id)  throws Exception{
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*")
                .where("id", id)
                .limit(1)
                .table("room");

        return this.map(this.getQuery().query(select));
    }

    public void createRoom(Room room) throws Exception {
        FewInsertMySQL insert = new FewInsertMySQL();

        insert.insert("name", room.getName())
                .insert("building", room.getBuilding())
                .insert("capacity", room.getCapacity())
                .insert("close_time", room.getCloseTime().getTime())
                .insert("open_time", room.getOpenTime().getTime())
                .insert("floor", room.getFloor())
                .insert("state", room.getState())
                .table("room");

        this.getQuery().query(insert);
    }

    public void deleteRoomById(int id) throws Exception {
        FewDeleteMySQL delete = new FewDeleteMySQL();

        delete.table("room");
        delete.where("id", id);

        this.getQuery().query(delete);
    }

    public void updateRoom(Room room) throws Exception {
        FewUpdateMySQL update = new FewUpdateMySQL();

        if (room.getId() == 0) {
            throw new RuntimeException("Please provided an id to update user");
        }

        update.where("id", room.getId())
                .set("name", room.getName())
                .set("building", room.getBuilding())
                .set("capacity", room.getCapacity())
                .set("floor", room.getFloor())
                .set("close_time", room.getCloseTime().getTime())
                .set("open_time", room.getOpenTime().getTime())
                .set("state", room.getState())
                .table("room");
        this.getQuery().query(update);
    }
}
