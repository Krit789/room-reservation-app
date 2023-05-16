package net.itkmitl.room.portal.content.components.dashboard;

import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.TransparentPanel;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class ReservationPanel extends TransparentPanel {
    public static final String[] ROUTES = {"History", "Booking"};
    @Serial
    private static final long serialVersionUID = -2988916769883492575L;
    public static JButton[] buttonBox = new JButton[2];
    public JLabel reservationTextLabel;

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
