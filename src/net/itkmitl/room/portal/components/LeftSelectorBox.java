package net.itkmitl.room.portal.components;

import javax.swing.*;
import java.awt.*;

public class LeftSelectorBox extends JButton {
    public int boxID;
    public String type = "Select Box ";
    public LeftSelectorBox(String name, int ID){
        super(name);
        boxID = ID;
        this.setFont(new Font("Cousine", Font.BOLD, 20));
        this.setForeground(Color.WHITE);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }
}
