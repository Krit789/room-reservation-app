package net.itkmitl.room.portal.content.components.history;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.util.FewFile;
import net.itkmitl.room.libs.phatsanphon.entity.Reservation;
import net.itkmitl.room.libs.phatsanphon.repository.ReservationRepository;
import net.itkmitl.room.portal.components.ButtonGradient;
import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.RoundedPanel;
import net.itkmitl.room.portal.components.TransparentPanel;
import net.itkmitl.room.portal.content.MainContentView;
import net.itkmitl.room.portal.content.components.dashboard.BoxIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

public class ReservationHistoryBox extends RoundedPanel implements ActionListener {
    @Serial
    private static final long serialVersionUID = -4015833019658837000L;
    private final JLabel timeLabel;
    private final JLabel dateLabel;
    private final JLabel nameLabel;
    private final JPanel dataPanel;
    private final BoxIcon icon;
    public ButtonGradient feedBackBtn, cancelBtn;
    private FeedBackDialogue feedBackPage;
    private boolean dialogOpen;
    private Reservation reservation;

    public ReservationHistoryBox(String name, String time, String date, boolean isComplete, boolean isCancelled, Reservation reservation) {
        super(30, 40, Color.white);
        setBorder(new EmptyBorder(10, 25, 10, 35));
        setLayout(new BorderLayout());
        this.reservation = reservation;
        setPreferredSize(new Dimension(this.getBounds().width, 190));

        feedBackBtn = new ButtonGradient("Feedback", new Color(59, 151, 88));
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

        TransparentPanel iconPanel = new TransparentPanel();
        iconPanel.setLayout(new GridBagLayout());
        iconPanel.add(icon, new GBCBuilder(GridBagConstraints.CENTER, 1, 0, 0).getGBC());

        add(iconPanel, BorderLayout.WEST);

        TransparentPanel operationPanel = new TransparentPanel();
        operationPanel.setLayout(new GridBagLayout());

        dataPanel.add(nameLabel);
//        dataPanel.add(Box.createHorizontalStrut((this.getBounds().width - (icon.getWidth() + nameLabel.getWidth() + timeLabel.getWidth() + dateLabel.getWidth() + feedBackBtn.getWidth())) / 4));
        dataPanel.add(timeLabel);
//        dataPanel.add(Box.createHorizontalStrut((this.getBounds().width - (icon.getWidth() + nameLabel.getWidth() + timeLabel.getWidth() + dateLabel.getWidth() + feedBackBtn.getWidth())) / 4));
        dataPanel.add(dateLabel);
        add(dataPanel, BorderLayout.CENTER);
        if (isComplete) {
            operationPanel.add(feedBackBtn, new GBCBuilder(GridBagConstraints.CENTER, 1, 0, 0).getGBC());
        } else {
            if (isCancelled) {
                cancelBtn.setText("Cancelled");
                cancelBtn.setColor1(Color.gray);
                cancelBtn.setColor2(Color.gray);
                cancelBtn.setEnabled(false);
                operationPanel.add(cancelBtn, new GBCBuilder(GridBagConstraints.CENTER, 1, 0, 0).getGBC());
            } else {
                operationPanel.add(cancelBtn, new GBCBuilder(GridBagConstraints.CENTER, 1, 0, 0, new Insets(0, 0, 0, 10)).getGBC());
            }
        }

        this.add(operationPanel, BorderLayout.EAST);
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

    private void cancelReservaion(int resId) {
        SwingWorker<?, ?> worker = new SwingWorker<Object, Object>() {
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
                    cancelBtn.setColor1(Color.gray);
                    cancelBtn.setColor2(Color.gray);
                    cancelBtn.setEnabled(false);
                } catch (Exception ex) {
                    cancelBtn.setText("Cancel");
                    cancelBtn.setEnabled(true);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Something went wrong", JOptionPane.ERROR_MESSAGE);
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
            int option = JOptionPane.showOptionDialog(findWindow(this), "<html><p>Are you sure that you want to cancel this reservation? This action cannot be undone!</p></html>", "Cancel", JOptionPane.NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            if (option == 0) {
                cancelReservaion(reservation.getId());
                JOptionPane.showMessageDialog(findWindow(this), "Reservation Cancelled", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            MainContentView.glassPane.setVisible(false);
            MainContentView.glassPane.setEnabled(false);
        }
    }
}
