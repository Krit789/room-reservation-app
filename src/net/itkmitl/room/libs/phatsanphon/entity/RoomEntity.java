package net.itkmitl.room.libs.phatsanphon.entity;

import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.model.Room;
import net.itkmitl.room.libs.phatsanphon.model.User;

import java.util.ArrayList;

public class RoomEntity implements Entity<Room> {
    private final FewQuery query;

    public RoomEntity(FewQuery query) {
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
        ArrayList<Room> objects = new ArrayList();

        while (result.nextBind()) {
            objects.add(new Room(result));
        }

        return objects;
    }
}
