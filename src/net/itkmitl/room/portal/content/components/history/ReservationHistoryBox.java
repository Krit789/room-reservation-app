package net.itkmitl.room.portal.content.components.history;

import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.portal.components.ButtonGradient;
import net.itkmitl.room.portal.components.RoundedPanel;
import net.itkmitl.room.portal.content.MainContentView;
import net.itkmitl.room.portal.content.components.dashboard.BoxIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

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
    private Room boxRoom;

    public ReservationHistoryBox(String name, String time, String date, boolean isComplete/*, Room room*/) {
        super(30, 40, Color.white);
//        this.room = room;
        setPreferredSize(new Dimension(1250, 190));

        feedBackBtn = new ButtonGradient("FeedBack", new Color(59, 151, 88));
        feedBackBtn.setActionCommand("Feedback");
        feedBackBtn.addActionListener(this);

        cancelBtn = new ButtonGradient("Cancel", new Color(217, 76, 78));
        cancelBtn.setActionCommand("Cancel");
        cancelBtn.addActionListener(this);

        nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Cousine", Font.BOLD, 25));
        dateLabel = new JLabel(date);
        dateLabel.setFont(new Font("Cousine", Font.PLAIN, 20));
        timeLabel = new JLabel(time);
        timeLabel.setFont(new Font("Cousine", Font.PLAIN, 20));
        dataPanel = new JPanel(new GridLayout(1, 5));
        dataPanel.setOpaque(false);
        icon = new BoxIcon(FewFile.getImage("icons/round-table.png"));

        add(icon, BorderLayout.WEST);
        add(Box.createHorizontalStrut(15));
        dataPanel.add(nameLabel);
        dataPanel.add(Box.createHorizontalStrut(5));
        dataPanel.add(timeLabel);
        dataPanel.add(Box.createHorizontalStrut(5));
        dataPanel.add(dateLabel);
        add(dataPanel, BorderLayout.CENTER);
        add(Box.createHorizontalStrut(15));
        if (isComplete) {
            add(feedBackBtn, BorderLayout.EAST);
        } else {
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
                FeedBackDialogueController rsvpd = new FeedBackDialogueController(null, boxRoom);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Feedback")) {
            if (!dialogOpen) {
                openFeedbackDialog();
                dialogOpen = true;
            }
        } else if (e.getActionCommand().equals("Cancel")) {
            JOptionPane.showConfirmDialog(findWindow(this), "Cancellation Successful", "", JOptionPane.CLOSED_OPTION);

        }
    }
}
