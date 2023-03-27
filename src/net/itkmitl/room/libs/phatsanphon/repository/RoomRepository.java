package net.itkmitl.room.libs.phatsanphon.repository;

import net.itkmitl.room.libs.peeranat.query.*;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import java.util.ArrayList;

public class RoomRepository extends Repository<Room> {
    private final FewQuery query;

    public RoomRepository(final FewQuery query) {
        super(Room.class);
        this.query = query;
    }

    public Room map(FewQuery result) {
        Room object = null;

        while (result.nextBind()) {
            object = new Room(result);
        }

        return object;
    }

    @Override
    public ArrayList<Room> maps(FewQuery result) {
        ArrayList<Room> objects = new ArrayList<>();

        while (result.nextBind()) {
            objects.add(new Room(result));
        }

        return objects;
    }

    public ArrayList<Room> getRooms(int limit) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*");
        select.table("room");
        select.limit(limit);

        FewQuery result = query.query(select);

        return this.maps(result);
    }

    public Room getRoomById(int id) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*");
        select.where("id", id);
        select.limit(1);
        select.table("room");

        FewQuery result = query.query(select);

        return this.map(result);
    }

    public void createRoom(Room room) {
        FewInsertMySQL insert = new FewInsertMySQL();

        insert.insert("name", room.getName());
        insert.insert("building", room.getBuilding());
        insert.insert("capacity", room.getCapacity());
        insert.insert("floor", room.getFloor());
        insert.table("room");

        query.query(insert);
    }

    public void deleteRoomById(int id) {
        FewDeleteMySQL delete = new FewDeleteMySQL();

        delete.table("room");
        delete.where("id", id);

        query.query(delete);
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

        query.query(update);
    }
}
