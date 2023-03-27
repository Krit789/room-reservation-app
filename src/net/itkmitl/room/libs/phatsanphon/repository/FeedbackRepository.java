package net.itkmitl.room.libs.phatsanphon.repository;

import net.itkmitl.room.libs.peeranat.query.*;
import net.itkmitl.room.libs.phatsanphon.entity.Feedback;

import java.util.ArrayList;

public class FeedbackRepository extends Repository<Feedback> {

    public FeedbackRepository(FewQuery query) {
        super(Feedback.class, query);
    }

    public ArrayList<Feedback> getFeedbacks() {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*");
        select.table("feedback");

        return this.maps(this.getQuery().query(select));
    }

    public ArrayList<Feedback> getFeedbacks(int limit) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*");
        select.limit(limit);
        select.table("feedback");

        return this.maps(this.getQuery().query(select));
    }

    public Feedback getFeedbackById(int id) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("id", id);
        select.select("*");
        select.table("feedback");

        return this.map(this.getQuery().query(select));
    }

    public ArrayList<Feedback> getFeedbackByUserId(int userId) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("user_id", userId);
        select.select("*");
        select.table("feedback");

        return this.maps(this.getQuery().query(select));
    }

    public ArrayList<Feedback> getFeedbackByRoomId(int roomId) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("user_id", roomId);
        select.select("*");
        select.table("feedback");

        return this.maps(this.getQuery().query(select));
    }

    public void createFeedback(Feedback feedback) {
        FewInsertMySQL insert = new FewInsertMySQL();

        insert.insert("user_id", feedback.getUser().getId());
        insert.insert("room_id", feedback.getRoom().getId());
        insert.insert("comment", feedback.getComment());
        insert.insert("rating", feedback.getRating());
        insert.table("feedback");

        this.getQuery().query(insert);
    }

    public void updateFeedback(Feedback feedback) {
        FewUpdateMySQL update = new FewUpdateMySQL();

        if (feedback.getId() == 0) {
            try {
                throw new Exception("Please provided an id to update user");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        update.where("id", feedback.getId());
        update.set("user_id", feedback.getUser().getId());
        update.set("room_id", feedback.getRoom().getId());
        update.set("comment", feedback.getComment());
        update.set("rating", feedback.getRating());
        update.table("feedback");

        this.getQuery().query(update);
    }

    public void deleteFeedbackById(int id) {
        FewDeleteMySQL delete = new FewDeleteMySQL();

        delete.table("feedback");
        delete.where("id", id);

        this.getQuery().query(delete);
    }
}
