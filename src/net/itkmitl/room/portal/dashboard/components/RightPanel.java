package net.itkmitl.room.portal.dashboard.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RightPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3515411566688472487L;
	public JLabel nameLabel;

    public RightPanel() {
        super();

        this.setBackground(new Color(255, 255, 255));
        this.setPreferredSize(
                new Dimension(534, (int) this.getBounds().getSize().getHeight())
        );

        nameLabel = new JLabel("Hello");
        nameLabel.setFont(new Font("Calibri", Font.BOLD, 20));

        this.add(nameLabel);
    }
}
