package net.itkmitl.room.libs.phatsanphon.entity;

import net.itkmitl.room.libs.peeranat.query.*;
import net.itkmitl.room.libs.phatsanphon.model.User;

import java.sql.Array;
import java.util.ArrayList;

public class UserEntity {
    private final FewQuery query;

    public UserEntity(FewQuery query) {
        this.query = query;
    }

    private User mapUser(FewQuery result) {
        User user = null;

        while (result.nextBind()) {
            user = new User(result);
        }

        return user;
    }

    private ArrayList<User> mapUsers(FewQuery result) {
        ArrayList<User> users = new ArrayList();

        while (result.nextBind()) {
            users.add(new User(result));
        }

        return users;
    }

    /*
    * Get all users
    */
    public ArrayList<User> getUsers() {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*");
        select.table("user");

        FewQuery result = query.query(select);

        return this.mapUsers(result);
    }

    /*
     * Get all users with limit
     */
    public ArrayList<User> getUsers(int limit) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*");
        select.table("user");
        select.limit(limit);

        FewQuery result = query.query(select);

        return this.mapUsers(result);
    }

    /*
     * Get user by id
     */
    public User getUserById(int id) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*");
        select.where("id", id);
        select.limit(1);
        select.table("user");

        FewQuery result = query.query(select);

        return this.mapUser(result);
    }

    /*
     * Create user
     */
    public User createUser(User user) {
        FewInsertMySQL insert = new FewInsertMySQL();

        insert.insert("firstname", user.getFirstname());
        insert.insert("lastname", user.getLastname());
        insert.insert("tel_num", user.getTelephoneNumber());
        insert.insert("email", user.getEmail());
        insert.table("user");

        FewQuery result = query.query(insert);

        return this.mapUser(result);
    }
}
