package net.itkmitl.room.libs.phatsanphon.model;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.date.DateTime;
import net.itkmitl.room.libs.phatsanphon.entity.RoomEntity;
import net.itkmitl.room.libs.phatsanphon.entity.UserEntity;

import java.sql.Date;

public class Reservation {
    private int id;
    private User user;
    private Room room;
    private String reason;
    private Date startTime;
    private Date endTime;
    private Date reservationTime;
    private boolean isCancelled;

    public Reservation(FewQuery query) {
        this.processQuery(query);
    }

    public Reservation() {

    }

    private void processQuery(FewQuery query) {

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
        User user = new UserEntity(RVDB.getDB()).getUserById(userId);
        this.setUser(user);
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setRoom(int roomId) {
        Room room = new RoomEntity(RVDB.getDB()).getRoomById(roomId);
        this.setRoom(room);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    private void setStartTime(String rawStartTime) {
        this.setStartTime(new DateTime(rawStartTime).getDateTime());
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    private void setEndTime(String rawEndTime) {
        this.setEndTime(new DateTime(rawEndTime).getDateTime());
    }

    public Date getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Date reservationTime) {
        this.reservationTime = reservationTime;
    }

    private void setReservationTime(String rawReservationTime) {
        this.setReservationTime(new DateTime(rawReservationTime).getDateTime());
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}
