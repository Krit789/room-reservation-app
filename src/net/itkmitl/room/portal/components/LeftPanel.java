package net.itkmitl.room.portal.components;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    public JLabel nameLabel;

    public LeftPanel() {
        super(new BorderLayout());

        this.setBackground(new Color(255, 255, 255));
        this.setPreferredSize(
                new Dimension(408, (int) this.getBounds().getSize().getHeight())
        );

        nameLabel = new JLabel("LAEW TAE HONG");
        nameLabel.setFont(new Font("Calibri", Font.BOLD, 20));

        this.add(nameLabel, BorderLayout.NORTH);
    }
}
