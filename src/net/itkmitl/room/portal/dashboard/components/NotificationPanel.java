package net.itkmitl.room.portal.dashboard.components;

import java.awt.*;

import javax.swing.*;

import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.RoundedPanel;
import net.itkmitl.room.portal.components.TransparentPanel;

public class NotificationPanel extends RoundedPanel {
    /**
     *
     */
    private static final long serialVersionUID = 4305337873118944635L;
    public JLabel notificationTextLabel, calendarIcon;
    private JPanel panelPanel;

    public NotificationPanel() {
        super();
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(255, 255, 255, 100));

        panelPanel = new TransparentPanel();
        panelPanel.setLayout(new FlowLayout());
        calendarIcon = new JLabel(new ImageIcon("resource/icons/calendar-32px.png"));
        notificationTextLabel = new JLabel("You have a reservation for Room XXX in 19.00 - 20.00");
        panelPanel.add(calendarIcon);
        panelPanel.add(Box.createHorizontalStrut(10));
        panelPanel.add(notificationTextLabel);
        panelPanel.setBackground(Color.white);
        this.setMaximumSize(new Dimension(1300, 50));

        notificationTextLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        this.add(panelPanel, new GBCBuilder(GridBagConstraints.NONE, 1, 1, 0, 0, new Insets(0, 10, 0, 0)).setAnchor(GridBagConstraints.WEST));
    }
}
