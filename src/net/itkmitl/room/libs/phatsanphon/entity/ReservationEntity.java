package net.itkmitl.room.libs.phatsanphon.entity;

import net.itkmitl.room.libs.peeranat.query.FewDeleteMySQL;
import net.itkmitl.room.libs.peeranat.query.FewInsertMySQL;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.query.FewSelectMySQL;
import net.itkmitl.room.libs.phatsanphon.model.Reservation;
import java.util.ArrayList;

public class ReservationEntity implements Entity<Reservation> {
    FewQuery query;

    public ReservationEntity(FewQuery query) {
        this.query = query;
    }

    @Override
    public Reservation map(FewQuery result) {
        Reservation object = null;

        while (result.nextBind()) {
            System.out.println(result.getValue("room_id").asInt());
//            object = new Reservation(result);
        }

        return null;
    }

    @Override
    public ArrayList<Reservation> maps(FewQuery result) {
        ArrayList<Reservation> objects = new ArrayList();

        while (result.nextBind()) {
            objects.add(new Reservation(result));
        }

        return objects;
    }

    public ArrayList<Reservation> getReservations() {
        FewSelectMySQL select = new FewSelectMySQL();

        select.select("*");
        select.table("room");

        FewQuery result = query.query(select);

        return this.maps(result);
    }

    public Reservation getReservationById(int id) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("id", id);
        select.select("*");
        select.table("room");

        FewQuery result = query.query(select);

        return this.map(result);
    }

    public Reservation getReservationByRoomId(int roomId) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("room_id", roomId);
        select.select("*");
        select.table("room");

        FewQuery result = query.query(select);

        return this.map(result);
    }

    public Reservation getReservationByUserId(int userId) {
        FewSelectMySQL select = new FewSelectMySQL();

        select.where("user_id", userId);
        select.select("*");
        select.table("room");

        FewQuery result = query.query(select);

        return this.map(result);
    }

    public void deleteReservationById(int id) {
        FewDeleteMySQL delete = new FewDeleteMySQL();

        delete.table("reservation");
        delete.where("id", id);

        query.query(delete);
    }

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
