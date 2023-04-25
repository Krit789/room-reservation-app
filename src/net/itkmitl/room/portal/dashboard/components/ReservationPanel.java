package net.itkmitl.room.portal.dashboard.components;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReservationPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2988916769883492575L;

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
