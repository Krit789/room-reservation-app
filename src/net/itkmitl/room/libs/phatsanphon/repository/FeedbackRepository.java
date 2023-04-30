package net.itkmitl.room.libs.phatsanphon.repository;

import java.util.ArrayList;

import net.itkmitl.room.libs.peeranat.query.FewDeleteMySQL;
import net.itkmitl.room.libs.peeranat.query.FewInsertMySQL;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.query.FewSelectMySQL;
import net.itkmitl.room.libs.peeranat.query.FewUpdateMySQL;
import net.itkmitl.room.libs.phatsanphon.entity.Feedback;

public class FeedbackRepository extends Repository<Feedback> {

    public FeedbackRepository(FewQuery query) {
        super(Feedback.class, query);
    }

    public ArrayList<Feedback> getFeedbacks() {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*").table("feedback");

        return this.maps(this.getQuery().query(select));
    }

    public ArrayList<Feedback> getFeedbacks(int limit) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*").limit(limit).table("feedback");

        return this.maps(this.getQuery().query(select));
    }

    public ArrayList<Feedback> getFeedbacksBy(int prop, String search) {
        String query = null;
        switch (prop) {
            case 0: // ID
                query = String.format("SELECT * FROM `feedback` WHERE id='%s'", search);
                break;
            case 1: // Room ID
                query = String.format("SELECT * FROM `feedback` WHERE room_id='%s'", search);
                break;
            case 2: // User ID
                query = String.format("SELECT * FROM `feedback` WHERE lastname='%s'", search);
                break;
            case 3: // Rating
                query = String.format("SELECT * FROM `feedback` WHERE rating LIKE '%%%s'", search);
                break;
            case 4: // Comment
                query = String.format("SELECT * FROM `feedback` WHERE comment LIKE '%%%s%%'", search);
                break;
        }
        return this.maps(this.getQuery().unsafeQuery(query));
    }

    public Feedback getFeedbackById(int id) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("id", id)
                .select("*")
                .table("feedback");

        return this.map(this.getQuery().query(select));
    }

    public ArrayList<Feedback> getFeedbackByUserId(int userId) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("user_id", userId)
                .select("*")
                .table("feedback");

        return this.maps(this.getQuery().query(select));
    }

    public ArrayList<Feedback> getFeedbackByRoomId(int roomId) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("user_id", roomId)
                .select("*")
                .table("feedback");

        return this.maps(this.getQuery().query(select));
    }

    public void createFeedback(Feedback feedback) {
        FewInsertMySQL insert = new FewInsertMySQL();

        insert.insert("user_id", feedback.getUser().getId())
                .insert("room_id", feedback.getRoom().getId())
                .insert("comment", feedback.getComment())
                .insert("rating", feedback.getRating())
                .table("feedback");

        this.getQuery().query(insert);
    }

    public void updateFeedback(Feedback feedback) {
        FewUpdateMySQL update = new FewUpdateMySQL();

        if (feedback.getId() == 0) {
            throw new RuntimeException("Please provided an id to update user");
        }

        update.where("id", feedback.getId())
                .set("user_id", feedback.getUser().getId())
                .set("room_id", feedback.getRoom().getId())
                .set("comment", feedback.getComment())
                .set("rating", feedback.getRating())
                .table("feedback");

        this.getQuery().query(update);
    }

    public void deleteFeedbackById(int id) {
        FewDeleteMySQL delete = new FewDeleteMySQL();

        delete.where("id", id).table("feedback");

        this.getQuery().query(delete);
    }
}
