package net.itkmitl.room.libs.phatsanphon.entity;

import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.date.DateTime;
import net.itkmitl.room.libs.phatsanphon.repository.RoomRepository;
import net.itkmitl.room.libs.phatsanphon.repository.UserRepository;

public class Reservation extends Entity {
    private int id;
    private User user;
    private Room room;
    private String reason;
    private DateTime startTime;
    private DateTime endTime;
    private DateTime reservationTime;
    private boolean isCancelled;

    public Reservation(FewQuery query) throws Exception {
        super(query);
    }

    public Reservation() throws Exception {}

    @Override
    public void processQuery(FewQuery query) throws Exception {
        try {
            this.setId(query.getValue("id").asInt());
            this.setUser(query.getValue("user_id").asInt());
            this.setRoom(query.getValue("room_id").asInt());
            this.setReason(query.getValue("reason").asString());
            this.setStartTime(query.getValue("start_time").asLong());
            this.setEndTime(query.getValue("end_time").asLong());
            this.setReservationTime(query.getValue("reservation_time").asLong());
            this.setCancelled(query.getValue("is_cancelled").asBoolean());
        } catch (Exception e) {
             throw e;
        }
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

    public void setUser(int userId) throws Exception {
        User user = new UserRepository(this.getDB()).getUserById(userId);
        this.setUser(user);
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setRoom(int roomId) throws Exception {
        try {
            Room room = new RoomRepository(this.getDB()).getRoomById(roomId);
        } catch (Exception e){
            throw e;
        }
        this.setRoom(room);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    private void setStartTime(long startTime) {
        this.setStartTime(new DateTime(startTime));
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    private void setEndTime(long endTime) {
        this.setEndTime(new DateTime(endTime));
    }

    public DateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(DateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    private void setReservationTime(long reservationTime) {
        this.setReservationTime(new DateTime(reservationTime));
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}
