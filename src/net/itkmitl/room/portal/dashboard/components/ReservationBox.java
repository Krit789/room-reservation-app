package net.itkmitl.room.portal.dashboard.components;

import net.itkmitl.room.libs.phatsanphon.entity.Reservation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReservationBox extends JPanel {
    public JButton redirectButton;

    public ReservationBox(String title) {
        super(new BorderLayout());
        this.setMaximumSize(new Dimension(200, 200));
        this.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        redirectButton = new JButton(title.toUpperCase());

        this.add(titleLabel, BorderLayout.CENTER);
        this.add(redirectButton, BorderLayout.SOUTH);
    }
}
