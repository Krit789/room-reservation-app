package net.itkmitl.room.libs.phatsanphon.entity;

import net.itkmitl.room.libs.peeranat.query.*;
import net.itkmitl.room.libs.phatsanphon.model.User;

public class UserEntity {
    private final FewQuery query;

    public UserEntity(FewQuery query) {
        this.query = query;
    }

    public User getUserById(int id) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*");
        select.where("id", id);

        FewQuery result = query.query(select);

        return new User(result);
    }
}
