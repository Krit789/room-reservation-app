package net.itkmitl.room.portal.components;

import net.itkmitl.room.libs.phatsanphon.entity.Room;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFloorSelectionBox extends RoundedPanel {
    private static final long serialVersionUID = 3939071942263200950L;
    private JLabel nameLabel;
    private JScrollPane roomHolder;
    private Room boxRoom;
    public MainFloorSelectionBox(String name, int size){
        super(30, 40, new Color(16, 52, 105), new Color(120, 164, 205));
        nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Cousine", Font.PLAIN, 18));
        nameLabel.setForeground(Color.WHITE);
        namePanel = new TransparentPanel();
        namePanel.setLayout(new GridBagLayout());
        namePanel.add(nameLabel, new GBCBuilder(GridBagConstraints.CENTER, 1, 0, 0, new Insets(25, 25, 25, 25)).getGBC());

        this.setLayout(new BorderLayout());
        roomPanel = new TransparentPanel();
        roomPanel.setLayout(new GridBagLayout());
        roomHolder = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        roomHolder.setViewportView(roomPanel);
        roomHolder.getViewport().setOpaque(false);
        roomHolder.setOpaque(false);
        roomHolder.setBorder(null);
        this.add(namePanel, BorderLayout.NORTH);
        this.add(roomHolder, BorderLayout.CENTER);
    }
}
