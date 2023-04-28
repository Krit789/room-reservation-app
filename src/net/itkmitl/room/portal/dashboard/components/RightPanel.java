package net.itkmitl.room.portal.dashboard.components;

import java.awt.*;

import javax.swing.*;

public class RightPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3515411566688472487L;
	public JLabel nameLabel;
    public BookingStatusPanel bookingPanel;

    public RightPanel() {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 50, 20, 50));
        this.setBackground(new Color(255, 255, 255));
        this.setPreferredSize(
                new Dimension(534, (int) this.getBounds().getSize().getHeight())
        );

        nameLabel = new JLabel("Hello");
        nameLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);
        bookingPanel = new BookingStatusPanel();

        this.add(nameLabel);
        this.add(bookingPanel);
    }
}
