package net.itkmitl.room.portal.dashboard.components;


import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.RoundedPanel;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class BookingStatusPanel extends RoundedPanel {

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
        titleLabel.setFont(new Font("Cousine", Font.BOLD, 20));

        this.add(titleLabel, new GBCBuilder(GridBagConstraints.NONE, 0, 1, 0, 0).setAnchor(GridBagConstraints.NORTH));
    }
}
