package net.itkmitl.room.portal.content.components.history;

import net.itkmitl.room.portal.components.ButtonGradient;
import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.RoundedPanel;
import net.itkmitl.room.portal.dashboard.components.BoxIcon;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ReservationHistoryBox extends RoundedPanel {
    public ButtonGradient feedBackBtn, cancelBtn;
    private JLabel timeLabel, dateLabel, nameLabel;
    private JPanel dataPanel;
    private BoxIcon icon;
    public ReservationHistoryBox(String name, String time, String date){
        super(30, 40, Color.white);
        setSize(1400, 250);

        feedBackBtn = new ButtonGradient("FeedBack", Color.green);
        feedBackBtn.setActionCommand("FeedBack");

        cancelBtn = new ButtonGradient("Cancel", Color.red);
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
        add(feedBackBtn, BorderLayout.EAST);
    }

}
