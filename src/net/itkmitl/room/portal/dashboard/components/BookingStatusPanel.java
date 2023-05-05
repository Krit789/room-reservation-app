package net.itkmitl.room.portal.dashboard.components;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.RoundedPanel;

public class BookingStatusPanel extends RoundedPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 84550694890228683L;
	public JLabel titleLabel;

    public BookingStatusPanel() {
        super(30, 40, new Color(156, 199, 223), new Color(181, 191, 224));

        this.setLayout(new GridBagLayout());
        this.setBorder(new EmptyBorder(15, 15, 15, 15));
        this.setBackground(new Color(234, 234, 234));
        this.setPreferredSize(
                new Dimension(200, 400)
        );

        titleLabel = new JLabel("Room Booking Status");
        titleLabel.setFont(new Font("Cousin", Font.BOLD, 20));

        this.add(titleLabel, new GBCBuilder(GridBagConstraints.NONE, 0, 1, 0, 0).setAnchor(GridBagConstraints.NORTH));
    }
}
