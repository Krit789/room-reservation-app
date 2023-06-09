package net.itkmitl.room.portal.admin.components;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.libs.jarukrit.ProgramError;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.simplevalue.FewSimpleValue;
import net.itkmitl.room.libs.peeranat.util.FewFile;
import net.itkmitl.room.libs.phatsanphon.entity.Feedback;
import net.itkmitl.room.libs.phatsanphon.entity.Reservation;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.phatsanphon.repository.FeedbackRepository;
import net.itkmitl.room.libs.phatsanphon.repository.ReservationRepository;
import net.itkmitl.room.libs.phatsanphon.repository.RoomRepository;
import net.itkmitl.room.libs.phatsanphon.repository.UserRepository;
import net.itkmitl.room.libs.phatsanphon.repository.enums.FeedbackQuery;
import net.itkmitl.room.libs.phatsanphon.repository.enums.ReservationQuery;
import net.itkmitl.room.libs.phatsanphon.repository.enums.RoomQuery;
import net.itkmitl.room.libs.phatsanphon.repository.enums.UserQuery;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.admin.controllers.DataListEditableController;
import net.itkmitl.room.portal.admin.controllers.DataListTableController;
import net.itkmitl.room.portal.admin.models.DataListTableModel;
import net.itkmitl.room.portal.components.LoadingDialog;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;

public class DatabaseLoader implements InternalFrameListener {
    private boolean editableTable;

    public void databaseLoader(int whichTable) {
        databaseLoader(whichTable, 99, "", editableTable, null);
    }

    public void databaseLoader(int whichTable, boolean editableTable) {
        databaseLoader(whichTable, 99, "", editableTable, null);
    }

    public void databaseLoader(int whichTable, String searchQuery, boolean editableTable) {
        databaseLoader(whichTable, 99, searchQuery, editableTable, null);
    }

    public void databaseLoader(int whichTable, int type, String searchQuery, boolean editableTable) {
        databaseLoader(whichTable, type, searchQuery, editableTable, null);
    }

    public void databaseLoader(int whichTable, int type, String searchQuery) {
        databaseLoader(whichTable, type, searchQuery, editableTable, null);
    }

    public void databaseLoader(int whichTable, int type, String searchQuery, DataListEditableController table) {
        databaseLoader(whichTable, type, searchQuery, editableTable, table);
    }

    public void databaseLoader(int whichTable, int type, String searchQuery, boolean editableTable, DataListTableController table) {
        this.editableTable = editableTable;
        SwingWorker<?, ?> worker = new SwingWorker<>() {
            final LoadingDialog ld = new LoadingDialog();
            Object[] data;

            @Override
            protected String doInBackground() throws Exception {
                String errorMessage;
                ld.dialog.setVisible(true);
                BaseWindow.progressBar.setIndeterminate(true);
                try {
                    FewQuery db = LaewTaeDB.getDB();
                    DataListTableModel model = new DataListTableModel();
                    DefaultTableModel dtm;
                    switch (whichTable) {
                        case 1:
                            ArrayList<User> users_list;
                            UserRepository userRepository = new UserRepository(db);
                            if (type == 99) {
                                users_list = userRepository.getUsers();
                                model.setTitle("User Data");
                                model.setPageTitle("User DataTable");
                            } else {
                                users_list = new UserRepository(db).getUsers(UserQuery.getQueryByID(type), searchQuery);
                                model.setTitle(String.format("Results for \"%s\" in user", searchQuery));
                                model.setPageTitle(String.format("(%d) Query Search for User", UserQuery.getQueryByID(type).getQuery()));
                            }
                            BaseWindow.statusLabel.setText("Loading data from Database (User Table)");
                            model.setPageSubtitle(users_list.size() + " records was retrieved from database.");
                            dtm = new DefaultTableModel(null, new String[]{"ID", "First Name", "Last Name", "Active", "Phone Number", "E-Mail", "Role", "Created On", "Staff"}){
                                @Override
                                public Class<?> getColumnClass(int column) {
                                    switch(column) {
                                        case 0: return Integer.class;
                                        case 8: return ImageIcon.class;
                                        default: return Object.class;
                                    }
                                }
                            };
                            for (User u : users_list) {
                                dtm.addRow(new Object[]{u.getId(), u.getFirstname(), u.getLastname(), u.isActive(), u.getTelephoneNumber(), u.getEmail(), u.getRole(), u.getCreatedOn(), u.isStaff() ? FewFile.getImage("icons/yes-16px.png"): null});
                            }
                            model.setDtm(dtm);
                            data = new Object[]{Boolean.valueOf(true), model};
                            break;
                        case 2:
                            ArrayList<Room> room_list;
                            RoomRepository roomRepository = new RoomRepository(db);
                            if (type == 99) {
                                room_list = roomRepository.getRooms();
                                model.setTitle("Room Data");
                                model.setPageTitle("Room DataTable");
                            } else {
                                room_list = roomRepository.getRooms(RoomQuery.getQueryByID(type), searchQuery);
                                model.setTitle(String.format("Results for \"%s\" in room", searchQuery));
                                model.setPageTitle(String.format("(%d) Query Search for Room", RoomQuery.getQueryByID(type).getQuery()));
                            }
                            BaseWindow.statusLabel.setText("Loading data from Database (Room Table)");
                            model.setPageSubtitle(room_list.size() + " records was retrieved from database.");
                            dtm = new DefaultTableModel(null, new String[]{"ID", "Name", "Building", "Floor", "Capacity", "Open Time", "Close Time", "State"});
                            for (Room u : room_list) {
                                dtm.addRow(new Object[]{u.getId(), u.getName(), u.getBuilding(), u.getFloor(), u.getCapacity(), String.format("%02d:%02d", u.getOpenTime().getHours(), u.getOpenTime().getMinutes()), String.format("%02d:%02d", u.getCloseTime().getHours(), u.getCloseTime().getMinutes()), u.getState()});
                            }
                            model.setDtm(dtm);
                            data = new Object[]{Boolean.valueOf(true), model};
                            break;
                        case 3:
                            ArrayList<Reservation> reservations_list;
                            ReservationRepository reservationRepository = new ReservationRepository(db);
                            if (type == 99) {
                                reservations_list = reservationRepository.getReservations();
                                model.setTitle("Reservation Data");
                                model.setPageTitle("Reservation DataTable");
                            } else {
                                reservations_list = reservationRepository.getReservations(ReservationQuery.getQueryByID(type), searchQuery);
                                model.setTitle(String.format("Results for \"%s\" in reservation", searchQuery));
                                model.setPageTitle("Query Search for Reservation");
                                model.setPageTitle(String.format("(%d) Query Search for Reservation", ReservationQuery.getQueryByID(type).getQuery()));
                            }
                            BaseWindow.statusLabel.setText("Loading data from Database (Reservation Table)");
                            model.setPageSubtitle(reservations_list.size() + " records was retrieved from database.");
                            dtm = new DefaultTableModel(null, new String[]{"ID", "Room Name", "Room Location", "Reserver", "Start Time", "End Time", "Reserved At", "Cancelled"}){
                                @Override
                                public Class<?> getColumnClass(int column) {
                                    switch(column) {
                                        case 0: return Integer.class;
                                        case 7: return ImageIcon.class;
                                        default: return Object.class;
                                    }
                                }
                            };
                            for (Reservation u : reservations_list) {
                                dtm.addRow(new Object[]{u.getId(), u.getRoom().getName(), u.getRoom().getBuilding(), u.getUser().getFirstname() + " " + u.getUser().getLastname(), u.getStartTime(), u.getEndTime(), u.getReservationTime(), u.isCancelled() ? FewFile.getImage("icons/yes-16px.png"): null});
                            }
                            model.setDtm(dtm);
                            data = new Object[]{Boolean.valueOf(true), model};
                            break;
                        case 4:
                            ArrayList<Feedback> feedbacks_list;
                            FeedbackRepository feedbackRepository = new FeedbackRepository(db);
                            if (type == 99) {
                                feedbacks_list = feedbackRepository.getFeedbacks();
                                model.setTitle("Feedback Data");
                                model.setPageTitle("Feedback DataTable");
                            } else {
                                feedbacks_list = feedbackRepository.getFeedbacks(FeedbackQuery.getQueryByID(type), searchQuery);
                                model.setTitle(String.format("Results for \"%s\" in feedback", searchQuery));
                                model.setPageTitle(String.format("(%d) Query Search for Feedback", FeedbackQuery.getQueryByID(type).getQuery()));
                            }
                            BaseWindow.statusLabel.setText("Loading data from Database (Feedback Table)");
                            model.setPageSubtitle(feedbacks_list.size() + " records was retrieved from database.");
                            dtm = new DefaultTableModel(null, new String[]{"ID", "Rating", "Created On", "Room", "User"});
                            for (Feedback u : feedbacks_list) {
                                dtm.addRow(new Object[]{u.getId(), u.getRating(), u.getCreatedOn(), u.getRoom().getName(), u.getUser().getFirstname() + " " + u.getUser().getLastname()});
                            }
                            model.setDtm(dtm);
                            data = new Object[]{Boolean.valueOf(true), model};
                            break;
                        default:
                            data = new Object[]{Boolean.valueOf(false), String.format("Error: Table %d is out of index!", whichTable)};
                            break;
                    }
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                    data = new Object[]{Boolean.valueOf(false), String.format("\"%s\" type \"%d\" was not found in the records", searchQuery, type)};
                } catch (SQLSyntaxErrorException | SQLNonTransientConnectionException ex) {
                    data = new Object[]{Boolean.valueOf(false), ex.getMessage()};
                } catch (Exception ex) {
                    errorMessage = ProgramError.getStackTrace(ex);
                    data = new Object[]{Boolean.valueOf(false), errorMessage};
                }
                return "";
            }

            @Override
            protected void done() {
                if ((table != null) && (new FewSimpleValue(data[0]).asBoolean())) {
                    table.view.table.setModel(((DataListTableModel) data[1]).getDtm());
                    if (table instanceof DataListEditableController) {
                        ((DataListEditableController) table).view.reloadButton.setEnabled(true);
                    } else {
                        table.view.reloadButton.setEnabled(true);
                    }
                    try {
                        table.view.getFrame().setSelected(true);
                        if (table.view.getFrame().isIcon()) {
                            table.view.getFrame().setIcon(false);
                        }
                        table.view.getFrame().setSelected(true);
                    } catch (PropertyVetoException ex) {

                    }
                    table.view.getFrame().requestFocus();

                } else if ((table != null) && (!new FewSimpleValue(data[0]).asBoolean())) {
                    JOptionPane.showMessageDialog(BaseWindow.baseFrame, data[1].toString(), "Database Query Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    spawnTableView(data);
                }
                ld.dialog.dispose();
                BaseWindow.progressBar.setIndeterminate(false);
            }
        };
        worker.execute();
    }

    public void spawnTableView(Object[] data) {
        boolean dbReady = (new FewSimpleValue(data[0]).asBoolean());

        if (dbReady) {
            if (editableTable) {
                DataListEditableController table = new DataListEditableController((DataListTableModel) data[1]);
                Dimension desktopSize = BaseWindow.getDesktop().getSize();
                Dimension jInternalFrameSize = table.view.getFrame().getSize();
                table.view.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                        (desktopSize.height - jInternalFrameSize.height) / 2);
                table.view.getFrame().show();
                table.view.getFrame().setVisible(true);
                BaseWindow.getDesktop().add(table.view.getFrame());
                table.view.getFrame().moveToFront();
                try {
                    table.view.getFrame().setSelected(true);
                    if (table.view.getFrame().isIcon()) {
                        table.view.getFrame().setIcon(false);
                    }
                    table.view.getFrame().setSelected(true);
                } catch (PropertyVetoException ex) {

                }
                table.view.getFrame().requestFocus();
            } else {
                DataListTableController table = new DataListTableController((DataListTableModel) data[1]);
                Dimension desktopSize = BaseWindow.getDesktop().getSize();
                Dimension jInternalFrameSize = table.view.getFrame().getSize();
                table.view.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                        (desktopSize.height - jInternalFrameSize.height) / 2);
                table.view.getFrame().show();
                table.view.getFrame().setVisible(true);
                BaseWindow.getDesktop().add(table.view.getFrame());
                table.view.getFrame().moveToFront();
                try {
                    table.view.getFrame().setSelected(true);
                    if (table.view.getFrame().isIcon()) {
                        table.view.getFrame().setIcon(false);
                    }
                    table.view.getFrame().setSelected(true);
                } catch (PropertyVetoException ex) {

                }
                table.view.getFrame().requestFocus();
            }

        } else {
            JOptionPane.showMessageDialog(BaseWindow.baseFrame, data[1].toString(), "Database Query Error", JOptionPane.ERROR_MESSAGE);
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
        } catch (Exception ex) {
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
