package net.itkmitl.room;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.libs.peeranat.query.FewMySQLValue;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.query.FewSelectMySQL;
import net.itkmitl.room.libs.phatsanphon.entity.UserEntity;
import net.itkmitl.room.libs.phatsanphon.model.User;

import java.sql.SQLException;

public class ApplicationMain {

    public static void main(String[] args) throws SQLException {

        UserEntity userEntity = new UserEntity(RVDB.getDB());

        User user = userEntity.getUserById(1);

        System.out.println(user.getId() + " " + user.getFirstname() + " " + user.getLastname());
    }

}
