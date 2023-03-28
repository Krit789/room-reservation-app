package net.itkmitl.room.libs.phatsanphon.repository;

import net.itkmitl.room.libs.peeranat.query.*;
import net.itkmitl.room.libs.phatsanphon.entity.User;

import java.util.ArrayList;

public class UserRepository extends Repository<User> {
    public UserRepository(final FewQuery query) {
        super(User.class, query);
    }

    /*
     * Get all users
     */
    public ArrayList<User> getUsers() {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*");
        select.table("user");

        return this.maps(this.getQuery().query(select));
    }

    /*
     * Get all users with limit
     */
    public ArrayList<User> getUsers(int limit) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*");
        select.table("user");
        select.limit(limit);

        return this.maps(this.getQuery().query(select));
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

        return this.map(this.getQuery().query(select));
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

        this.getQuery().query(insert);
    }

    public void deleteUserById(int id) {
        FewDeleteMySQL delete = new FewDeleteMySQL();

        delete.table("user");
        delete.where("id", id);

        this.getQuery().query(delete);
    }

    public void updateUser(User user) {
        FewUpdateMySQL update = new FewUpdateMySQL();

        if (user.getId() == 0) {
            throw new RuntimeException("Please provided an id to update user");
        }

        update.where("id", user.getId());
        update.set("email", user.getEmail());
        update.set("firstname", user.getFirstname());
        update.set("lastname", user.getLastname());
        update.set("tel_num", user.getTelephoneNumber());
        update.set("is_active", user.isActive());
        update.set("role", user.getRole());
        update.table("user");

        this.getQuery().query(update);
    }
}
