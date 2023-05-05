package net.itkmitl.room.portal.components;

import net.itkmitl.room.libs.phatsanphon.entity.Room;

import javax.swing.*;
import java.awt.Color;

public class MainRoomSelectionBox extends RoundedPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3939071942263200950L;
    public JButton name;
    private Room boxRoom;
    //key is floor, Value is arraylist of room in floor
//    private J

    public MainRoomSelectionBox(Room room){
        super(30, 40, Color.WHITE);
        name = new JButton(room.getName());
        name.setActionCommand(String.format("reserveRoom_%d" ,room.getId()));
        name.setOpaque(false);
        name.setContentAreaFilled(false);
//        name.setBorderPainted(false);
        add(name);
    }
}
