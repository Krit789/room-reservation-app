package net.itkmitl.room.libs.phatsanphon.entity;

import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.date.DateTime;
import net.itkmitl.room.libs.phatsanphon.repository.RoomRepository;
import net.itkmitl.room.libs.phatsanphon.repository.UserRepository;

public class Feedback extends Entity {
    private int id;
    private Room room;
    private User user;
    private DateTime createdOn;
    private String comment;
    private double rating;

    public Feedback(FewQuery result) {
        super(result);
    }

    public Feedback() {
    }

    @Override
    public void processQuery(FewQuery query) {
        this.setId(query.getValue("id").asInt());
        this.setUser(query.getValue("user_id").asInt());
        this.setRoom(query.getValue("room_id").asInt());
        this.setComment(query.getValue("comment").asString());
        this.setRating(query.getValue("rating").asDouble());
        this.setCreatedOn(query.getValue("created_on").asString());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUser(int userId) {
        User user = new UserRepository(this.getDB()).getUserById(userId);
        this.setUser(user);
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setRoom(int roomId) {
        Room room = new RoomRepository(this.getDB()).getRoomById(roomId);
        this.setRoom(room);
    }

    public DateTime getCreatedOn() {
        return createdOn;
    }

    private void setCreatedOn(DateTime createdOn) {
        this.createdOn = createdOn;
    }

    private void setCreatedOn(long createdOn) {
        this.setCreatedOn(new DateTime(createdOn));
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
        this.rating = rating;
    }
}
