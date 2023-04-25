package net.itkmitl.room.portal.admin.components;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.entity.Feedback;
import net.itkmitl.room.libs.phatsanphon.entity.Reservation;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.phatsanphon.repository.FeedbackRepository;
import net.itkmitl.room.libs.phatsanphon.repository.ReservationRepository;
import net.itkmitl.room.libs.phatsanphon.repository.RoomRepository;
import net.itkmitl.room.libs.phatsanphon.repository.UserRepository;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.admin.controllers.DataListTableController;
import net.itkmitl.room.portal.admin.models.DataListTableModel;
import net.itkmitl.room.portal.components.LoadingDialog;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DatabaseLoader implements InternalFrameListener {
    public void databaseLoader(int whichTable) {
        databaseLoader(whichTable, 99, "");
    }

    public void databaseLoader(int whichTable, int type, String searchQuery) {
        SwingWorker worker = new SwingWorker() {
            DataListTableModel model = new DataListTableModel();
            Object[] data;
            LoadingDialog ld = new LoadingDialog();

            @Override
            protected String doInBackground() throws Exception {
                String errorMessage;
                ld.dialog.setVisible(true);
                BaseWindow.progressBar.setIndeterminate(true);
                FewQuery db = RVDB.getDB();
                DataListTableModel model = new DataListTableModel();
                try {
                    DefaultTableModel dtm;
                    switch (whichTable) {
                        case 1:
                            ArrayList<User> users_list;
                            switch (type) {
                                case 99:
                                    users_list = new UserRepository(db).getUsers();
                                    model.setTitle("User Data");
                                    model.setPageTitle("User DataTable");
                                    break;
                                default:
                                    users_list = new UserRepository(db).getUsersBy(type, searchQuery);
                                    model.setTitle(String.format("Results for \"%s\" in user", searchQuery));
                                    model.setPageTitle("Query Search for User");
                                    break;
                            }
                            BaseWindow.statusLabel.setText("Loading data from Database (User Table)");
                            model.setPageSubtitle(users_list.size() + " records was retrieved from database.");
                            dtm = new DefaultTableModel(null, new String[]{"ID", "First Name", "Last Name", "Active", "Phone Number", "E-Mail", "Role", "Created On", "Staff"});
                            for (User u : users_list) {
                                dtm.addRow(new Object[]{u.getId(), u.getFirstname(), u.getLastname(), u.isActive(), u.getEmail(), u.getTelephoneNumber(), u.getRole(), u.getCreatedOn(), u.isStaff()});
                            }
                            model.setDtm(dtm);
                            data = new Object[]{Boolean.valueOf(true), model};
                            break;
                        case 2:
                            ArrayList<Room> room_list;
                            switch (type) {
                                case 99:
                                    room_list = new RoomRepository(db).getRooms();
                                    model.setTitle("Room Data");
                                    model.setPageTitle("Room DataTable");
                                    break;
                                default:
                                    room_list = new RoomRepository(db).getRoomsBy(type, searchQuery);
                                    model.setTitle(String.format("Results for \"%s\" in room", searchQuery));
                                    model.setPageTitle("Query Search for Room");
                                    break;
                            }
                            BaseWindow.statusLabel.setText("Loading data from Database (Room Table)");
                            model.setPageSubtitle(room_list.size() + " records was retrieved from database.");
                            dtm = new DefaultTableModel(null, new String[]{"ID", "Name", "Building", "Capacity", "Floor", "State"});
                            for (Room u : room_list) {
                                dtm.addRow(new Object[]{u.getId(), u.getName(), u.getBuilding(), u.getCapacity(), u.getFloor(), u.getState()});
                            }
                            model.setDtm(dtm);
                            data = new Object[]{Boolean.valueOf(true), model};
                            break;
                        case 3:
                            ArrayList<Reservation> reservations_list;
                            switch (type) {
                                case 99:
                                    reservations_list = new ReservationRepository(db).getReservations();
                                    model.setTitle("Reservation Data");
                                    model.setPageTitle("Reservation DataTable");
                                    break;
                                default:
                                    reservations_list = new ReservationRepository(db).getReservationsBy(type, searchQuery);
                                    model.setTitle(String.format("Results for \"%s\" in reservation", searchQuery));
                                    model.setPageTitle("Query Search for Reservation");
                                    break;
                            }
                            BaseWindow.statusLabel.setText("Loading data from Database (Reservation Table)");
                            model.setPageSubtitle(reservations_list.size() + " records was retrieved from database.");
                            dtm = new DefaultTableModel(null, new String[]{"ID", "Room Name", "Room Location", "Reserver", "Reason", "Start Time", "End Time", "Reservation Length", "Cancelled"});
                            for (Reservation u : reservations_list) {
                                dtm.addRow(new Object[]{u.getId(), u.getRoom().getName(), u.getRoom().getBuilding(), u.getUser().getFirstname() + " " + u.getUser().getLastname(), u.getReason(), u.getStartTime(), u.getEndTime().toString(), u.getReservationTime(), u.isCancelled()});
                            }
                            model.setDtm(dtm);
                            data = new Object[]{Boolean.valueOf(true), model};
                            break;
                        case 4:
                            ArrayList<Feedback> feedbacks_list ;
                            switch (type) {
                                case 99:
                                    feedbacks_list = new FeedbackRepository(db).getFeedbacks();
                                    model.setTitle("Feedback Data");
                                    model.setPageTitle("Feedback DataTable");
                                    break;
                                default:
                                    feedbacks_list = new FeedbackRepository(db).getFeedbacksBy(type, searchQuery);
                                    model.setTitle(String.format("Results for \"%s\" in feedback", searchQuery));
                                    model.setPageTitle("Query Search for Feedback");
                                    break;
                            }
                            BaseWindow.statusLabel.setText("Loading data from Database (Feedback Table)");
                            model.setPageSubtitle(feedbacks_list.size() + " records was retrieved from database.");
                            dtm = new DefaultTableModel(null, new String[]{"ID", "Comment", "Rating", "Created On", "Room", "User"});
                            for (Feedback u : feedbacks_list) {
                                dtm.addRow(new Object[]{u.getId(), u.getComment(), u.getRating(), u.getCreatedOn(), u.getRoom().getName(), u.getUser().getFirstname() + " " + u.getUser().getLastname()});
                            }
                            model.setDtm(dtm);
                            data = new Object[]{Boolean.valueOf(true), model};
                            break;
                        default:
                            data = new Object[]{Boolean.valueOf(false), String.format("Error: Table %d is out of index!", whichTable)};
                            break;
                    }
                } catch (Exception e) {
                    errorMessage = e.getMessage();
                    data = new Object[]{Boolean.valueOf(false), errorMessage};
                }
                return "";
            }

            @Override
            protected void done() {
                spawnTableView(data);
                ld.dialog.dispose();
                BaseWindow.progressBar.setIndeterminate(false);
            }
        };
        worker.execute();
    }

    public DataListTableController spawnTableView(Object[] data) {
        boolean dbReady = ((Boolean) data[0]);

        if (dbReady) {
            DataListTableController table = new DataListTableController((DataListTableModel) data[1]);
            Dimension desktopSize = BaseWindow.getDesktop().getSize();
            Dimension jInternalFrameSize = table.view.getFrame().getSize();
            table.view.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            table.view.getFrame().addInternalFrameListener(this);
            table.view.getFrame().show();
            table.view.getFrame().setVisible(true);
            BaseWindow.getDesktop().add(table.view.getFrame());
            table.view.getFrame().moveToFront();
            return table;
        } else {
            JOptionPane.showMessageDialog(BaseWindow.baseFrame, data[1].toString(), "Database Query Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
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
        BaseWindow.windowMenu.remove(BaseWindow.windowList.get(e.getInternalFrame()));
        BaseWindow.windowList.remove(e.getInternalFrame());
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
