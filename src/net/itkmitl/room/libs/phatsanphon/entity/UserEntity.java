package net.itkmitl.room.libs.phatsanphon.entity;

import net.itkmitl.room.libs.peeranat.query.*;
import net.itkmitl.room.libs.phatsanphon.model.User;
import java.util.ArrayList;

public class UserEntity implements Entity<User> {
    private final FewQuery query;

    public UserEntity(FewQuery query) {
        this.query = query;
    }

    @Override
    public User map(FewQuery result) {
        User object = null;

        while (result.nextBind()) {
            object = new User(result);
        }

        return object;
    }

    @Override
    public ArrayList<User> maps(FewQuery result) {
        ArrayList<User> objects = new ArrayList();

        while (result.nextBind()) {
            objects.add(new User(result));
        }

        return objects;
    }


    /*
     * Get all users
     */
    public ArrayList<User> getUsers() {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*");
        select.table("user");

        FewQuery result = query.query(select);

        return this.maps(result);
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

        return this.maps(result);
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

        return this.map(result);
    }

    /*
     * Create user
     */
    public void createUser(User user) {
        FewInsertMySQL insert = new FewInsertMySQL();

        insert.insert("firstname", user.getFirstname());
        insert.insert("lastname", user.getLastname());
        insert.insert("tel_num", user.getTelephoneNumber());
        insert.insert("email", user.getEmail());
        insert.table("user");

        query.query(insert);

    }

    public void deleteUserById(int id) {
        FewDeleteMySQL delete = new FewDeleteMySQL();

        delete.table("user");
        delete.where("id", id);

        query.query(delete);
    }

    public void updateUser(User user) {
        FewUpdateMySQL update = new FewUpdateMySQL();

        if (user.getId() == 0) {
            try {
                throw new Exception("Please provided an id to update user");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        update.where("id", user.getId());
        update.set("email", user.getEmail());
        update.set("firstname", user.getFirstname());
        update.set("lastname", user.getLastname());
        update.set("tel_num", user.getTelephoneNumber());
        update.set("is_active", user.isActive());
        update.set("role", user.getRole());
        update.table("user");

        System.out.println("OK");

        System.out.println(update);
        query.query(update);
    }
}
