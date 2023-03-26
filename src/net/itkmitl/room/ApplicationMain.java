package net.itkmitl.room;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.libs.phatsanphon.entity.*;
import net.itkmitl.room.libs.phatsanphon.model.*;
import java.util.ArrayList;

public class ApplicationMain {

    public static void main(String[] args) {
        ReservationEntity reservationEntity = new ReservationEntity(RVDB.getDB());
        Reservation reservation = reservationEntity.getReservationById(1);

//        for (Reservation r : reservations) {
//            System.out.println(r.getId() + " " + r.getReason());
//        }
    }
}
