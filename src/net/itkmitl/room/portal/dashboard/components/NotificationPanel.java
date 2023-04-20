package net.itkmitl.room.portal.dashboard.components;

import javax.swing.*;
import java.awt.*;

public class NotificationPanel extends JPanel {
    public JLabel notificationTextLabel;

    public NotificationPanel() {
        super();

        notificationTextLabel = new JLabel("You have a reservation for Room XXX in 19.00 - 20.00");

        this.setBackground(new Color(255, 255, 255, 200));
        this.setMaximumSize(new Dimension(1300, 50));
        this.setAlignmentX(CENTER_ALIGNMENT);

        notificationTextLabel.setFont(new Font("Inter", Font.BOLD, 16));
        notificationTextLabel.setVerticalAlignment(SwingConstants.CENTER);

        this.add(notificationTextLabel, BorderLayout.CENTER);
    }
}
