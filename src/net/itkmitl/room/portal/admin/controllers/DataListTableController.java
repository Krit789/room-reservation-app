package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.admin.models.DataListTableModel;
import net.itkmitl.room.portal.admin.models.DataSearchModel;
import net.itkmitl.room.portal.admin.views.*;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataListTableController implements ListSelectionListener, InternalFrameListener, ActionListener {
    public DataListTableView view;
    public DataListTableModel model;

    public DataListTableController() {
        this(new DataListTableModel());
    }

    public DataListTableController(DataListTableModel model) {
        this.view = new DataListTableView();
        this.model = model;
        view.getFrame().setTitle(model.getTitle());
        view.pageTitle.setText(model.getPageTitle());
        view.pageSubtitle.setText(model.getPageSubtitle());
        view.table.setModel(model.getDtm());
        view.viewEntryButton.setEnabled(false);
        if (!(this instanceof DataListEditableController)) {
            view.table.getSelectionModel().addListSelectionListener(this);
            view.getFrame().addInternalFrameListener(this);
        }
        view.viewEntryButton.addActionListener(this);

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
            System.out.println("Removal Success: " + e.getInternalFrame().getTitle() + " " + this.getClass().getSimpleName());
        } catch (Exception ex) {
            System.out.println("Removal Failure: " + e.getInternalFrame().getTitle() + " " + this.getClass().getSimpleName());
            ex.printStackTrace();
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

    public void spawnUserDataEditor(int mode, int userID, DataListEditableController dlec) {
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
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
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

    }
}
