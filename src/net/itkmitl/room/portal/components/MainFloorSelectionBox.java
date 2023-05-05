package net.itkmitl.room.portal.components;

import net.itkmitl.room.libs.phatsanphon.entity.Room;

import javax.swing.*;
import java.awt.*;

public class MainFloorSelectionBox extends RoundedPanel {
    private static final long serialVersionUID = 3939071942263200950L;
    private JLabel nameLabel;
    private JScrollPane roomHolder;
    private Room boxRoom;
    public MainFloorSelectionBox(String name, int size){
        super(30, 40, new Color(16, 52, 105), new Color(120, 164, 205));
        nameLabel = new JLabel(name);
        roomHolder = new JScrollPane(size, 1);
        this.setLayout(new GridBagLayout());
    }
}
