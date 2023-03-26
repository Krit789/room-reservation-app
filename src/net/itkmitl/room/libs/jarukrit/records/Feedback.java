package net.itkmitl.room.libs.jarukrit.records;

import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.query.FewInsertMySQL;
import net.itkmitl.room.db.RVDB;

public class Feedback {
    private int room_id;
    private int user_id;
    private String comment;
    private double rating = -1;

    public Feedback(int room_id, int user_id, double rating) {
        this(room_id, user_id, "", rating);
    }

    public Feedback(int room_id, int user_id, String comment, double rating) {
        this.room_id = room_id;
        this.user_id = user_id;
        this.comment = comment;
        this.rating = rating;
    }

    public boolean submit() {
        if (getRating() >= 0 && getRating() <= 10) {
            FewQuery query = RVDB.getDB();
            FewInsertMySQL insertMySQL = new FewInsertMySQL();
            insertMySQL.table("feedback");
            insertMySQL.insert("room_id", getRoom_id());
            insertMySQL.insert("user_id", getUser_id());
            insertMySQL.insert("comment", getComment());
            insertMySQL.insert("rating", getRating());
            query.query(insertMySQL);
            return true;
        }
        return false;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if (rating >= 0 && rating <= 10)
            this.rating = rating;
        else System.out.println("[Feedback] Rating is out of range!");
    }
}
