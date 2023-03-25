package net.itkmitl.room;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.libs.phatsanphon.entity.UserEntity;
import net.itkmitl.room.libs.phatsanphon.model.User;

import java.util.ArrayList;

public class ApplicationMain {

    public static void main(String[] args) {
        UserEntity userEntity = new UserEntity(RVDB.getDB());
        ArrayList<User> users = userEntity.getUsers();

        for (User u : users) {
            System.out.println(u.getFirstname() + " " + u.getLastname());
        }
    }
}
