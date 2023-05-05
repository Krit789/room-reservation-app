package net.itkmitl.room.portal.components;

import net.itkmitl.room.libs.phatsanphon.entity.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainRoomSelectionBox extends RoundedPanel implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 3939071942263200950L;
    public JButton name;
    private Room boxRoom;
    //key is floor, Value is arraylist of room in floor
//    private J

    public MainRoomSelectionBox(Room room){
        super(40, 60, Color.WHITE);
        name = new JButton(room.getName());
        name.setFont(new Font("Cousine", Font.PLAIN, 18));
        name.setActionCommand(String.format("reserveRoom_%d" ,room.getId()));
        name.addActionListener(this);
        name.setOpaque(false);
        name.setContentAreaFilled(false);
        boxRoom = room;
//        name.setBorderPainted(false);
        add(name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(boxRoom.getId());
    }
}
