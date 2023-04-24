package net.itkmitl.room.libs.phatsanphon.repository;

import net.itkmitl.room.libs.peeranat.query.*;
import net.itkmitl.room.libs.phatsanphon.entity.*;

import java.util.ArrayList;

/**
 * ReservationRepository
 */
public class ReservationRepository extends Repository<Reservation> {
    /**
     * Constructor
     *
     * @param query FewQuery
     */
    public ReservationRepository(final FewQuery query) {
        super(Reservation.class, query);
    }

    /**
     * @return ArrayList<Reservation>
     */
    public ArrayList<Reservation> getReservations() {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*").table("reservation");

        return this.maps(this.getQuery().query(select));
    }

    /**
     * @param id int
     * @return Reservation
     */
    public Reservation getReservationById(int id) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("id", id)
                .select("*")
                .limit(1)
                .table("reservation");

        return this.map(this.getQuery().query(select));
    }

    /**
     * @param roomId int
     * @return ArrayList<Reservation>
     */
    public ArrayList<Reservation> getReservationByRoomId(int roomId) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("room_id", roomId)
                .select("*")
                .table("reservation");

        return this.maps(this.getQuery().query(select));
    }

    /**
     * @param userId int
     * @return ArrayList<Reservation>
     */
    public ArrayList<Reservation> getReservationByUserId(int userId) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("user_id", userId)
                .select("*")
                .table("reservation");

        return this.maps(this.getQuery().query(select));
    }

    /**
     * @param id int
     */
    public void deleteReservationById(int id) {
        FewDeleteMySQL delete = new FewDeleteMySQL();

        delete.where("id", id).table("reservation");

        this.getQuery().query(delete);
    }

    /**
     * @param reservation Reservation
     * @return boolean
     */
    public boolean createReservation(Reservation reservation) {
        FewInsertMySQL insert = new FewInsertMySQL();

        if (reservation.getUser() == null && reservation.getRoom() == null) {
            System.out.println("User or Room must include.");
            return false;
        }

        insert.insert("user_id", reservation.getUser().getId())
                .insert("room_id", reservation.getRoom().getId())
                .insert("start_time", reservation.getStartTime().toString())
                .insert("end_time", reservation.getEndTime().toString())
                .insert("reason", reservation.getReason())
                .table("reservation");

        this.getQuery().query(insert);

        return true;
    }

    public void updateReservation(Reservation reservation) {
        FewUpdateMySQL update = new FewUpdateMySQL();

        if (reservation.getId() == 0) {
            throw new RuntimeException("Please provided an id to update user");
        }

        update.where("id", reservation.getId())
                .set("user_id", reservation.getUser().getId())
                .set("room_id", reservation.getRoom().getId())
                .set("reason", reservation.getReason())
                .set("start_time", reservation.getStartTime().toString())
                .set("end_time", reservation.getEndTime().toString())
                .table("reservation");

        this.getQuery().query(update);
    }
}
