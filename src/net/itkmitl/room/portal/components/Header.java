package net.itkmitl.room.portal.components;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Header extends TransparentPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 752500660828855659L;
	public JLabel headerLabel, descriptionLabel;

    public Header(String header, String description) {
        super(new GridBagLayout());
        setBorder(new EmptyBorder(25, 25, 25 ,25));
        headerLabel = new JLabel(header);
        headerLabel.setFont(new Font("Cousine", Font.BOLD, 24));
        descriptionLabel = new JLabel(description);
        descriptionLabel.setFont(new Font("Cousine", Font.PLAIN, 14));


        this.add(headerLabel, new GBCBuilder(GridBagConstraints.BOTH,1, 0,0).setAnchor(GridBagConstraints.WEST));
        this.add(descriptionLabel, new GBCBuilder(GridBagConstraints.BOTH,1, 0,1, new Insets(5, 0, 0, 0)).setAnchor(GridBagConstraints.WEST));
    }
}
