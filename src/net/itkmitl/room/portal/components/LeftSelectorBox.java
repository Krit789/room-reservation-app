package net.itkmitl.room.portal.components;

import javax.swing.*;
import java.awt.*;

public class LeftSelectorBox extends JButton {
    /**
     *
     */
    private static final long serialVersionUID = -3861775095110257593L;
    public int boxID;
    public String type = "Select Box ";
    public boolean selected = false;

    public LeftSelectorBox(String name, int ID) {
        super(name);
        boxID = ID;
        this.setFont(new Font("Cousine", Font.BOLD, 20));
        this.setBackground(new Color(44, 102, 188));
        this.setForeground(Color.WHITE);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }
}
