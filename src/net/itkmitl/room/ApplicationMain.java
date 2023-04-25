package net.itkmitl.room;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.entity.*;
import net.itkmitl.room.libs.phatsanphon.repository.*;

import java.util.ArrayList;

public class ApplicationMain {
    public static void main(String[] args) {
        FewQuery db = RVDB.getDB();
        RoomRepository roomRepository = new RoomRepository(db);
        ArrayList<Room> roomList = roomRepository.getRooms();

        for (Room room : roomList) {
            System.out.println(room.getName());
        }
    }
}
