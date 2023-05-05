package net.itkmitl.room.portal.components;

import net.itkmitl.room.libs.phatsanphon.entity.Room;

import javax.swing.*;
import java.awt.Color;

public class MainRoomSelectionBox extends RoundedPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3939071942263200950L;
    private JLabel nameLabel;
    private Room boxRoom;
    //key is floor, Value is arraylist of room in floor
//    private J

    public MainRoomSelectionBox(Room room){
        super(30, 40, Color.WHITE);

    }
}
