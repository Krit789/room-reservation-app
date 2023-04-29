package net.itkmitl.room.portal.components;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
    public JLabel headerLabel, descriptionLabel;

    public Header(String header, String description) {
        super(new GridLayout(2, 1));
        headerLabel = new JLabel(header);
        descriptionLabel = new JLabel(description);

        this.add(headerLabel);
        this.add(descriptionLabel);
    }
}
