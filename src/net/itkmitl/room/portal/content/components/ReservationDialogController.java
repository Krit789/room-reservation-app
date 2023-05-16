package net.itkmitl.room.portal.content.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.util.FewFile;
import net.itkmitl.room.libs.phatsanphon.date.DateTime;
import net.itkmitl.room.libs.phatsanphon.entity.Reservation;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.phatsanphon.repository.ReservationRepository;
import net.itkmitl.room.libs.store.AppStore;
import net.itkmitl.room.portal.content.MainContentView;

public class ReservationDialogController implements ActionListener, DateChangeListener, WindowListener, ChangeListener, ItemListener {
    public ReservationDialog view;
    private final Room myRoom;
    private ArrayList<Reservation> resList;
    private ArrayList<ReservableEntity> availableTime;
    private DateTime virtualReservationStart;
    private DateTime virtualReservationEnd;

    public ReservationDialogController(JFrame parent, Room room) {
        myRoom = room;
        view = new ReservationDialog(parent, room);
        dataFetch();
        view.segmentBox.setEnabled(false);
        view.okButton.addActionListener(this);
        view.cancelButton.addActionListener(this);
        view.addWindowListener(this);
        view.reservationDatePicker.addDateChangeListener(this);
        view.lengthSpinner.addChangeListener(this);
        view.segmentBox.addItemListener(this);
    }

    public void dataFetch() {
        SwingWorker<?, ?> worker = new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() {
                try {
                    FewQuery db = LaewTaeDB.getDB();
                    ReservationRepository rsvprp = new ReservationRepository(db);
                    Long currentTime = System.currentTimeMillis();
                    resList = rsvprp.getReservationsByRoomIdAndTimeRange(myRoom.getId(), currentTime - (86400L * 1000), (currentTime + (86400L * 1000 * 8)));
                    availableTime = getAvailableTimes(resList, myRoom, 7);
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

    public ArrayList<ReservableEntity> getAvailableTimes(ArrayList<Reservation> resList, Room room, int dayLimit) {
        ArrayList<ReservableEntity> availableTimes = new ArrayList<>();
        DateTime currentTime = new DateTime(((int) (System.currentTimeMillis() / 1000L)) * 1000L);
        currentTime.setSeconds(0);
        currentTime.setMinutes(room.getOpenTime().getMinutes());

        DateTime limitTime = new DateTime(System.currentTimeMillis() + (86400L * dayLimit) * 1000L);
        limitTime.setHours(room.getCloseTime().getHours());
        limitTime.setMinutes(room.getCloseTime().getMinutes());
        limitTime.setSeconds(0);

        HashMap<Long, Reservation> resTimeMap = new HashMap<>();
        for (Reservation r : resList) {
            if (!r.isCancelled()) {
                resTimeMap.put(r.getStartTime().getTime(), r);
            }
        }

        for (DateTime currentTimeMilis = currentTime; currentTimeMilis.getTime() <= limitTime.getTime(); currentTimeMilis.setTime(currentTimeMilis.getTime() + (1800L * 1000L))) {
            boolean unavailable = false;
            for (Reservation r : resTimeMap.values()) {
                virtualReservationStart = r.getStartTime();
                virtualReservationEnd = r.getEndTime();


                if (currentTimeMilis.getDate() == r.getStartTime().getDate() && (currentTimeMilis.getTime() >= virtualReservationStart.getTime() && currentTimeMilis.getTime() < virtualReservationEnd.getTime())) {
                    unavailable = true;
                }
//                System.out.println(String.format("%s: %s %s", currentTimeMilis,currentTimeMilis.getTime() >= virtualReservationStart.getTime(), currentTimeMilis.getTime() < virtualReservationEnd.getTime()));
            }
            if (!unavailable &&
                    (currentTimeMilis.addMillis(1800L * 1000L).getHours() < room.getCloseTime().getHours() ||
                            (currentTimeMilis.addMillis(1800L * 1000L).getHours() == room.getCloseTime().getHours() &&
                                    currentTimeMilis.addMillis(1800L * 1000L).getMinutes() <= room.getCloseTime().getMinutes()))

                    && (currentTimeMilis.getHours() > room.getOpenTime().getHours() ||
                    (currentTimeMilis.getHours() == room.getOpenTime().getHours() &&
                            currentTimeMilis.getMinutes() >= room.getOpenTime().getMinutes()))) {

                availableTimes.add(new ReservableEntity(new DateTime(currentTimeMilis.getTime()), new DateTime(currentTimeMilis.getTime() + 1800L * 1000L)));
            } else if (unavailable) {
//                System.out.println("Unavailable Time (" + room.getName() + ")" + currentTimeMilis + " : " + (currentTimeMilis.getTime() >= virtualReservationStart.getTime()) + " && " + (currentTimeMilis.getTime() <= virtualReservationEnd.getTime()));
//                System.out.println("(" + currentTimeMilis.getTime()  + " >= " + virtualReservationStart.getTime() + ") && (" + currentTimeMilis.getTime() + " < " + virtualReservationEnd.getTime()+")");
            }
        }
        return availableTimes;
    }


    public boolean checkAvailableTime(ArrayList<Reservation> resList, DateTime timeToCheck, double hourAhead, Room room) {

        HashMap<Long, Reservation> resTimeMap = new HashMap<>();
        for (Reservation r : resList) {
            if (!r.isCancelled() && r.getStartTime().getDate() == timeToCheck.getDate()) {
                resTimeMap.put(r.getStartTime().getTime(), r);
            }
        }
        boolean unavailable = false;
        for (Reservation r : resTimeMap.values()) {
            DateTime ttc = new DateTime(timeToCheck.getTime());
            DateTime virtualReservationStart = r.getStartTime();
            DateTime virtualReservationEnd = r.getEndTime();

            for (int i = 0; i < (int) (hourAhead * 2); i++) {
                if (ttc.getDate() == virtualReservationStart.getDate() && ttc.getTime() >= virtualReservationStart.getTime() && ttc.getTime() < virtualReservationEnd.getTime()) {
                    unavailable = true;
                    System.out.println("Checked: " + ttc + " " + " Result: " + unavailable + " ResID: " + r.getId());
                    System.out.println((ttc.getTime() >= virtualReservationStart.getTime()) + " " + (ttc.getTime() <= virtualReservationEnd.getTime()));
                }
                long addedTime = 1800L * 1000;
                ttc.setTime(ttc.getTime() + addedTime);
            }
        }
        timeToCheck.setTime(timeToCheck.getTime() + (((int) (hourAhead * 2)) * 1800L * 1000));
        if (timeToCheck.getHours() < room.getOpenTime().getHours() || timeToCheck.getHours() > room.getCloseTime().getHours() || (timeToCheck.getHours() == room.getCloseTime().getHours() && timeToCheck.getMinutes() > room.getCloseTime().getMinutes())) {
            unavailable = true;
        }
        System.out.println((timeToCheck.getHours() + " < " + room.getOpenTime().getHours()) + " || " + timeToCheck.getHours() + " > " + room.getCloseTime().getHours() + " || (" + timeToCheck.getHours() + " == " + room.getCloseTime().getHours() + " && " + timeToCheck.getMinutes() + " > " + room.getCloseTime().getMinutes() + ")");
        System.out.println((timeToCheck.getHours() < room.getOpenTime().getHours()) + " || " + (timeToCheck.getHours() > room.getCloseTime().getHours()) + " || (" + (timeToCheck.getHours() == room.getCloseTime().getHours()) + " && " + (timeToCheck.getMinutes() > room.getCloseTime().getMinutes()) + ")");

        return unavailable;
    }


    private void updateReservationSegment() {
        view.okButton.setEnabled(false);
        view.segmentLoadingLabel.setIcon(FewFile.getImage("icons/loading-16px.gif"));
        view.segmentBox.removeAllItems();
        view.segmentBox.setEnabled(false);
        for (ReservableEntity re : availableTime) {
            if (re.begin.getDate() == view.reservationDatePicker.getDate().getDayOfMonth()) {
                view.segmentBox.addItem(re);
            }
        }
        if (view.segmentBox.getItemCount() == 0) {
            view.segmentLoadingLabel.setIcon(FewFile.getImage("icons/info-16px.png"));
            view.segmentLoadingLabel.setToolTipText("No reservation available on selected date");
        } else {
            view.segmentBox.setEnabled(true);
            view.segmentLoadingLabel.setIcon(FewFile.getImage("icons/yes-16px.png"));
            view.segmentLoadingLabel.setToolTipText("Fetch Successful");
            view.okButton.setEnabled(true);
        }
        validateTime();
    }

    @Override
    public void dateChanged(DateChangeEvent event) {
        updateReservationSegment();
    }

    public static void disableGlassPane() {
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
            protected Object doInBackground() {
                try {
                    FewQuery db = LaewTaeDB.getDB();
                    ReservationRepository rsvprp = new ReservationRepository(db);
                    Reservation myRsvp = new Reservation();
                    myRsvp.setReservationTime(new DateTime(System.currentTimeMillis()));
                    myRsvp.setStartTime(segment.begin);

                    DateTime endTime = new DateTime(segment.begin.getTime() + (1800L * 1000L * Double.valueOf(2 * ((Number) view.lengthSpinner.getValue()).doubleValue()).longValue()));
                    myRsvp.setEndTime(endTime);
                    myRsvp.setUser(((User) AppStore.getAppStore().select("user")));
                    System.out.println(myRsvp.getUser().getId() + " " + myRsvp.getUser().getFirstname());
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

    @Override
    public void stateChanged(ChangeEvent e) {
        validateTime();
    }

    private void validateTime() {
        DateTime myTime = ((ReservableEntity) Objects.requireNonNull(view.segmentBox.getSelectedItem())).begin;
        if (checkAvailableTime(resList, new DateTime(myTime.getTime()), ((Number) view.lengthSpinner.getValue()).doubleValue(), myRoom)) {
            view.okButton.setEnabled(false);
            view.lengthSpinner.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 50, 100)));
        } else {
            view.okButton.setEnabled(true);
            view.lengthSpinner.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(44, 84, 144)));
        }
    }

    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            validateTime();
        }
    }
}
