package net.itkmitl.room.portal.content.components.history;

import net.itkmitl.room.portal.components.ButtonGradient;
import net.itkmitl.room.portal.components.RoundedPanel;
import net.itkmitl.room.portal.content.components.dashboard.BoxIcon;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class ReservationHistoryBox extends RoundedPanel {
    @Serial
    private static final long serialVersionUID = -4015833019658837000L;
    public ButtonGradient feedBackBtn, cancelBtn;
    private JLabel timeLabel, dateLabel, nameLabel;
    private JPanel dataPanel;
    private BoxIcon icon;

    public ReservationHistoryBox(String name, String time, String date, boolean isComplete) {
        super(30, 40, Color.white);
        setPreferredSize(new Dimension(1000, 190));

        feedBackBtn = new ButtonGradient("FeedBack", new Color(59, 151, 88));
        feedBackBtn.setActionCommand("FeedBack");

        cancelBtn = new ButtonGradient("Cancel", new Color(217, 76, 78));
        cancelBtn.setActionCommand("Cancel");

        nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Cousine", Font.BOLD, 25));
        dateLabel = new JLabel(date);
        dateLabel.setFont(new Font("Cousine", Font.PLAIN, 20));
        timeLabel = new JLabel(time);
        timeLabel.setFont(new Font("Cousine", Font.PLAIN, 20));
        dataPanel = new JPanel(new GridLayout(1, 5));
        dataPanel.setOpaque(false);
        icon = new BoxIcon(new ImageIcon("resource/icons/round-table.png"));

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

}
