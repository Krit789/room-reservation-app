package net.itkmitl.room.portal.components;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 561892552360870497L;
	public JLabel nameLabel;

    public LeftPanel() {
        super(new BorderLayout());

        this.setBackground(new Color(16, 52, 105));
        this.setPreferredSize(
                new Dimension(408, (int) this.getBounds().getSize().getHeight())
        );

        nameLabel = new JLabel("LAEW TAE HONG");
        nameLabel.setFont(new Font("Calibri", Font.BOLD, 25));
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);
        nameLabel.setForeground(Color.WHITE);

        this.add(nameLabel, BorderLayout.NORTH);
    }
}
