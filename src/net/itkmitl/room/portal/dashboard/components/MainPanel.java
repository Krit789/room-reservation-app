package net.itkmitl.room.portal.dashboard.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel() {
        super(new BorderLayout());
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(30, 70, 30, 70));
    }
}
