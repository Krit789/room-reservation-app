package net.itkmitl.room.portal.content.components.history;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.util.FewFile;
import net.itkmitl.room.libs.phatsanphon.entity.Reservation;
import net.itkmitl.room.libs.phatsanphon.repository.ReservationRepository;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.components.ButtonGradient;
import net.itkmitl.room.portal.components.RoundedPanel;
import net.itkmitl.room.portal.content.MainContentView;
import net.itkmitl.room.portal.content.components.dashboard.BoxIcon;

public class ReservationHistoryBox extends RoundedPanel implements ActionListener {
    @Serial
    private static final long serialVersionUID = -4015833019658837000L;
    public ButtonGradient feedBackBtn, cancelBtn;
    private final JLabel timeLabel;
    private final JLabel dateLabel;
    private final JLabel nameLabel;
    private final JPanel dataPanel;
    private final BoxIcon icon;
    private FeedBackDialogue feedBackPage;
    private boolean dialogOpen;
    private Reservation reservation;

    public ReservationHistoryBox(String name, String time, String date, boolean isComplete, boolean isCancelled, Reservation reservation) {
        super(30, 40, Color.white);
        this.reservation = reservation;
        setPreferredSize(new Dimension(this.getBounds().width, 190));

        feedBackBtn = new ButtonGradient("FeedBack", new Color(59, 151, 88));
        feedBackBtn.setActionCommand("Feedback");
        feedBackBtn.addActionListener(this);

        cancelBtn = new ButtonGradient("Cancel", new Color(217, 76, 78));
        cancelBtn.setActionCommand("Cancel");
        cancelBtn.addActionListener(this);

        nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Cousine", Font.BOLD, 23));
        dateLabel = new JLabel(date);
        dateLabel.setFont(new Font("Cousine", Font.PLAIN, 18));
        timeLabel = new JLabel(time);
        timeLabel.setFont(new Font("Cousine", Font.PLAIN, 18));
        dataPanel = new JPanel(new GridLayout(1, 5));
        dataPanel.setOpaque(false);
        icon = new BoxIcon(FewFile.getImage("icons/round-table.png"));

        add(icon, BorderLayout.WEST);
        dataPanel.add(nameLabel);
        dataPanel.add(Box.createHorizontalStrut((this.getBounds().width - (icon.getWidth() + nameLabel.getWidth() + timeLabel.getWidth() + dateLabel.getWidth() + feedBackBtn.getWidth())) / 4));
        dataPanel.add(timeLabel);
//        dataPanel.add(Box.createHorizontalStrut((this.getBounds().width - (icon.getWidth() + nameLabel.getWidth() + timeLabel.getWidth() + dateLabel.getWidth() + feedBackBtn.getWidth())) / 4));
        dataPanel.add(dateLabel);
        add(dataPanel, BorderLayout.CENTER);
        if (isComplete) {
            add(feedBackBtn, BorderLayout.EAST);
        } else {
            if (isCancelled){
                cancelBtn.setText("Cancelled");
                cancelBtn.setEnabled(false);
            }
            add(cancelBtn, BorderLayout.EAST);
        }
    }

    public static Window findWindow(Component c) {
        if (c == null) {
            return JOptionPane.getRootFrame();
        } else if (c instanceof JFrame) {
            return (Window) c;
        } else {
            return findWindow(c.getParent());
        }
    }

    public void openFeedbackDialog() {
        SwingWorker<?, ?> worker = new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() throws Exception {
                FeedBackDialogueController rsvpd = new FeedBackDialogueController(null, reservation);
                MainContentView.glassPane.setSpinnerVisibility(false);
                MainContentView.glassPane.setText("");
                MainContentView.glassPane.setVisible(true);
                MainContentView.glassPane.setEnabled(true);
                rsvpd.view.setVisible(true);
                return null;
            }

            @Override
            protected void done() {
                dialogOpen = false;
            }

        };
        worker.execute();

    }

    private void cancelReservaion(int resId){
        SwingWorker<?,?> worker = new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() throws Exception {
                try {
                    cancelBtn.setText("Processing...");
                    cancelBtn.setEnabled(false);
                    FewQuery db = LaewTaeDB.getDB();
                    ReservationRepository reservationRepository = new ReservationRepository(db);
                    Reservation toBeCancelled = reservationRepository.getReservationById(reservation.getId());
                    toBeCancelled.setCancelled(true);
                    reservationRepository.updateReservation(toBeCancelled);
                    cancelBtn.setText("Cancelled");
                    cancelBtn.setEnabled(false);
                } catch (Exception ex) {
                    cancelBtn.setText("Cancel");
                    cancelBtn.setEnabled(true);
                    JOptionPane.showConfirmDialog(null, ex.getMessage(), "Something went wrong", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }
        };
        worker.execute();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Feedback")) {
            if (!dialogOpen) {
                openFeedbackDialog();
                dialogOpen = true;
            }
        } else if (e.getActionCommand().equals("Cancel")) {
            String[] options = new String[]{"OK", "Cancel"};
            MainContentView.glassPane.setSpinnerVisibility(false);
            MainContentView.glassPane.setText("");
            MainContentView.glassPane.setVisible(true);
            MainContentView.glassPane.setEnabled(true);
            int option = JOptionPane.showOptionDialog(findWindow(this),"<html><p>Are you sure that you want to cancel this reservation? This action cannot be undone!</p></html>", "Cancel", JOptionPane.NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

            if(option == 0) {
                MainContentView.glassPane.setVisible(false);
                MainContentView.glassPane.setEnabled(false);
                cancelReservaion(reservation.getId());
                JOptionPane.showConfirmDialog(findWindow(this), "Reservation Canceled", "Success", 0);
            }else {
                MainContentView.glassPane.setVisible(false);
                MainContentView.glassPane.setEnabled(false);
            }
        }
    }
}
