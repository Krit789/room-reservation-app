package net.itkmitl.room.portal.dashboard.components;

import net.itkmitl.room.portal.components.TransparentPanel;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import static net.itkmitl.room.portal.dashboard.components.ReservationPanel.buttonBox;

public class ReservationBox extends TransparentPanel {
    /**
     *
     */
    private static final long serialVersionUID = 3285944903070661887L;
    public JButton redirectButton;
    private BoxIcon icon;

    public ReservationBox(String title) {
        this.setLayout(new BorderLayout());
        this.setMaximumSize(new Dimension(200, 200));
        this.setBorder(new EmptyBorder(30, 30, 30, 30));

        redirectButton = new JButton(title.toUpperCase());
        redirectButton.setActionCommand(title);
        if(title.equals("History")){
            icon = new BoxIcon(new ImageIcon("resource/icons/history.png"));
            icon.setSize(160, 160);
            this.add(icon, BorderLayout.CENTER);
            buttonBox[0] = redirectButton;
        }else if(title.equals("Booking")){
            icon = new BoxIcon(new ImageIcon("resource/icons/booking.png"));
            icon.setSize(160, 160);
            this.add(icon, BorderLayout.CENTER);
            buttonBox[1] = redirectButton;
        }else{
            JLabel titleLabel = new JLabel(title, JLabel.CENTER);
            this.add(titleLabel, BorderLayout.CENTER);
        }

        this.add(redirectButton, BorderLayout.SOUTH);
    }
}
