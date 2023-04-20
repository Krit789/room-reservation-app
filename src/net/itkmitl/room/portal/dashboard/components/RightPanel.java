package net.itkmitl.room.portal.dashboard.components;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
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
