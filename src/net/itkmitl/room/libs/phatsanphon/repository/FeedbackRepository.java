package net.itkmitl.room.libs.phatsanphon.repository;

import java.util.ArrayList;

import net.itkmitl.room.libs.peeranat.query.*;
import net.itkmitl.room.libs.phatsanphon.entity.Feedback;
import net.itkmitl.room.libs.phatsanphon.repository.enums.FeedbackQuery;

public class FeedbackRepository extends Repository<Feedback> {

    public FeedbackRepository(FewQuery query) {
        super(Feedback.class, query);
    }

    public ArrayList<Feedback> getFeedbacks() throws Exception{
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*").table("feedback");

        return this.maps(this.getQuery().query(select));
    }

    public ArrayList<Feedback> getFeedbacks(int limit) throws Exception {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*").limit(limit).table("feedback");

        return this.maps(this.getQuery().query(select));
    }


    public ArrayList<Feedback> getFeedbacks(FeedbackQuery queryBy, String query) throws Exception {
        if (queryBy == FeedbackQuery.ID) {
            Feedback data = this.getFeedbackById(Integer.parseInt(query));

            ArrayList<Feedback> objects = new ArrayList<>();
            objects.add(data);

            return objects;
        } else if (queryBy == FeedbackQuery.ROOM_ID) {
            return this.getFeedbacksByRoomId(Integer.parseInt(query));
        } else if (queryBy == FeedbackQuery.USER_ID) {
            return this.getFeedbacksByUserId(Integer.parseInt(query));
        } else if (queryBy == FeedbackQuery.RATING) {
            return this.getFeedbacksByRating(query);
        } else if (queryBy == FeedbackQuery.COMMENT) {
            return this.getFeedbacksByComment(query);
        }

        return null;
    }

    public Feedback getFeedbackById(int id) throws Exception {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("id", id)
                .select("*")
                .table("feedback");

        return this.map(this.getQuery().query(select));
    }

    public ArrayList<Feedback> getFeedbacksByUserId(int userId) throws Exception {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("user_id", userId)
                .select("*")
                .table("feedback");

        return this.maps(this.getQuery().query(select));
    }

    public ArrayList<Feedback> getFeedbacksByRoomId(int roomId) throws Exception {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("room_id", roomId)
                .select("*")
                .table("feedback");

        return this.maps(this.getQuery().query(select));
    }

    public ArrayList<Feedback> getFeedbacksByComment(String comment) throws Exception {
        String query = String.format("SELECT * FROM `feedback` WHERE comment LIKE '%%%s%%'", comment);
        return this.maps(this.getQuery().unsafeQuery(query));
    }

    public ArrayList<Feedback> getFeedbacksByRating(String rating) throws Exception {
        String query = String.format("SELECT * FROM `feedback` WHERE rating LIKE '%%%s'", rating);
        return this.maps(this.getQuery().unsafeQuery(query));
    }

    public void createFeedback(Feedback feedback) throws Exception {
        FewInsertMySQL insert = new FewInsertMySQL();

        insert.insert("user_id", feedback.getUser().getId())
                .insert("room_id", feedback.getRoom().getId())
                .insert("comment", feedback.getComment())
                .insert("created_on", System.currentTimeMillis())
                .insert("rating", feedback.getRating())
                .table("feedback");

        this.getQuery().query(insert);
    }

    public void updateFeedback(Feedback feedback) throws Exception {
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

    public void deleteFeedbackById(int id) throws Exception {
        FewDeleteMySQL delete = new FewDeleteMySQL();

        delete.where("id", id).table("feedback");

        this.getQuery().query(delete);
    }
}
