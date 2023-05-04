package net.itkmitl.room.portal.dashboard.components;

import net.itkmitl.room.portal.components.RoundedPanel;

import java.awt.*;

import javax.swing.*;

public class ReservationPanel extends RoundedPanel {
    /**
     *
     */
    private static final long serialVersionUID = -2988916769883492575L;

    public JLabel reservationTextLabel;

    public static final String[] ROUTES = {"History", "Booking"};
    public static JButton[] buttonBox = new JButton[2];

    public ReservationPanel() {
        super(30, 30);
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(1, 3));
        this.setMaximumSize(new Dimension(1300, 200));
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setOpaque(false);

        for (String route : ROUTES) {
            this.add(new ReservationBox(route));
        }
    }
}
