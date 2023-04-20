package net.itkmitl.room.portal.dashboard.components;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    public WelcomePanel() {
        super();

        this.setBackground(new Color(255, 255, 255));
        this.add(new JLabel("Hello, Welcome. Have Room, Have Condom."));
        this.setMaximumSize(new Dimension(1300, 400));
        this.setAlignmentX(CENTER_ALIGNMENT);
    }
}
