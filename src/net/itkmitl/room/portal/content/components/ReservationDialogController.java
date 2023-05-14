package net.itkmitl.room.portal.content.components;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.entity.Reservation;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.libs.phatsanphon.repository.ReservationRepository;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

public class ReservationDialogController {
    public ReservationDialog view;
    private Room myRoom;
    private ArrayList<Object[]> allReservedTime = new ArrayList<>();
    private ArrayList<Object[]> availableTime = new ArrayList<>();
    public ReservationDialogController(JFrame parent, Room room){
        myRoom = room;
        view = new ReservationDialog(parent, room);
        dataFetch();
    }

    public void dataFetch(){
        SwingWorker<?, ?> worker = new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() throws Exception {
                FewQuery db = LaewTaeDB.getDB();
                ReservationRepository rsvprp = new ReservationRepository(db);
                Long currentTime = System.currentTimeMillis();
                ArrayList<Reservation> resList = rsvprp.getReservationsByRoomIdAndTimeRange(myRoom.getId(), currentTime / 1000L, (currentTime + TimeUnit.DAYS.toMillis(7) / 1000L));
                for (Reservation r: resList){
                    allReservedTime.add(new Object[]{r.getStartTime().getTime(), r.getEndTime().getTime()});
                }
                availableTime = getAvailableTimes(allReservedTime, myRoom.getOpenTime().getTime(), myRoom.getCloseTime().getTime());
                for (Object[] o: availableTime){
                    view.segmentBox.addItem(new ReservableEntity(o));
                }
                return null;
            }
            @Override
            protected void done(){

            }
        };
        worker.execute();
    }

    public static ArrayList<Object[]> getAvailableTimes(ArrayList<Object[]> existingReservations, long roomOpenTime, long roomCloseTime) {
        ArrayList<Object[]> availableTimes = new ArrayList<Object[]>();

        // If there are no existing reservations, the entire room open time is available
        if (existingReservations.size() == 0) {
            availableTimes.add(new Object[]{roomOpenTime, roomCloseTime});
            return availableTimes;
        }

        // Sort the existing reservations by start time
        existingReservations.sort(new Comparator<Object[]>() {
            public int compare(Object[] reservation1, Object[] reservation2) {
                return Long.compare((long) reservation1[0], (long) reservation2[0]);
            }
        });

        // Check the gap between the room open time and the first reservation
        Object[] firstReservation = existingReservations.get(0);
        if (roomOpenTime < (long) firstReservation[0]) {
            availableTimes.add(new Object[]{roomOpenTime, (long) firstReservation[0]});
        }

        // Check the gaps between the existing reservations
        for (int i = 0; i < existingReservations.size() - 1; i++) {
            Object[] currentReservation = existingReservations.get(i);
            Object[] nextReservation = existingReservations.get(i+1);
            if ((long) nextReservation[0] > (long) currentReservation[1]) {
                availableTimes.add(new Object[]{(long) currentReservation[1], (long) nextReservation[0]});
            }
        }

        // Check the gap between the last reservation and the room close time
        Object[] lastReservation = existingReservations.get(existingReservations.size() - 1);
        if ((long) lastReservation[1] < roomCloseTime) {
            availableTimes.add(new Object[]{(long) lastReservation[1], roomCloseTime});
        }

        return availableTimes;
    }
}
