package net.itkmitl.room.libs.phatsanphon.repository;

import net.itkmitl.room.libs.peeranat.query.*;
import net.itkmitl.room.libs.peeranat.util.FewMySQL;
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

        select.select("*").table("user");

        return this.maps(this.getQuery().query(select));
    }

    /*
     * Get all users with limit
     */
    public ArrayList<User> getUsers(int limit) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*").limit(limit).table("user");

        return this.maps(this.getQuery().query(select));
    }

    public ArrayList<User> getUsersBy(int prop, String search) {
        String query = null;
        switch (prop) {
            case 0: // ID
                query = String.format("SELECT * FROM `user` WHERE id='%s'", search);
                break;
            case 1: // First Name
                query = String.format("SELECT * FROM `user` WHERE firstname LIKE '%%%s%%'", search);
                break;
            case 2: // Last Name
                query = String.format("SELECT * FROM `user` WHERE lastname LIKE '%%%s%%'", search);
                break;
            case 3: // E-Mail
                query = String.format("SELECT * FROM `user` WHERE email LIKE '%%%s%%'", search);
                break;
            case 4: // Phone Number
                query = String.format("SELECT * FROM `user` WHERE tel_num LIKE '%%%s%%'", search);
                break;
        }
        return this.maps(this.getQuery().unsafeQuery(query));
    }


    /*
     * Get user by id
     */
    public User getUserById(int id) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*")
                .where("id", id)
                .limit(1)
                .table("user");

        return this.map(this.getQuery().query(select));
    }

    /*
     * Create user
     */
    public void createUser(User user) {
        FewInsertMySQL insert = new FewInsertMySQL();

        insert.insert("firstname", user.getFirstname())
                .insert("lastname", user.getLastname())
                .insert("tel_num", user.getTelephoneNumber())
                .insert("email", user.getEmail())
                .insert("password_hash", user.getPasswordHash())
                .insert("role", user.getRole().getLevel())
                .table("user");

        this.getQuery().query(insert);
    }

    public void deleteUserById(int id) {
        FewDeleteMySQL delete = new FewDeleteMySQL();

        delete.where("id", id).table("user");

        this.getQuery().query(delete);
    }

    public void updateUser(User user) {
        FewUpdateMySQL update = new FewUpdateMySQL();

        if (user.getId() == 0) {
            throw new RuntimeException("Please provide user id to update user record");
        }

        update.where("id", user.getId())
                .set("email", user.getEmail())
                .set("firstname", user.getFirstname())
                .set("lastname", user.getLastname())
                .set("tel_num", user.getTelephoneNumber())
                .set("is_active", user.isActive() ? 1 : 0)
                .set("password_hash", user.getPasswordHash())
                .set("role", user.getRole().getLevel())
                .table("user");

        this.getQuery().query(update);
    }
}
