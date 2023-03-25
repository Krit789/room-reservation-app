package net.itkmitl.room.libs.phatsanphon.entity;

import net.itkmitl.room.libs.peeranat.query.*;
import net.itkmitl.room.libs.phatsanphon.model.User;

public class UserEntity {
    final private FewQuery query;

    public UserEntity(FewQuery query) {
        this.query = query;
    }

    public void getUserById(int id) {
        FewInsertMySQL insert = new FewInsertMySQL();
    }
}
