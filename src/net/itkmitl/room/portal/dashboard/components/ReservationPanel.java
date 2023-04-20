package net.itkmitl.room.portal.dashboard.components;

import javax.swing.*;
import java.awt.*;

public class ReservationPanel extends JPanel {
    public JLabel reservationTextLabel;

    public static final String[] ROUTES = {"History", "Booking", "Calendar"};

    public ReservationPanel() {
        super(new GridLayout(1, 3));
        this.setMaximumSize(new Dimension(1300, 200));
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setOpaque(false);

        for (String route : ROUTES) {
            this.add(new ReservationBox(route));
        }
    }
}
