package net.itkmitl.room.portal.dashboard.components;

import net.itkmitl.room.portal.components.ButtonGradient;
import net.itkmitl.room.portal.components.RoundedPanel;
import net.itkmitl.room.portal.components.TransparentPanel;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import static net.itkmitl.room.portal.dashboard.components.ReservationPanel.buttonBox;

public class ReservationBox extends RoundedPanel {
    /**
     *
     */
    private static final long serialVersionUID = 3285944903070661887L;
    public ButtonGradient redirectButton;
    private BoxIcon icon;

    public ReservationBox(String title) {
        super(30, 30);
        this.setLayout(new BorderLayout());
        this.setMaximumSize(new Dimension(200, 200));
        this.setBorder(new EmptyBorder(30, 30, 30, 30));

        redirectButton = new ButtonGradient(title.toUpperCase(), new Color(44, 102, 188), new Color(94, 135, 197));
        redirectButton.setFont(new Font("Cousine", Font.BOLD, 18));
        redirectButton.setSizeSpeed(30f);
        redirectButton.setActionCommand(title);
        if (title.equals("History")) {
            icon = new BoxIcon(new ImageIcon("resource/icons/history.png"));
            icon.setSize(160, 160);
            this.add(icon, BorderLayout.CENTER);
            buttonBox[0] = redirectButton;
        } else if (title.equals("Booking")) {
            icon = new BoxIcon(new ImageIcon("resource/icons/booking.png"));
            icon.setSize(160, 160);
            this.add(icon, BorderLayout.CENTER);
            buttonBox[1] = redirectButton;
        } else {
            JLabel titleLabel = new JLabel(title, JLabel.CENTER);
            this.add(titleLabel, BorderLayout.NORTH);
        }
        this.add(redirectButton, BorderLayout.SOUTH);
    }
}
