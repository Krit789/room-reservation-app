package net.itkmitl.room.portal.dashboard.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePanel extends RoundedPanel {
    /**
     *
     */
    private static final long serialVersionUID = -4015833019658837000L;

    public WelcomePanel() {
        super();

        this.setBackground(new Color(255, 255, 255));
        this.add(new JLabel("Hello, Welcome. Have Room, Have Condom."));
        this.setMaximumSize(new Dimension(1300, 400));
        this.setAlignmentX(CENTER_ALIGNMENT);
    }
}
