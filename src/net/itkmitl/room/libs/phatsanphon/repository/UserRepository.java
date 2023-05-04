package net.itkmitl.room.libs.phatsanphon.repository;

import java.util.ArrayList;

import net.itkmitl.room.libs.peeranat.query.FewDeleteMySQL;
import net.itkmitl.room.libs.peeranat.query.FewInsertMySQL;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.query.FewSelectMySQL;
import net.itkmitl.room.libs.peeranat.query.FewUpdateMySQL;
import net.itkmitl.room.libs.phatsanphon.entity.Reservation;
import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.phatsanphon.repository.enums.UserQuery;

public class UserRepository extends Repository<User> {

    public UserRepository(final FewQuery query) {
        super(User.class, query);
    }

    /*
     * Get all users
     */
    public ArrayList<User> getUsers() throws Exception {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*").table("user");

        return this.maps(this.getQuery().query(select));
    }

    /*
     * Get all users with limit
     */
    public ArrayList<User> getUsers(int limit) throws Exception {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*").limit(limit).table("user");

        return this.maps(this.getQuery().query(select));
    }

    public ArrayList<User> getUsers(UserQuery queryBy, String query) throws Exception {
        if (queryBy == UserQuery.ID) {
            User data = this.getUserById(Integer.parseInt(query));

            ArrayList<User> objects = new ArrayList<>();
            objects.add(data);

            return objects;
        } else if (queryBy == UserQuery.FIRSTNAME) {
            return this.getUsersByFirstname(query);
        } else if (queryBy == UserQuery.LASTNAME) {
            return this.getUsersByLastname(query);
        } else if (queryBy == UserQuery.EMAIL) {
            User data = this.getUserByEmail(query);
            ArrayList<User> objects = new ArrayList<>();
            objects.add(data);
            return objects;
        } else if (queryBy == UserQuery.PHONE_NUMBER) {
            return this.getUsersByTelephoneNumber(query);
        }

        return null;
    }


    /*
     * Get user by id
     */
    public User getUserById(int id) throws Exception {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*")
                .where("id", id)
                .limit(1)
                .table("user");

        return this.map(this.getQuery().query(select));
    }

    /*
     * Get user by email
     */
    public User getUserByEmail(String email) throws Exception {
        String query = String.format("SELECT * FROM `user` WHERE email LIKE '%%%s%%'", email);

        return this.map(this.getQuery().unsafeQuery(query));
    }

    public User getExactUserByEmail(String email) throws Exception {
        FewSelectMySQL select = new FewSelectMySQL();
        select.select("*")
              .where("email", email)
              .limit(1)
              .table("user");
        return this.map(this.getQuery().query(select));
    }

    public ArrayList<User> getUsersByFirstname(String firstname) throws Exception {
        String query = String.format("SELECT * FROM `user` WHERE firstname LIKE '%%%s%%'", firstname);

        return this.maps(this.getQuery().unsafeQuery(query));
    }

    public ArrayList<User> getUsersByTelephoneNumber(String telephoneNumber) throws Exception {
        String query = String.format("SELECT * FROM `user` WHERE tel_num LIKE '%%%s%%'", telephoneNumber);

        return this.maps(this.getQuery().unsafeQuery(query));
    }

    public ArrayList<User> getUsersByLastname(String lastname) throws Exception {
        String query = String.format("SELECT * FROM `user` WHERE lastname LIKE '%%%s%%'", lastname);

        return this.maps(this.getQuery().unsafeQuery(query));
    }

    /*
     * Create user
     */
    public void createUser(User user) throws Exception {
        FewInsertMySQL insert = new FewInsertMySQL();

        insert.insert("firstname", user.getFirstname())
                .insert("lastname", user.getLastname())
                .insert("tel_num", user.getTelephoneNumber())
                .insert("email", user.getEmail())
                .insert("password_hash", user.getPasswordHash())
                .insert("role", user.getRole().getLevel())
                .insert("created_on", System.currentTimeMillis() / 1000L)
                .table("user");

        this.getQuery().query(insert);
    }

    public void deleteUserById(int id) throws Exception {
        FewDeleteMySQL delete = new FewDeleteMySQL();

        delete.where("id", id).table("user");

        this.getQuery().query(delete);
    }

    public void updateUser(User user) throws Exception {
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
