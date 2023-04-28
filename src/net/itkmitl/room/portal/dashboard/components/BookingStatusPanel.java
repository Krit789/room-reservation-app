package net.itkmitl.room.portal.dashboard.components;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BookingStatusPanel extends JPanel{

    public JLabel titleLabel;
    public BookingStatusPanel(){
        super();

        this.setBackground(new Color(234, 234, 234));
        this.setPreferredSize(
                new Dimension(200, 400)
        );

        titleLabel = new JLabel("Room Booking Status");
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 20));

        this.add(titleLabel);
    }
}
