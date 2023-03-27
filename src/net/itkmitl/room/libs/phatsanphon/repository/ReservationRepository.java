package net.itkmitl.room.libs.phatsanphon.repository;

import net.itkmitl.room.libs.peeranat.query.*;
import net.itkmitl.room.libs.phatsanphon.entity.*;
import java.util.ArrayList;

/**
 * ReservationRepository
 */
public class ReservationRepository extends Repository<Reservation> {
    private final FewQuery query;

    /**
     * Constructor
     * @param query FewQuery
     */
    public ReservationRepository(final FewQuery query) {
        super(Reservation.class);
        this.query = query;
    }

    /**
     * @return ArrayList<Reservation>
     */
    public ArrayList<Reservation> getReservations() {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*");
        select.table("reservation");

        FewQuery result = query.query(select);

        return this.maps(result);
    }

    /**
     * @param id int
     * @return Reservation
     */
    public Reservation getReservationById(int id) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("id", id);
        select.select("*");
        select.table("reservation");

        FewQuery result = query.query(select);

        return this.map(result);
    }

    /**
     * @param roomId int
     * @return ArrayList<Reservation>
     */
    public ArrayList<Reservation> getReservationByRoomId(int roomId) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("room_id", roomId);
        select.select("*");
        select.table("reservation");

        FewQuery result = query.query(select);

        return this.maps(result);
    }

    /**
     * @param userId int
     * @return ArrayList<Reservation>
     */
    public ArrayList<Reservation> getReservationByUserId(int userId) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("user_id", userId);
        select.select("*");
        select.table("reservation");

        FewQuery result = query.query(select);

        return this.maps(result);
    }

    /**
     * @param id int
     */
    public void deleteReservationById(int id) {
        FewDeleteMySQL delete = new FewDeleteMySQL();

        delete.table("reservation");
        delete.where("id", id);

        query.query(delete);
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

        insert.insert("user_id", reservation.getUser().getId());
        insert.insert("room_id", reservation.getRoom().getId());
        insert.insert("reason", reservation.getReason());
        insert.table("reservation");

        query.query(insert);

        return true;
    }
}
