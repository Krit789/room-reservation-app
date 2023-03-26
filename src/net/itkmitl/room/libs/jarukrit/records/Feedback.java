package net.itkmitl.room.libs.jarukrit.records;

import java.sql.Timestamp;
import java.time.LocalDateTime;


public class Feedback {
    private int feedback_id;
    private int room_id;
    private Timestamp created_on;
    private int user_id;
    private String comment;
    private double rating;

    public Feedback(int room_id, int user_id, double rating) {
        this(room_id, user_id, "", rating);
    }

    public Feedback(int room_id, int user_id, String comment, double rating) {
        this(-1, room_id, user_id, "", rating);
    }

    public Feedback(int feedback_id, int room_id, int user_id, String comment, double rating) {
        this.room_id = room_id;
        this.user_id = user_id;
        this.comment = comment;
        this.rating = rating;
        this.feedback_id = feedback_id;
    }

    public Feedback(int feedback_id, int room_id, int user_id, String created_on, String comment, double rating) {
        this.feedback_id = feedback_id;
        this.room_id = room_id;
        this.setCreated_on(Timestamp.valueOf(LocalDateTime.parse(created_on)));
        this.user_id = user_id;
        this.comment = comment;
        this.rating = rating;

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

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public Timestamp getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Timestamp created_on) {
        this.created_on = created_on;
    }
}
