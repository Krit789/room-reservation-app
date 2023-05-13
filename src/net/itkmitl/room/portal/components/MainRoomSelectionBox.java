package net.itkmitl.room.portal.components;

import net.itkmitl.room.enums.EnumRoomState;
import net.itkmitl.room.libs.phatsanphon.entity.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainRoomSelectionBox extends RoundedPanel implements ActionListener, MouseListener {
    /**
     *
     */
    private static final long serialVersionUID = 3939071942263200950L;
    public JButton name;
    private Room boxRoom;
    //key is floor, Value is arraylist of room in floor
//    private J

    public MainRoomSelectionBox(Room room) {
        super(40, 60);
        name = new JButton(room.getName());
        if (!room.getState().equals(EnumRoomState.AVAILABLE)) {
            name.setText(String.format("%s (%s)", name.getText(), room.getState().getName()));
            setBackgroundColor(Color.GRAY);
        } else {
            setBackgroundColor(Color.WHITE);
            name.addActionListener(this);
            this.addMouseListener(this);
            name.addMouseListener(this);
        }
        name.setFont(new Font("Cousine", Font.PLAIN, 18));
        name.setOpaque(false);
        name.setContentAreaFilled(false);
        boxRoom = room;
        add(name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("BoxRoom MainRoomSelectionBox " + boxRoom.getId());
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackgroundColor(Color.LIGHT_GRAY);
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackgroundColor(Color.WHITE);
        repaint();

    }
}
