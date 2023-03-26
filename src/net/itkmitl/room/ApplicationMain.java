package net.itkmitl.room;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.entity.*;
import net.itkmitl.room.libs.phatsanphon.model.*;

import java.util.ArrayList;

public class ApplicationMain {

    public static void main(String[] args) {
        FewQuery db = RVDB.getDB();
        UserEntity userEntity = new UserEntity(db);
        RoomEntity roomEntity = new RoomEntity(db);
        ArrayList<User> users = userEntity.getUsers();

        for (User u : users) {
            System.out.println(u.getFirstname() + " " + u.getLastname() + " " + u.getCreatedOn().toString());
        }

        Room room = new Room();
        room.setBuilding("Information Technology");
        room.setName("Auditorium");
        room.setFloor("M");
        room.setCapacity(250);

        roomEntity.createRoom(room);
    }
}
