package net.itkmitl.room.libs.peeranat.util;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.enums.EnumRoomState;
import net.itkmitl.room.libs.peeranat.FewDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.libs.phatsanphon.repository.RoomRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FewRoom {

    public static Collection<Room> getRoomByState(EnumRoomState state) throws Exception {
        RoomRepository roomRepository = new RoomRepository(LaewTaeDB.getDB());
        List<Room> newRoom = new ArrayList<>();
        for (Room room : roomRepository.getRooms()) {
            if (room.getState() == state) {
                newRoom.add(room);
            }
        }
        return Collections.unmodifiableList(newRoom);
    }

}
