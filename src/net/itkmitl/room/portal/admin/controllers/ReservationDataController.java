package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.libs.jarukrit.ProgramError;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.date.DateTime;
import net.itkmitl.room.libs.phatsanphon.entity.Reservation;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.phatsanphon.repository.ReservationRepository;
import net.itkmitl.room.libs.phatsanphon.repository.RoomRepository;
import net.itkmitl.room.libs.phatsanphon.repository.UserRepository;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.admin.components.DatabaseLoader;
import net.itkmitl.room.portal.admin.models.RoomComboBoxModel;
import net.itkmitl.room.portal.admin.models.UserComboBoxModel;
import net.itkmitl.room.portal.admin.views.ReservationDataView;
import net.itkmitl.room.portal.components.LoadingDialog;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;

public class ReservationDataController implements ActionListener, InternalFrameListener, ItemListener, ChangeListener {
    public final ReservationDataView view;
    private Reservation reservation;
    private Object[] data;
    private final int mode;
    private final ArrayList<UserComboBoxModel> userArrayList = new ArrayList<>();
    private final ArrayList<RoomComboBoxModel> roomArrayList = new ArrayList<>();
    private final DataListEditableController dlec;

    public ReservationDataController(int mode, int reservationID, DataListEditableController dlec) {
        this.mode = mode;
        this.dlec = dlec;
        view = new ReservationDataView();
        try {
            reservation = new Reservation();
            switch (mode) {
                case 0: // Just Load
                    view.getFrame().setTitle("View Reservation");
                    view.pageTitle.setText("Reservation Records Viewer");
                    view.getFrame().pack();
                    view.saveButton.addActionListener(this);
                    view.cancelButton.addActionListener(this);
                    databaseLoader(reservationID);
                    break;
                case 1: // Update
                    view.getFrame().setTitle("Update Reservation");
                    view.pageTitle.setText("Reservation Records Update");
                    view.getFrame().pack();
                    view.saveButton.addActionListener(this);
                    view.cancelButton.addActionListener(this);
                    view.roomIDSelect.addItemListener(this);
                    view.startTimeHourField.addChangeListener(this);
                    view.endTimeHourField.addChangeListener(this);
                    view.startTimeMinuteField.addChangeListener(this);
                    view.endTimeMinuteField.addChangeListener(this);
                    databaseLoader(reservationID);
                    break;
                case 2: // Create
                    view.saveButton.addActionListener(this);
                    view.cancelButton.addActionListener(this);
                    view.getFrame().setTitle("Reservation Creation");
                    view.pageTitle.setText("Reservation Records Creator");
                    view.pageSubtitle.setText("Create new reservation records");
                    view.saveButton.setText("Create");
                    view.roomIDSelect.addItemListener(this);
                    view.startTimeHourField.addChangeListener(this);
                    view.endTimeHourField.addChangeListener(this);
                    view.startTimeMinuteField.addChangeListener(this);
                    view.endTimeMinuteField.addChangeListener(this);
                    dataPopulator();
                    break;
                case 3: // Delete
                    reservation.setId(reservationID);
                    databaseCommiter(reservation, 1);
                    break;
            }
            view.getFrame().addInternalFrameListener(this);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(BaseWindow.baseFrame, ex.toString(), "Database Query Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void databaseLoader(int reservationID) {
        SwingWorker worker = new SwingWorker() {
            final LoadingDialog ld = new LoadingDialog();

            @Override
            protected String doInBackground() {
                String errorMessage;
                ld.dialog.setVisible(true);
                BaseWindow.progressBar.setIndeterminate(true);


                try {
                    FewQuery db = RVDB.getDB();
                    ReservationRepository reservationRepository = new ReservationRepository(db);
                    UserRepository userRepository = new UserRepository(db);
                    RoomRepository roomRepository = new RoomRepository(db);
                    Reservation myReservation = reservationRepository.getReservationById(reservationID);
                    switch (mode) {
                        case 0: // View Only
                            userArrayList.add(new UserComboBoxModel(myReservation.getUser()));
                            roomArrayList.add(new RoomComboBoxModel(myReservation.getRoom()));
                            break;
                        default:
                            ArrayList<User> user_list = userRepository.getUsers();
                            ArrayList<Room> room_list = roomRepository.getRooms();
                            for (User u : user_list) {
                                userArrayList.add(new UserComboBoxModel(u));
                            }
                            for (Room r : room_list) {
                                roomArrayList.add(new RoomComboBoxModel(r));
                            }
                            break;
                    }

                    data = new Object[]{true, myReservation};
                } catch (Exception ex) {
                    errorMessage = ex.getMessage();
                    data = new Object[]{false, errorMessage};
                }
                return "";
            }

            @Override
            protected void done() {
                dataLoader();
                ld.dialog.dispose();
                BaseWindow.progressBar.setIndeterminate(false);
            }
        };
        worker.execute();
    }

    public void databaseCommiter(Reservation reservation, int mode) {
        SwingWorker worker = new SwingWorker() {
            LoadingDialog ld = new LoadingDialog();

            @Override
            protected String doInBackground() {
                String errorMessage;
                ld.dialog.setVisible(true);
                BaseWindow.progressBar.setIndeterminate(true);
                try {
                    FewQuery db = RVDB.getDB();
                    ReservationRepository myReservation = new ReservationRepository(db);
                    switch (mode) {
                        case 0: // Update
                            try {
                                myReservation.updateReservation(reservation);
                                data = new Object[]{true, myReservation};
                            } catch (Exception ex) {
                                errorMessage = ProgramError.getStackTrace(ex);
                                data = new Object[]{false, errorMessage};
                            }
                            break;
                        case 1: // Delete
                            try {
                                myReservation.deleteReservationById(reservation.getId());
                                data = new Object[]{true, myReservation};
                            } catch (Exception ex) {
                                errorMessage = ProgramError.getStackTrace(ex);
                                data = new Object[]{false, errorMessage};
                            }
                            break;
                        case 2: // Create
                            try {
                                myReservation.createReservation(reservation);
                                data = new Object[]{true, myReservation};
                            } catch (Exception ex) {
                                errorMessage = ProgramError.getStackTrace(ex);
                                data = new Object[]{false, errorMessage};
                            }
                            break;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(BaseWindow.baseFrame, ProgramError.getStackTrace(ex), "Database Query Error", JOptionPane.ERROR_MESSAGE);
                }

                return "";
            }

            @Override
            protected void done() {
                ld.dialog.dispose();
                BaseWindow.progressBar.setIndeterminate(false);
                if (mode != 1) {
                    view.getFrame().dispose();
                }
                dlec.reloadData();
            }
        };
        worker.execute();
    }

    public void dataPopulator() {
        SwingWorker worker = new SwingWorker() {
            LoadingDialog ld = new LoadingDialog();

            @Override
            protected String doInBackground() {
                String errorMessage;
                ld.dialog.setVisible(true);
                BaseWindow.progressBar.setIndeterminate(true);


                try {
                    FewQuery db = RVDB.getDB();
                    UserRepository userRepository = new UserRepository(db);
                    RoomRepository roomRepository = new RoomRepository(db);
                    ArrayList<User> userList = userRepository.getUsers();
                    ArrayList<Room> roomList = roomRepository.getRooms();
                    for (User u : userList) {
                        userArrayList.add(new UserComboBoxModel(u));
                    }
                    for (Room r : roomList) {
                        roomArrayList.add(new RoomComboBoxModel(r));
                    }
                    data = new Object[]{true, null};
                } catch (Exception ex) {
                    errorMessage = ex.getMessage();
                    data = new Object[]{false, errorMessage};
                }
                return "";
            }

            @Override
            protected void done() {
                for (UserComboBoxModel u : userArrayList) {
                    view.userIDSelect.addItem(u);
                }
                for (RoomComboBoxModel r : roomArrayList) {
                    view.roomIDSelect.addItem(r);
                }
                ld.dialog.dispose();
                BaseWindow.progressBar.setIndeterminate(false);
            }
        };
        worker.execute();
    }

    private void dataLoader() {
        if ((boolean) data[0]) {
            Reservation myReservation = (Reservation) data[1];
            reservation = (Reservation) data[1];
            view.pageSubtitle.setText("Reservation records for Reservation ID " + myReservation.getId());
            view.idField.setText(reservation.getId() + "");
            for (UserComboBoxModel u : userArrayList) {
                view.userIDSelect.addItem(u);
                if (u.getUser().getId() == reservation.getUser().getId()) {
                    view.userIDSelect.setSelectedItem(u);
                }
            }
            for (RoomComboBoxModel r : roomArrayList) {
                view.roomIDSelect.addItem(r);
                if (r.getRoom().getId() == reservation.getRoom().getId()) {
                    view.roomIDSelect.setSelectedItem(r);
                }
            }

            view.reasonField.setText(reservation.getReason());
            view.startTimeHourField.getModel().setValue(reservation.getStartTime().getHours());
            view.startTimeMinuteField.getModel().setValue(reservation.getStartTime().getMinutes());
            view.endTimeHourField.getModel().setValue(reservation.getEndTime().getHours());
            view.endTimeMinuteField.getModel().setValue(reservation.getEndTime().getMinutes());

            view.reservationTimeField.setText(reservation.getReservationTime().toString());
            view.cancelledCheckbox.setSelected(reservation.isCancelled());

            switch (mode) {
                case 0: // View Mode
                    view.userIDSelect.setEnabled(false);
                    view.roomIDSelect.setEnabled(false);
                    view.reasonField.setEditable(false);
                    view.startTimeHourField.setEnabled(false);
                    view.startTimeMinuteField.setEnabled(false);
                    view.endTimeHourField.setEnabled(false);
                    view.endTimeMinuteField.setEnabled(false);
                    view.cancelledCheckbox.setEnabled(false);
                    break;
            }
        } else {
            view.getFrame().dispose();
            JOptionPane.showMessageDialog(BaseWindow.baseFrame, data[1].toString(), "Database Query Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.saveButton)) {
            reservation.setUser(((UserComboBoxModel) view.userIDSelect.getSelectedItem()).getUser());
            reservation.setRoom(((RoomComboBoxModel) view.roomIDSelect.getSelectedItem()).getRoom());
            reservation.setReason(view.reasonField.getText());

            DateTime startTime = new DateTime(new Date());
            startTime.setHours((Integer) view.startTimeHourField.getValue());
            startTime.setMinutes((Integer) view.startTimeMinuteField.getValue());

            DateTime endTime = new DateTime(new Date());
            endTime.setHours((Integer) view.endTimeHourField.getValue());
            endTime.setMinutes((Integer) view.endTimeMinuteField.getValue());
            reservation.setStartTime(startTime);
            reservation.setEndTime(endTime);
            reservation.setCancelled(view.cancelledCheckbox.isSelected());

            switch (mode) {
                case 1: // Update
                    databaseCommiter(reservation, 0);
                    break;
                case 2: // Create
                    databaseCommiter(reservation, 2);
                    break;
            }

        } else if (e.getSource().equals(view.cancelButton)) {
            view.getFrame().dispose();
        }
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        BaseWindow.statusLabel.setText(e.getInternalFrame().getTitle() + " was opened.");
        JMenuItem newItem = new JMenuItem(e.getInternalFrame().getTitle());
        BaseWindow.windowList.put(e.getInternalFrame(), newItem);
        newItem.setIcon(e.getInternalFrame().getFrameIcon());
        BaseWindow.windowMenu.add(newItem);
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
        try {
            BaseWindow.windowMenu.remove(BaseWindow.windowList.get(e.getInternalFrame()));
            BaseWindow.windowList.remove(e.getInternalFrame());
            System.out.println("Removal Success: " + e.getInternalFrame().getTitle() + " " + e.getInternalFrame().getClass().getCanonicalName() + " " + this.getClass().getSimpleName());
        } catch (Exception ex) {
            System.out.println("Removal Failure: " + e.getInternalFrame().getTitle() + " " + e.getSource().toString() + " " + e.getInternalFrame().getClass().getCanonicalName() + " " + this.getClass().getSimpleName());
            ex.printStackTrace();
        }
    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource().equals(view.roomIDSelect) && e.getStateChange() == ItemEvent.SELECTED) {
            Room myRoom = ((RoomComboBoxModel) view.roomIDSelect.getSelectedItem()).getRoom();
            int openHour = (Integer) view.startTimeHourField.getValue();
            SpinnerNumberModel startModel = (SpinnerNumberModel) view.startTimeHourField.getModel();
            startModel.setMinimum(myRoom.getOpenTime().getHours());
            startModel.setMaximum(myRoom.getCloseTime().getHours());
            view.startTimeHourField.setValue(Math.max((Integer) view.startTimeHourField.getValue(), myRoom.getOpenTime().getHours()));

            if (openHour < myRoom.getOpenTime().getHours() || openHour > myRoom.getCloseTime().getHours()) {
                if (((Integer) view.startTimeMinuteField.getValue()) < myRoom.getOpenTime().getMinutes()) {
                    view.startTimeMinuteField.getModel().setValue(myRoom.getOpenTime().getMinutes());
                }
                view.startTimeHourField.getModel().setValue(myRoom.getOpenTime().getHours());

            } else {
                view.startTimeHourField.getModel().setValue(openHour);
            }
            int endHour = (Integer) view.endTimeHourField.getValue();
            SpinnerNumberModel endModel = (SpinnerNumberModel) view.endTimeHourField.getModel();
            endModel.setMinimum(myRoom.getOpenTime().getHours());
            endModel.setMaximum(myRoom.getCloseTime().getHours());
            view.endTimeHourField.setValue(Math.min((Integer) view.endTimeHourField.getValue(), myRoom.getCloseTime().getHours()));

            if (endHour > myRoom.getCloseTime().getHours() || endHour < myRoom.getOpenTime().getHours()) {
                if ((Integer) view.endTimeMinuteField.getValue() > myRoom.getCloseTime().getMinutes()) {
                    view.endTimeMinuteField.getModel().setValue(myRoom.getCloseTime().getMinutes());
                }
                view.endTimeHourField.getModel().setValue(myRoom.getCloseTime().getHours());
            } else {
                view.endTimeHourField.getModel().setValue(endHour);
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Room myRoom = ((RoomComboBoxModel) view.roomIDSelect.getSelectedItem()).getRoom();
        if (e.getSource().equals(view.startTimeHourField) || e.getSource().equals(view.startTimeMinuteField)) {

            assert myRoom != null;
            SpinnerNumberModel startModel = (SpinnerNumberModel) view.startTimeMinuteField.getModel();
            if ((Integer) view.startTimeHourField.getValue() == myRoom.getOpenTime().getHours()) {
                startModel.setMinimum(myRoom.getOpenTime().getMinutes());
                startModel.setMaximum(59);
                view.startTimeMinuteField.setValue(Math.max((Integer) view.startTimeMinuteField.getValue(), myRoom.getOpenTime().getMinutes()));

                SpinnerNumberModel endModel = (SpinnerNumberModel) view.endTimeMinuteField.getModel();
                endModel.setMinimum((Integer) view.startTimeMinuteField.getValue());
                endModel.setMaximum(59);
                view.endTimeMinuteField.setValue(Math.max((Integer) view.endTimeMinuteField.getValue(), (Integer) view.startTimeMinuteField.getValue()));
                if ((Integer) view.startTimeHourField.getValue() > (Integer) view.endTimeHourField.getValue() ||
                        ((Integer) view.startTimeHourField.getValue() == (Integer) view.endTimeHourField.getValue() &&
                                (Integer) view.startTimeMinuteField.getValue() > (Integer) view.endTimeMinuteField.getValue())) {
                    view.startTimeHourField.setValue(view.endTimeHourField.getValue());
                    view.startTimeMinuteField.setValue(view.endTimeMinuteField.getValue());
                }
            } else if ((Integer) view.startTimeHourField.getValue() == myRoom.getCloseTime().getHours()) {
                startModel.setMinimum(0);
                startModel.setMaximum((Integer) view.endTimeMinuteField.getValue());
                view.startTimeMinuteField.setValue(Math.min((Integer) view.endTimeMinuteField.getValue(), (Integer) view.startTimeMinuteField.getValue()));
            } else {
                startModel.setMinimum(0);
                startModel.setMaximum(59);
            }
        } else if (e.getSource().equals(view.endTimeHourField) || (e.getSource().equals(view.endTimeMinuteField))) {
            assert myRoom != null;
            SpinnerNumberModel endModel = (SpinnerNumberModel) view.endTimeMinuteField.getModel();
            if ((Integer) view.endTimeHourField.getValue() == myRoom.getCloseTime().getHours()) {
                endModel.setMinimum(0);
                endModel.setMaximum(myRoom.getCloseTime().getMinutes());
                view.endTimeMinuteField.setValue(Math.min((Integer) view.endTimeMinuteField.getValue(), myRoom.getCloseTime().getMinutes()));

                if ((Integer) view.startTimeHourField.getValue() == (Integer) view.endTimeHourField.getValue()) {
                    SpinnerNumberModel startModel = (SpinnerNumberModel) view.startTimeMinuteField.getModel();
                    startModel.setMaximum((Integer) view.endTimeMinuteField.getValue());
                    view.startTimeMinuteField.setValue(Math.min((Integer) view.endTimeMinuteField.getValue(), (Integer) view.startTimeMinuteField.getValue()));
                }


            } else {
                endModel.setMaximum(59);
            }

            if ((Integer) view.endTimeHourField.getValue() < (Integer) view.startTimeHourField.getValue() ||
                    ((Integer) view.endTimeHourField.getValue() == (Integer) view.startTimeHourField.getValue() &&
                            (Integer) view.endTimeMinuteField.getValue() < (Integer) view.startTimeMinuteField.getValue())) {
                view.endTimeHourField.setValue(view.startTimeHourField.getValue());
                view.endTimeMinuteField.setValue(view.startTimeMinuteField.getValue());
            }
        }
    }

}
