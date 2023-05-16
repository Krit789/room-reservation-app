package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.admin.components.DatabaseLoader;
import net.itkmitl.room.portal.admin.models.DataListTableModel;
import net.itkmitl.room.portal.admin.views.*;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

public class DataListTableController implements ListSelectionListener, InternalFrameListener, ActionListener {
    public DataListTableView view;
    public DataListTableModel model;
    private int whichTable = 0;

    public DataListTableController() {
        this(new DataListTableModel());
    }

    public DataListTableController(DataListTableModel model) {
        this.model = model;
        if (!(this instanceof DataListEditableController)) {
            this.view = new DataListTableView();
            view.getFrame().setTitle(model.getTitle());
            view.pageTitle.setText(model.getPageTitle());
            view.pageSubtitle.setText(model.getPageSubtitle());
            view.table.setModel(model.getDtm());
            view.viewEntryButton.setEnabled(false);
            view.table.getSelectionModel().addListSelectionListener(this);
            view.getFrame().addInternalFrameListener(this);
            view.reloadButton.addActionListener(this);
            view.viewEntryButton.addActionListener(this);
        }
    }

    public void reloadData() {
        DatabaseLoader dbl = new DatabaseLoader();
        // Search Model
        if (model.getTitle().contains("\" in")) {
            String[] out = model.getTitle().split("\"");
            String query = out[1];
            String type = out[2];
            System.out.println(out[1] + " " + out[2]);
            if (type.contains("user")) {
                whichTable = 1;
            } else if (type.contains("room")) {
                whichTable = 2;
            } else if (type.contains("reservation")) {
                whichTable = 3;
            } else if (type.contains("feedback")) {
                whichTable = 4;
            }
            if (this instanceof DataListEditableController) {
                dbl.databaseLoader(whichTable, Character.getNumericValue(model.getPageTitle().charAt(1)), query, false, this);
                ((DataListEditableController) this).view.viewEntryButton.setEnabled(false);
                ((DataListEditableController) this).view.editSelectedButton.setEnabled(false);
                ((DataListEditableController) this).view.deletedSelectedButton.setEnabled(false);

            } else {
                dbl.databaseLoader(whichTable, Character.getNumericValue(model.getPageTitle().charAt(1)), query, true, this);
                this.view.viewEntryButton.setEnabled(false);

            }
            // Editable Model
        } else {
            if (model.getPageTitle().contains("User")) {
                whichTable = 1;
            } else if (model.getPageTitle().contains("Room")) {
                whichTable = 2;
            } else if (model.getPageTitle().contains("Reservation")) {
                whichTable = 3;
            } else if (model.getPageTitle().contains("Feedback")) {
                whichTable = 4;
            }
            if (this instanceof DataListEditableController) {
                dbl.databaseLoader(whichTable, 99, "", false, this);
                ((DataListEditableController) this).view.viewEntryButton.setEnabled(false);
                ((DataListEditableController) this).view.editSelectedButton.setEnabled(false);
                ((DataListEditableController) this).view.deletedSelectedButton.setEnabled(false);
                ((DataListEditableController) this).view.viewEntryButton.setEnabled(false);
            } else {
                dbl.databaseLoader(whichTable, 99, "", true, this);
                this.view.viewEntryButton.setEnabled(false);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && view.table.getSelectedRow() != -1) {
            view.viewEntryButton.setEnabled(true);
        }
    }

    public void internalFrameOpened(InternalFrameEvent e) {
        BaseWindow.statusLabel.setText(e.getInternalFrame().getTitle() + " was opened.");
        JMenuItem newItem = new JMenuItem(e.getInternalFrame().getTitle());
        BaseWindow.windowList.put(e.getInternalFrame(), newItem);
        newItem.setIcon(e.getInternalFrame().getFrameIcon());
        BaseWindow.windowMenu.add(newItem);
    }

    public void internalFrameClosing(InternalFrameEvent e) {
    }

    public void internalFrameClosed(InternalFrameEvent e) {
        try {
            BaseWindow.windowMenu.remove(BaseWindow.windowList.get(e.getInternalFrame()));
            BaseWindow.windowList.remove(e.getInternalFrame());
//            System.out.println("Removal Success: " + e.getInternalFrame().getTitle() + " " + this.getClass().getSimpleName());
        } catch (Exception ex) {
            System.out.println("Removal Failure: " + e.getInternalFrame().getTitle() + " " + this.getClass().getSimpleName());
//            ex.printStackTrace();
        }
    }

    public void internalFrameIconified(InternalFrameEvent e) {
    }

    public void internalFrameDeiconified(InternalFrameEvent e) {
    }

    public void internalFrameActivated(InternalFrameEvent e) {
    }

    public void internalFrameDeactivated(InternalFrameEvent e) {
    }

    public void spawnUserDataEditor(int mode, int userID, DataListEditableController dlec) throws Exception {
        UserDataController udc = new UserDataController(mode, userID, dlec);
        if (mode != 3) {
            UserDataView view = udc.view;

            Dimension desktopSize = BaseWindow.getDesktop().getSize();
            Dimension jInternalFrameSize = view.getFrame().getSize();
            view.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            view.getFrame().show();
            view.getFrame().setVisible(true);
            BaseWindow.getDesktop().add(view.getFrame());
            view.getFrame().moveToFront();
            try {
                view.getFrame().setSelected(true);
                if (view.getFrame().isIcon()) {
                    view.getFrame().setIcon(false);
                }
                view.getFrame().setSelected(true);
            } catch (PropertyVetoException ex) {

            }
            view.getFrame().requestFocus();
        }
    }

    public void spawnRoomDataEditor(int mode, int roomID, DataListEditableController dlec) {
        RoomDataController rdc = new RoomDataController(mode, roomID, dlec);
        if (mode != 3) {
            RoomDataView view = rdc.view;

            Dimension desktopSize = BaseWindow.getDesktop().getSize();
            Dimension jInternalFrameSize = view.getFrame().getSize();
            view.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            view.getFrame().show();
            view.getFrame().setVisible(true);
            BaseWindow.getDesktop().add(view.getFrame());
            view.getFrame().moveToFront();
            try {
                view.getFrame().setSelected(true);
                if (view.getFrame().isIcon()) {
                    view.getFrame().setIcon(false);
                }
                view.getFrame().setSelected(true);
            } catch (PropertyVetoException ex) {

            }
            view.getFrame().requestFocus();
        }
    }

    public void spawnReservationDataEditor(int mode, int reservationID, DataListEditableController dlec) {
        ReservationDataController rdc = new ReservationDataController(mode, reservationID, dlec);
        if (mode != 3) {
            ReservationDataView view = rdc.view;
            view.getFrame().revalidate();
            view.getFrame().pack();
            Dimension desktopSize = BaseWindow.getDesktop().getSize();
            Dimension jInternalFrameSize = view.getFrame().getSize();
            view.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            view.getFrame().show();
            view.getFrame().setVisible(true);
            BaseWindow.getDesktop().add(view.getFrame());
            view.getFrame().moveToFront();
            try {
                view.getFrame().setSelected(true);
                if (view.getFrame().isIcon()) {
                    view.getFrame().setIcon(false);
                }
                view.getFrame().setSelected(true);
            } catch (PropertyVetoException ex) {

            }
            view.getFrame().requestFocus();
        }
    }

    public void spawnFeedbackDataEditor(int mode, int feedbackID, DataListEditableController dlec) {
        FeedbackDataController fdc = new FeedbackDataController(mode, feedbackID, dlec);
        if (mode != 3) {
            FeedbackDataView view = fdc.view;
            view.getFrame().revalidate();
            view.getFrame().pack();
            Dimension desktopSize = BaseWindow.getDesktop().getSize();
            Dimension jInternalFrameSize = view.getFrame().getSize();
            view.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            view.getFrame().show();
            view.getFrame().setVisible(true);
            BaseWindow.getDesktop().add(view.getFrame());
            view.getFrame().moveToFront();
            try {
                view.getFrame().setSelected(true);
                if (view.getFrame().isIcon()) {
                    view.getFrame().setIcon(false);
                }
                view.getFrame().setSelected(true);
            } catch (PropertyVetoException ex) {

            }
            view.getFrame().requestFocus();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.reloadButton)) {
            view.reloadButton.setEnabled(false);
            view.reloadButton.revalidate();
            reloadData();
        } else {
            try {
                if (model.getPageTitle().contains("User")) {
                    if (e.getSource().equals(view.viewEntryButton)) {
                        spawnUserDataEditor(0, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()), null);
                    }
                } else if (model.getPageTitle().contains("Room")) {
                    if (e.getSource().equals(view.viewEntryButton)) {
                        spawnRoomDataEditor(0, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()), null);
                    }
                } else if (model.getPageTitle().contains("Reservation")) {
                    if (e.getSource().equals(view.viewEntryButton)) {
                        spawnReservationDataEditor(0, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()), null);
                    }
                } else if (model.getPageTitle().contains("Feedback")) {
                    if (e.getSource().equals(view.viewEntryButton)) {
                        spawnFeedbackDataEditor(0, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()), null);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(BaseWindow.baseFrame, ex.toString(), "Database Query Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
