package net.itkmitl.room.portal.dashboard.components;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ReservationBox extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 3285944903070661887L;
    public JButton redirectButton;
    private BoxIcon icon;

    public ReservationBox(String title) {
        super(new BorderLayout());
        this.setMaximumSize(new Dimension(200, 200));
        this.setBorder(new EmptyBorder(30, 30, 30, 30));

        if(title.equals("History")){
            icon = new BoxIcon(new ImageIcon("resource/icons/history.png"));
            icon.setSize(160, 160);
            this.add(icon, BorderLayout.CENTER);
        }else if(title.equals("Booking")){
            icon = new BoxIcon(new ImageIcon("resource/icons/booking.png"));
            icon.setSize(160, 160);
            this.add(icon, BorderLayout.CENTER);
        }else{
            JLabel titleLabel = new JLabel(title, JLabel.CENTER);
            this.add(titleLabel, BorderLayout.CENTER);
        }
        redirectButton = new JButton(title.toUpperCase());

        this.add(redirectButton, BorderLayout.SOUTH);
    }
}
