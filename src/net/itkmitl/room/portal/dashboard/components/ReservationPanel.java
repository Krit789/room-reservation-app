package net.itkmitl.room.portal.dashboard.components;

import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.RoundedPanel;
import net.itkmitl.room.portal.components.TransparentPanel;

import java.awt.*;

import javax.swing.*;

public class ReservationPanel extends TransparentPanel {
    /**
     *
     */
    private static final long serialVersionUID = -2988916769883492575L;

    public JLabel reservationTextLabel;

    public static final String[] ROUTES = {"History", "Booking"};
    public static JButton[] buttonBox = new JButton[2];

    public ReservationPanel() {
//        this.setBackground(Color.white);
        this.setLayout(new GridBagLayout());
        this.setMaximumSize(new Dimension(1300, 200));
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setOpaque(false);

        this.add(new ReservationBox(ROUTES[0]), new GBCBuilder(GridBagConstraints.BOTH, 0.4, 1, 0, 0).getGBC());
        this.add(Box.createHorizontalStrut(50), new GBCBuilder(GridBagConstraints.BOTH, 0.2, 1, 1, 0).getGBC());
        this.add(new ReservationBox(ROUTES[1]), new GBCBuilder(GridBagConstraints.BOTH, 0.4, 1, 2, 0).getGBC());

    }
}
