package net.itkmitl.room.portal.content.components;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.date.DateTime;
import net.itkmitl.room.libs.phatsanphon.entity.Reservation;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.libs.phatsanphon.repository.ReservationRepository;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjuster;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ReservationDialogController {
    public ReservationDialog view;
    private Room myRoom;
    private ArrayList<Object[]> allReservedTime = new ArrayList<>();
    private ArrayList<ReservableEntity> availableTime;
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
                ArrayList<Reservation> resList = rsvprp.getReservationsByRoomIdAndTimeRange(myRoom.getId(), currentTime, (currentTime + TimeUnit.DAYS.toMillis(7)));
                for (Reservation r: resList){
                    allReservedTime.add(new Object[]{r.getStartTime().getTime(), r.getEndTime().getTime()});
                    System.out.println(r.getStartTime() + " " + r.getEndTime());
                }

                availableTime = getAvailableTimes(resList, myRoom.getOpenTime(), myRoom.getCloseTime(), 7);
                for (ReservableEntity re: availableTime){
                    view.segmentBox.addItem(re);
                }
                return null;
            }
            @Override
            protected void done(){

            }
        };
        worker.execute();
    }

    public static ArrayList<ReservableEntity> getAvailableTimes(ArrayList<Reservation> resList, DateTime openTime, DateTime closeTime, int dayLimit) {
        ArrayList<ReservableEntity> availableTimes = new ArrayList<>();
        DateTime currentTime = new DateTime(System.currentTimeMillis());
        currentTime.setHours(openTime.getHours());
        currentTime.setMinutes(openTime.getMinutes());

        DateTime limitTime = new DateTime(System.currentTimeMillis() + (86400L * dayLimit) * 1000L);
        limitTime.setHours(closeTime.getHours());
        limitTime.setMinutes(closeTime.getMinutes());

        HashMap<Long, Reservation> resTimeMap = new HashMap<>();
        for (Reservation r: resList){
            resTimeMap.put(r.getStartTime().getTime(), r);
        }

        // Iterate over each day between the current time and the limit time
        for (DateTime currentTimeMilis = currentTime; currentTimeMilis.getTime() <= limitTime.getTime(); currentTimeMilis.setTime(currentTimeMilis.getTime() + (3600L * 1000L))) {
//            if (currentTimeMilis.getHours() >= closeTime.getHours() && currentTimeMilis.getMinutes() >= closeTime.getMinutes()){
//                currentTimeMilis.setTime(currentTimeMilis.getTime() + (86400L * 1000L));
//                System.out.println(currentTime.getDate());
//            }

            boolean unavailable = false;
            for (Reservation r: resTimeMap.values()) {
                    if (currentTimeMilis.getDate() == r.getStartTime().getDate() &&
                        currentTimeMilis.getHours() == r.getStartTime().getHours()
                    ) {
                        unavailable = true;
                        currentTimeMilis.setTime(currentTimeMilis.getTime() + (r.getEndTime().getTime() - r.getStartTime().getTime()));
                    }
                }
            System.out.println(String.format("%s, %s && %s && %s && %s", unavailable, currentTimeMilis.addMillis(3600L * 1000L).getHours() <= closeTime.getHours(), currentTimeMilis.addMillis(3600L * 1000L).getMinutes() <= closeTime.getMinutes(), currentTimeMilis.getHours() >= openTime.getHours(), currentTimeMilis.getMinutes() >= openTime.getMinutes()));
            if (!unavailable && currentTimeMilis.addMillis(3600L * 1000L).getHours() <= closeTime.getHours() && currentTimeMilis.getHours() >= openTime.getHours()) {
                availableTimes.add(new ReservableEntity(new DateTime(currentTimeMilis.getTime()), new DateTime(currentTimeMilis.getTime() + 3600L * 1000L)));
            }
        }
        return availableTimes;
    }


}
