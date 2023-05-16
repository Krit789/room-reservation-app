package net.itkmitl.room.portal.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Header extends TransparentPanel {
    /**
     *
     */
    private static final long serialVersionUID = 752500660828855659L;
    public JLabel headerLabel, descriptionLabel;

    public Header(String header, String description) {
        super(new GridBagLayout());
        setBorder(new EmptyBorder(25, 25, 25, 25));
        headerLabel = new JLabel(header);
        headerLabel.setFont(new Font("Cousine", Font.BOLD, 40));
        descriptionLabel = new JLabel(description);
        descriptionLabel.setFont(new Font("Cousine", Font.PLAIN, 18));


        this.add(headerLabel, new GBCBuilder(GridBagConstraints.BOTH, 1, 0, 0).setAnchor(GridBagConstraints.WEST));
        this.add(descriptionLabel, new GBCBuilder(GridBagConstraints.BOTH, 1, 0, 1, new Insets(5, 10, 0, 0)).setAnchor(GridBagConstraints.WEST));
    }
}
