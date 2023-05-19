package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.enums.EnumRoomState;
import net.itkmitl.room.libs.jarukrit.ProgramError;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.simplevalue.FewSimpleValue;
import net.itkmitl.room.libs.phatsanphon.date.DateTime;
import net.itkmitl.room.libs.phatsanphon.entity.Entity;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.libs.phatsanphon.repository.RoomRepository;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.admin.views.RoomDataView;
import net.itkmitl.room.portal.components.LoadingDialog;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class RoomDataController implements ActionListener, InternalFrameListener, ChangeListener, DataManager {
    RoomDataView view;
    Room room;
    Object[] data;
    int mode;
    DataTable dlec;

    public RoomDataController(int mode, int roomID, DataTable dlec) {
        this.mode = mode;
        this.dlec = dlec;
        try {
            view = new RoomDataView();
            room = new Room();
            switch (mode) {
                case 0: // Just Load
                    view.getFrame().setTitle("View Room");
                    view.pageTitle.setText("Room Records Viewer");
                    view.getFrame().pack();
                    view.saveButton.setEnabled(false);
                    view.cancelButton.addActionListener(this);
                    databaseLoader(roomID);
                    break;
                case 1: // Update
                    view.getFrame().setTitle("Update Room");
                    view.pageTitle.setText("Room Records Update");
                    view.getFrame().pack();
                    view.saveButton.addActionListener(this);
                    view.cancelButton.addActionListener(this);
                    view.openTimeHourField.addChangeListener(this);
                    view.openTimeMinuteField.addChangeListener(this);
                    databaseLoader(roomID);
                    break;
                case 2: // Create
                    view.saveButton.addActionListener(this);
                    view.cancelButton.addActionListener(this);
                    view.getFrame().setTitle("Room Creation");
                    view.pageTitle.setText("Room Records Creator");
                    view.pageSubtitle.setText("Create new room records");
                    view.saveButton.setText("Create");
                    view.openTimeHourField.addChangeListener(this);
                    view.openTimeMinuteField.addChangeListener(this);
                    view.getFrame().pack();
                    break;
                case 3: // Delete
                    room.setId(roomID);
                    databaseCommiter(room, 1);
                    break;
            }
            view.getFrame().addInternalFrameListener(this);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(BaseWindow.baseFrame, ex.getMessage(), "Database Query Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void databaseLoader(int roomID) {
        SwingWorker<?, ?> worker = new SwingWorker<>() {
            final LoadingDialog ld = new LoadingDialog();

            @Override
            protected String doInBackground() {
                String errorMessage;
                ld.dialog.setVisible(true);
                BaseWindow.progressBar.setIndeterminate(true);
                try {
                    FewQuery db = LaewTaeDB.getDB();
                    Room myRoom = new RoomRepository(db).getRoomById(roomID);
                    data = new Object[]{true, myRoom};
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

    @Override
    public void databaseCommiter(Entity room, int mode) {
        SwingWorker<?, ?> worker = new SwingWorker<>() {
            final LoadingDialog ld = new LoadingDialog();

            @Override
            protected String doInBackground() {
                String errorMessage;
                ld.dialog.setVisible(true);
                BaseWindow.progressBar.setIndeterminate(true);
                try {
                    FewQuery db = LaewTaeDB.getDB();
                    RoomRepository myRoom = new RoomRepository(db);
                    switch (mode) {
                        case 0: // Update
                            try {
                                myRoom.updateRoom((Room) room);
                                data = new Object[]{true, myRoom};
                            } catch (Exception ex) {
                                errorMessage = ProgramError.getStackTrace(ex);
                                data = new Object[]{false, errorMessage};
                            }
                            break;
                        case 1: // Delete
                            try {
                                myRoom.deleteRoomById(((Room) room).getId());
                                data = new Object[]{true, myRoom};
                            } catch (Exception ex) {
                                errorMessage = ProgramError.getStackTrace(ex);
                                data = new Object[]{false, errorMessage};
                            }
                            break;
                        case 2: // Create
                            try {
                                myRoom.createRoom((Room) room);
                                data = new Object[]{true, myRoom};
                            } catch (Exception ex) {
                                errorMessage = ProgramError.getStackTrace(ex);
                                data = new Object[]{false, errorMessage};
                            }
                            break;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(BaseWindow.baseFrame, ex.toString(), "Database Query Error", JOptionPane.ERROR_MESSAGE);
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

    @Override
    public void dataLoader() {
        if (new FewSimpleValue(data[0]).asBoolean()) {
            Room myUser = (Room) data[1];
            room = (Room) data[1];
            view.pageSubtitle.setText("Room records for Room ID " + myUser.getId());
            view.idField.setText(String.valueOf(room.getId()));
            view.nameField.setText(room.getName());
            view.capacitySpinner.setModel(new SpinnerNumberModel(room.getCapacity(), 0, 65535, 1));
            view.buildingField.setText(room.getBuilding());
            view.floorField.setText(room.getFloor());

            view.openTimeHourField.getModel().setValue(room.getOpenTime().getHours());
            view.openTimeMinuteField.getModel().setValue(room.getOpenTime().getMinutes());
            view.closeTimeHourField.getModel().setValue(room.getCloseTime().getHours());
            view.closeTimeMinuteField.getModel().setValue(room.getCloseTime().getMinutes());

            if (room.getState().equals(EnumRoomState.AVAILABLE)) {
                view.stateSelect.setSelectedIndex(0);
            } else if (room.getState().equals(EnumRoomState.UNAVAILABLE)) {
                view.stateSelect.setSelectedIndex(1);
            } else if (room.getState().equals(EnumRoomState.MAINTENANCE)) {
                view.stateSelect.setSelectedIndex(2);
            }

            if (mode == 0) {
                view.nameField.setEnabled(false);
                view.capacitySpinner.setEnabled(false);
                view.buildingField.setEnabled(false);
                view.floorField.setEnabled(false);
                view.stateSelect.setEnabled(false);
                view.openTimeHourField.setEnabled(false);
                view.openTimeMinuteField.setEnabled(false);
                view.closeTimeHourField.setEnabled(false);
                view.closeTimeMinuteField.setEnabled(false);
            }
            view.getFrame().pack();
        } else {
            view.getFrame().dispose();
            JOptionPane.showMessageDialog(BaseWindow.baseFrame, data[1].toString(), "Database Query Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.saveButton)) {
            room.setName(view.nameField.getText());
            room.setCapacity((Integer) view.capacitySpinner.getValue());
            room.setBuilding(view.buildingField.getText());
            room.setFloor(view.floorField.getText());
            switch (view.stateSelect.getSelectedIndex()) {
                case 0:
                    room.setState(EnumRoomState.AVAILABLE);
                    break;
                case 1:
                    room.setState(EnumRoomState.UNAVAILABLE);
                    break;
                case 2:
                    room.setState(EnumRoomState.MAINTENANCE);
                    break;
            }
            DateTime openTime = new DateTime(new Date());
            openTime.setHours((Integer) view.openTimeHourField.getValue());
            openTime.setMinutes((Integer) view.openTimeMinuteField.getValue());

            DateTime closeTime = new DateTime(new Date());
            closeTime.setHours((Integer) view.closeTimeHourField.getValue());
            closeTime.setMinutes((Integer) view.closeTimeMinuteField.getValue());
            room.setOpenTime(openTime);
            room.setCloseTime(closeTime);

            switch (mode) {
                case 1: // Update
                    databaseCommiter(room, 0);
                    break;
                case 2: // Create
                    databaseCommiter(room, 2);
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
//            System.out.println("Removal Success: " + e.getInternalFrame().getTitle() + " " + e.getInternalFrame().getClass().getCanonicalName() + " " + this.getClass().getSimpleName());
        } catch (Exception ex) {
            System.out.println("Removal Failure: " + e.getInternalFrame().getTitle() + " " + e.getSource().toString() + " " + e.getInternalFrame().getClass().getCanonicalName() + " " + this.getClass().getSimpleName());
//            ex.printStackTrace();
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
    public void stateChanged(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        int value = (int) spinner.getValue();
        int otherValue = (int) view.closeTimeHourField.getValue();
        int otherValue2 = (int) view.closeTimeMinuteField.getValue();

        if (e.getSource().equals(view.openTimeHourField)) {
            SpinnerNumberModel startModel = (SpinnerNumberModel) view.openTimeHourField.getModel();
            startModel.setMaximum(otherValue);
            if ((int) view.openTimeHourField.getValue() > otherValue)
                startModel.setValue(otherValue);

            SpinnerNumberModel endModel = (SpinnerNumberModel) view.closeTimeHourField.getModel();
            endModel.setMinimum((int) view.openTimeHourField.getValue());
            if ((int) view.closeTimeHourField.getValue() < (int) view.openTimeHourField.getValue())
                startModel.setValue(view.openTimeHourField.getValue());

        } else if (e.getSource().equals(view.openTimeMinuteField)) {
            if (value == otherValue) {
                SpinnerNumberModel endModel = (SpinnerNumberModel) view.openTimeMinuteField.getModel();
                endModel.setMaximum(otherValue2);
            } else {
                SpinnerNumberModel endModel = (SpinnerNumberModel) view.openTimeMinuteField.getModel();
                endModel.setMaximum(59);
            }
        }
    }
}
