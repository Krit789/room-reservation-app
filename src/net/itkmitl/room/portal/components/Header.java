package net.itkmitl.room.portal.components;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Header extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 752500660828855659L;
	public JLabel headerLabel, descriptionLabel;

    public Header(String header, String description) {
        super(new GridLayout(2, 1));
        headerLabel = new JLabel(header);
        descriptionLabel = new JLabel(description);

        this.add(headerLabel);
        this.add(descriptionLabel);
    }
}
