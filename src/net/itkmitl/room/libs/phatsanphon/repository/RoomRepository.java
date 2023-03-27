package net.itkmitl.room.libs.phatsanphon.repository;

import net.itkmitl.room.libs.peeranat.query.*;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import java.util.ArrayList;

public class RoomRepository extends Repository<Room> {
    public RoomRepository(final FewQuery query) {
        super(Room.class, query);
    }

    public ArrayList<Room> getRooms(int limit) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*");
        select.table("room");
        select.limit(limit);

        return this.maps(this.getQuery().query(select));
    }

    public Room getRoomById(int id) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*");
        select.where("id", id);
        select.table("room");

        return this.map(this.getQuery().query(select));
    }

    public void createRoom(Room room) {
        FewInsertMySQL insert = new FewInsertMySQL();

        insert.insert("name", room.getName());
        insert.insert("building", room.getBuilding());
        insert.insert("capacity", room.getCapacity());
        insert.insert("floor", room.getFloor());
        insert.table("room");

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
            try {
                throw new Exception("Please provided an id to update user");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        update.where("id", room.getId());
        update.set("name", room.getName());
        update.set("building", room.getBuilding());
        update.set("capacity", room.getCapacity());
        update.set("floor", room.getFloor());
        update.table("user");

        this.getQuery().query(update);
    }
}
