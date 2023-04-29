package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.admin.components.DatabaseLoader;
import net.itkmitl.room.portal.admin.models.DataListTableModel;
import net.itkmitl.room.portal.admin.models.DataSearchModel;
import net.itkmitl.room.portal.admin.views.DataListEditableView;
import net.itkmitl.room.portal.admin.views.DataSearchView;
import net.itkmitl.room.portal.admin.views.OperationWindowView;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OperationWindowController implements ActionListener, InternalFrameListener {
    private OperationWindowView view;
    private DatabaseLoader dbl;
    public static final int USER = 1;
    public static final int ROOM = 2;
    public static final int RESERVATION = 3;
    public static final int FEEDBACK = 4;

    public OperationWindowController() {
        view = new OperationWindowView();
        view.viewUser.addActionListener(this);
        view.viewRoom.addActionListener(this);
        view.viewReservation.addActionListener(this);
        view.viewFeedback.addActionListener(this);
        view.lookupUser.addActionListener(this);
        view.lookupRoom.addActionListener(this);
        view.lookupReservation.addActionListener(this);
        view.lookupFeedback.addActionListener(this);
        view.manageUser.addActionListener(this);
        view.manageRoom.addActionListener(this);
        view.manageReservation.addActionListener(this);
        view.manageFeedback.addActionListener(this);
        dbl = new DatabaseLoader();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.viewUser)) {
            dbl.databaseLoader(USER, false);
        } else if (e.getSource().equals(view.viewRoom)) {
            dbl.databaseLoader(ROOM, false);
        } else if (e.getSource().equals(view.viewReservation)) {
            dbl.databaseLoader(RESERVATION, false);
        } else if (e.getSource().equals(view.viewFeedback)) {
            dbl.databaseLoader(FEEDBACK, false);
        } else if (e.getSource().equals(view.lookupUser)) {
            spawnSearch(USER);
        } else if (e.getSource().equals(view.lookupRoom)) {
            spawnSearch(ROOM);
        } else if (e.getSource().equals(view.lookupReservation)) {
            spawnSearch(RESERVATION);
        } else if (e.getSource().equals(view.lookupFeedback)) {
            spawnSearch(FEEDBACK);
        } else if (e.getSource().equals(view.manageUser)) {
            dbl.databaseLoader(USER, true);
        } else if (e.getSource().equals(view.manageRoom)) {
            dbl.databaseLoader(ROOM, true);
        } else if (e.getSource().equals(view.manageReservation)) {
            dbl.databaseLoader(RESERVATION, true);
        } else if (e.getSource().equals(view.manageFeedback)) {
            dbl.databaseLoader(FEEDBACK, true);
        }
    }

    public OperationWindowView getView() {
        return view;
    }

    public void spawnSearch(int type) {
        ArrayList<String> radioList = new ArrayList<>();
        String title = null;
        switch (type) {
            case 1: // User
                title = "User";
                radioList.add("ID");
                radioList.add("First Name");
                radioList.add("Last Name");
                radioList.add("E-Mail");
                radioList.add("Phone Number");
                break;
            case 2: // Room
                title = "Room";
                radioList.add("ID");
                radioList.add("Name");
                radioList.add("Building");
                radioList.add("State");
                break;
            case 3: // Reservation
                title = "Reservation";
                radioList.add("ID");
                radioList.add("User ID");
                radioList.add("Room ID");
                radioList.add("Reason");
                break;
            case 4: // Feedback
                title = "Feedback";
                radioList.add("ID");
                radioList.add("Room ID");
                radioList.add("User ID");
                radioList.add("Rating");
                radioList.add("Comment");
                break;
        }
        DataSearchController dsc = new DataSearchController(new DataSearchModel(radioList, title));
        DataSearchView view = dsc.view;

        Dimension desktopSize = BaseWindow.getDesktop().getSize();
        Dimension jInternalFrameSize = view.getFrame().getSize();
        view.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        view.getFrame().addInternalFrameListener(this);
        view.getFrame().show();
        view.getFrame().setVisible(true);
        BaseWindow.getDesktop().add(view.getFrame());
        view.getFrame().moveToFront();
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


}
