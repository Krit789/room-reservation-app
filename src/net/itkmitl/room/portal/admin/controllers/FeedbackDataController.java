package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.libs.jarukrit.ProgramError;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.date.DateTime;
import net.itkmitl.room.libs.phatsanphon.entity.Feedback;
import net.itkmitl.room.libs.phatsanphon.entity.Reservation;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.phatsanphon.repository.FeedbackRepository;
import net.itkmitl.room.libs.phatsanphon.repository.ReservationRepository;
import net.itkmitl.room.libs.phatsanphon.repository.RoomRepository;
import net.itkmitl.room.libs.phatsanphon.repository.UserRepository;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.admin.components.DatabaseLoader;
import net.itkmitl.room.portal.admin.models.RoomComboBoxModel;
import net.itkmitl.room.portal.admin.models.UserComboBoxModel;
import net.itkmitl.room.portal.admin.views.FeedbackDataView;
import net.itkmitl.room.portal.components.LoadingDialog;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Date;

public class FeedbackDataController implements ActionListener, InternalFrameListener, ChangeListener {
    public final FeedbackDataView view;
    private Feedback feedback;
    private Object[] data;
    private final int mode;
    private final ArrayList<UserComboBoxModel> userArrayList = new ArrayList<>();
    private final ArrayList<RoomComboBoxModel> roomArrayList = new ArrayList<>();
    private final DataListEditableController dlec;

    public FeedbackDataController(int mode, int feedbackID, DataListEditableController dlec) {
        this.mode = mode;
        this.dlec = dlec;
        view = new FeedbackDataView();
        feedback = new Feedback();
        switch (mode) {
            case 0: // Just Load
                view.getFrame().setTitle("View Feedback");
                view.pageTitle.setText("Feedback Records Viewer");
                view.getFrame().pack();
                view.saveButton.addActionListener(this);
                view.cancelButton.addActionListener(this);
                databaseLoader(feedbackID);
                break;
            case 1: // Update
                view.getFrame().setTitle("Update Feedback");
                view.pageTitle.setText("Feedback Records Update");
                view.getFrame().pack();
                view.saveButton.addActionListener(this);
                view.cancelButton.addActionListener(this);
                databaseLoader(feedbackID);
                break;
            case 2: // Create
                view.saveButton.addActionListener(this);
                view.cancelButton.addActionListener(this);
                view.getFrame().setTitle("Feedback Creation");
                view.pageTitle.setText("Feedback Records Creator");
                view.pageSubtitle.setText("Create new feedback records");
                view.saveButton.setText("Create");
                dataPopulator();
                break;
            case 3: // Delete
                feedback.setId(feedbackID);
                databaseCommiter(feedback, 1);
                break;
        }
        view.ratingSpinner.addChangeListener(this);
        view.ratingSlider.addChangeListener(this);
        view.getFrame().addInternalFrameListener(this);

    }

    public void databaseLoader(int reservationID) {
        SwingWorker worker = new SwingWorker() {
            final LoadingDialog ld = new LoadingDialog();

            @Override
            protected String doInBackground() {
                String errorMessage;
                ld.dialog.setVisible(true);
                BaseWindow.progressBar.setIndeterminate(true);
                FewQuery db = RVDB.getDB();
                try {
                    Feedback myFeedback = new FeedbackRepository(db).getFeedbackById(reservationID);
                    switch (mode) {
                        case 0:
                            ArrayList<User> user_list = new UserRepository(db).getUsers();
                            ArrayList<Room> room_list = new RoomRepository(db).getRooms();
                            for (User u : user_list) {
                                userArrayList.add(new UserComboBoxModel(u));
                            }
                            for (Room r : room_list) {
                                roomArrayList.add(new RoomComboBoxModel(r));
                            }
                            break;
                        default:
                            userArrayList.add(new UserComboBoxModel(myFeedback.getUser()));
                            roomArrayList.add(new RoomComboBoxModel(myFeedback.getRoom()));
                            break;
                    }

                    data = new Object[]{true, myFeedback};
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

    public void databaseCommiter(Feedback feedback, int mode) {
        SwingWorker worker = new SwingWorker() {
            LoadingDialog ld = new LoadingDialog();

            @Override
            protected String doInBackground() {
                String errorMessage;
                ld.dialog.setVisible(true);
                BaseWindow.progressBar.setIndeterminate(true);
                FewQuery db = RVDB.getDB();
                FeedbackRepository myFeedback = new FeedbackRepository(db);
                switch (mode) {
                    case 0: // Update
                        try {
                            myFeedback.updateFeedback(feedback);
                            data = new Object[]{true, myFeedback};
                        } catch (Exception ex) {
                            errorMessage = ProgramError.getStackTrace(ex);
                            data = new Object[]{false, errorMessage};
                        }
                        break;
                    case 1: // Delete
                        try {
                            myFeedback.deleteFeedbackById(feedback.getId());
                            data = new Object[]{true, myFeedback};
                        } catch (Exception ex) {
                            errorMessage = ProgramError.getStackTrace(ex);
                            data = new Object[]{false, errorMessage};
                        }
                        break;
                    case 2: // Create
                        try {
                            myFeedback.createFeedback(feedback);
                            data = new Object[]{true, myFeedback};
                        } catch (Exception ex) {
                            errorMessage = ProgramError.getStackTrace(ex);
                            data = new Object[]{false, errorMessage};
                        }
                        break;
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
                DatabaseLoader dbl = new DatabaseLoader();
                dbl.databaseLoader(4, true);
                dlec.view.getFrame().dispose();

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
                FewQuery db = RVDB.getDB();
                try {
                    ArrayList<User> user_list = new UserRepository(db).getUsers();
                    ArrayList<Room> room_list = new RoomRepository(db).getRooms();
                    for (User u : user_list) {
                        userArrayList.add(new UserComboBoxModel(u));
                    }
                    for (Room r : room_list) {
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
            Feedback myFeedback = (Feedback) data[1];
            feedback = (Feedback) data[1];
            view.pageSubtitle.setText("Feedback records for Feedback ID " + myFeedback.getId());
            view.idField.setText(feedback.getId() + "");
            for (UserComboBoxModel u : userArrayList) {
                view.userIDSelect.addItem(u);
                if (u.getUser().getId() == feedback.getUser().getId()) {
                    view.userIDSelect.setSelectedItem(u);
                }
            }
            for (RoomComboBoxModel r : roomArrayList) {
                view.roomIDSelect.addItem(r);
                if (r.getRoom().getId() == feedback.getRoom().getId()) {
                    view.roomIDSelect.setSelectedItem(r);
                }
            }
            view.commentField.setText(feedback.getComment());
            view.createdOnField.setText(feedback.getCreatedOn().toString());
            view.ratingSpinner.getModel().setValue(feedback.getRating());
            view.ratingSlider.setValue((int) (feedback.getRating() * 2));

            switch (mode) {
                case 0:
                    view.userIDSelect.setEnabled(false);
                    view.roomIDSelect.setEnabled(false);
                    view.commentField.setEditable(false);
                    view.ratingSlider.setEnabled(false);
                    view.ratingSpinner.setEnabled(false);
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
            feedback.setUser(((UserComboBoxModel) view.userIDSelect.getSelectedItem()).getUser());
            feedback.setRoom(((RoomComboBoxModel) view.roomIDSelect.getSelectedItem()).getRoom());
            feedback.setComment(view.commentField.getText());
            feedback.setRating(((Number) view.ratingSpinner.getValue()).doubleValue());

            switch (mode) {
                case 1: // Update
                    databaseCommiter(feedback, 0);
                    break;
                case 2: // Create
                    databaseCommiter(feedback, 2);
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
    public void stateChanged(ChangeEvent e) {
        if (e.getSource().equals(view.ratingSpinner)) {
            view.ratingSlider.setValue((int) (((Number) view.ratingSpinner.getValue()).doubleValue() * 2));
        } else if (e.getSource().equals(view.ratingSlider)) {
            view.ratingSpinner.setValue((view.ratingSlider.getValue() / 2.0));
        }
    }
}
