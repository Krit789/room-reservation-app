package net.itkmitl.room.portal.content.components;

import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.date.DateTime;
import net.itkmitl.room.libs.phatsanphon.entity.Reservation;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.phatsanphon.repository.ReservationRepository;
import net.itkmitl.room.libs.store.AppStore;
import net.itkmitl.room.portal.content.MainContentView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ReservationDialogController implements ActionListener, DateChangeListener, WindowListener {
    public ReservationDialog view;
    private Room myRoom;
    private ArrayList<Object[]> allReservedTime = new ArrayList<>();
    private ArrayList<ReservableEntity> availableTime;

    public ReservationDialogController(JFrame parent, Room room) {
        myRoom = room;
        view = new ReservationDialog(parent, room);
        view.segmentBox.setEnabled(false);
        view.okButton.addActionListener(this);
        view.cancelButton.addActionListener(this);
        view.addWindowListener(this);
        dataFetch();
        view.reservationDatePicker.addDateChangeListener(this);
    }

    public void dataFetch() {
        SwingWorker<?, ?> worker = new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() {
                try {
                    FewQuery db = LaewTaeDB.getDB();
                    ReservationRepository rsvprp = new ReservationRepository(db);
                    Long currentTime = System.currentTimeMillis();
                    ArrayList<Reservation> resList = rsvprp.getReservationsByRoomIdAndTimeRange(myRoom.getId(), currentTime, (currentTime + TimeUnit.DAYS.toMillis(7)));
                    for (Reservation r : resList) {
                        allReservedTime.add(new Object[]{r.getStartTime().getTime(), r.getEndTime().getTime()});
                        System.out.println(r.getStartTime() + " " + r.getEndTime());
                    }
                    availableTime = getAvailableTimes(resList, myRoom.getOpenTime(), myRoom.getCloseTime(), 7);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }

            @Override
            protected void done() {
                updateReservationSegment();
                view.segmentBox.setEnabled(true);
                view.okButton.setEnabled(true);
            }
        };
        worker.execute();
    }

    public static ArrayList<ReservableEntity> getAvailableTimes(ArrayList<Reservation> resList, DateTime openTime, DateTime closeTime, int dayLimit) {
        ArrayList<ReservableEntity> availableTimes = new ArrayList<>();
        DateTime currentTime = new DateTime(System.currentTimeMillis());
        currentTime.setSeconds(0);
//        currentTime.setHours(openTime.getHours());
        currentTime.setMinutes(openTime.getMinutes());

        DateTime limitTime = new DateTime(System.currentTimeMillis() + (86400L * dayLimit) * 1000L);
        limitTime.setHours(closeTime.getHours());
        limitTime.setMinutes(closeTime.getMinutes());

        HashMap<Long, Reservation> resTimeMap = new HashMap<>();
        for (Reservation r : resList) {
            resTimeMap.put(r.getStartTime().getTime(), r);
        }

        for (DateTime currentTimeMilis = currentTime; currentTimeMilis.getTime() <= limitTime.getTime(); currentTimeMilis.setTime(currentTimeMilis.getTime() + (3600L * 1000L))) {
            boolean unavailable = false;
            for (Reservation r : resTimeMap.values()) {
                if (currentTimeMilis.getDate() == r.getStartTime().getDate() &&
                        currentTimeMilis.getHours() == r.getStartTime().getHours()
                ) {
                    unavailable = true;
                    currentTimeMilis.setTime(currentTimeMilis.getTime() + (r.getEndTime().getTime() - r.getStartTime().getTime()));
                }
            }
//            System.out.println(String.format("%s, %s && %s && %s && %s", unavailable, currentTimeMilis.addMillis(3600L * 1000L).getHours() <= closeTime.getHours(), currentTimeMilis.addMillis(3600L * 1000L).getMinutes() <= closeTime.getMinutes(), currentTimeMilis.getHours() >= openTime.getHours(), currentTimeMilis.getMinutes() >= openTime.getMinutes()));
            if (!unavailable && currentTimeMilis.addMillis(3600L * 1000L).getHours() <= closeTime.getHours() && currentTimeMilis.getHours() >= openTime.getHours()) {
                availableTimes.add(new ReservableEntity(new DateTime(currentTimeMilis.getTime()), new DateTime(currentTimeMilis.getTime() + 3600L * 1000L)));
            }
        }
        return availableTimes;
    }

    private void updateReservationSegment() {
        view.okButton.setEnabled(false);
        view.segmentLoadingLabel.setIcon(new ImageIcon("resource/icons/loading-16px.gif"));
        view.segmentBox.removeAllItems();
        view.segmentBox.setEnabled(false);
        for (ReservableEntity re : availableTime) {
            if (re.begin.getDate() == view.reservationDatePicker.getDate().getDayOfMonth()) {
                view.segmentBox.addItem(re);
            }
        }
        if (view.segmentBox.getItemCount() == 0) {
            view.segmentLoadingLabel.setIcon(new ImageIcon("resource/icons/info-16px.png"));
            view.segmentLoadingLabel.setToolTipText("No reservation available on selected date");
        } else {
            view.segmentBox.setEnabled(true);
            view.segmentLoadingLabel.setIcon(new ImageIcon("resource/icons/yes-16px.png"));
            view.segmentLoadingLabel.setToolTipText("Fetch Successful");
            view.okButton.setEnabled(true);
        }
    }

    @Override
    public void dateChanged(DateChangeEvent event) {
        updateReservationSegment();
    }

    private void disableGlassPane() {
        MainContentView.glassPane.setVisible(false);
        MainContentView.glassPane.setEnabled(false);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        disableGlassPane();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    public void makeReservation(ReservableEntity segment) {
        SwingWorker<?, ?> worker = new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() throws Exception {
                try {
                    FewQuery db = LaewTaeDB.getDB();
                    ReservationRepository rsvprp = new ReservationRepository(db);
                    Reservation myRsvp = new Reservation();
                    myRsvp.setReservationTime(new DateTime(System.currentTimeMillis()));
                    myRsvp.setStartTime(segment.begin);
                    myRsvp.setEndTime(segment.end);
                    myRsvp.setUser(((User) AppStore.getAppStore().select("user")));
                    myRsvp.setRoom(myRoom);
                    myRsvp.setReason(view.reasonTextArea.getText());
                    rsvprp.createReservation(myRsvp);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

                return null;
            }

            @Override
            protected void done() {
                JOptionPane.showMessageDialog(null, "Reservation Successful!", "Reservation", JOptionPane.INFORMATION_MESSAGE);
            }

        };
        worker.execute();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.okButton)) {
            makeReservation((ReservableEntity) view.segmentBox.getSelectedItem());
            view.dispose();
            disableGlassPane();

        } else if (e.getSource().equals(view.cancelButton)) {
            view.dispose();
            disableGlassPane();
        }
    }
}
