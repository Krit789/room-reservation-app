package net.itkmitl.room.portal.dashboard.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.itkmitl.room.portal.components.GBCBuilder;

public class NotificationPanel extends RoundedPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4305337873118944635L;
	public JLabel notificationTextLabel;

    public NotificationPanel() {
        super();
        this.setLayout(new GridBagLayout());
        notificationTextLabel = new JLabel("You have a reservation for Room XXX in 19.00 - 20.00");

        this.setBackground(new Color(255, 255, 255, 200));
        this.setMaximumSize(new Dimension(1300, 50));
//        this.setAlignmentX(CENTER_ALIGNMENT);

        notificationTextLabel.setFont(new Font("Inter", Font.BOLD, 16));
//        notificationTextLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.add(notificationTextLabel, new GBCBuilder(GridBagConstraints.CENTER, 1, 1, 0, 0).getGBC());
    }
}
