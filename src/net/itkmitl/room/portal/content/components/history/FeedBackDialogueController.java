package net.itkmitl.room.portal.content.components.history;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.entity.Feedback;
import net.itkmitl.room.libs.phatsanphon.entity.Reservation;
import net.itkmitl.room.libs.phatsanphon.repository.FeedbackRepository;
import net.itkmitl.room.portal.content.MainContentView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FeedBackDialogueController implements ActionListener, WindowListener {
    private final Reservation reservation;
    public FeedBackDialogue view;
    private double score;

    public FeedBackDialogueController(JFrame parent, Reservation reservation) {
        this.reservation = reservation;
        view = new FeedBackDialogue(parent, reservation);
        view.addWindowListener(this);
        view.submitBtn.addActionListener(this);
        view.cancelBtn.addActionListener(this);
        view.ratingGroup.clearSelection();
        view.rating00.addActionListener(this);
        view.rating05.addActionListener(this);
        view.rating10.addActionListener(this);
        view.rating15.addActionListener(this);
        view.rating20.addActionListener(this);
        view.rating25.addActionListener(this);
        view.rating30.addActionListener(this);
        view.rating35.addActionListener(this);
        view.rating40.addActionListener(this);
        view.rating45.addActionListener(this);
        view.rating50.addActionListener(this);
        view.rating00.setActionCommand("0");
        view.rating05.setActionCommand("0.5");
        view.rating10.setActionCommand("1");
        view.rating15.setActionCommand("1.5");
        view.rating20.setActionCommand("2");
        view.rating25.setActionCommand("2.5");
        view.rating30.setActionCommand("3");
        view.rating35.setActionCommand("3.5");
        view.rating40.setActionCommand("4");
        view.rating45.setActionCommand("4.5");
        view.rating50.setActionCommand("5");
    }

    private void disableGlassPane() {
        MainContentView.glassPane.setVisible(false);
        MainContentView.glassPane.setEnabled(false);
    }

    private void submitFeedback(int resId, double rating, String comment) {
        SwingWorker<?, ?> worker = new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() throws Exception {
                try {
                    FewQuery db = LaewTaeDB.getDB();
                    FeedbackRepository feedbackRepository = new FeedbackRepository(db);
                    Feedback toBeCriticized = new Feedback();
                    toBeCriticized.setUser(reservation.getUser());
                    toBeCriticized.setRoom(reservation.getRoom());
                    toBeCriticized.setRating(rating);
                    toBeCriticized.setComment(comment);
                    feedbackRepository.createFeedback(toBeCriticized);
                    JOptionPane.showMessageDialog(null, "Feedback submitted, Thank you for your input.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Something went wrong", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }

            @Override
            protected void done() {
                super.done();
                view.dispose();
                disableGlassPane();
            }
        };
        worker.execute();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.cancelBtn)) {
            view.dispose();
            disableGlassPane();
        } else if (e.getSource() instanceof JRadioButton & !view.submitBtn.isEnabled()) {
            view.submitBtn.setEnabled(true);
        } else if (e.getSource().equals(view.submitBtn)) {
            submitFeedback(reservation.getId(), Double.parseDouble(view.ratingGroup.getSelection().getActionCommand()), view.feedbackTextArea.getText());
        }
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
}
