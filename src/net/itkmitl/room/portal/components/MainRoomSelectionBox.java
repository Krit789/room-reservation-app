package net.itkmitl.room.portal.components;

import net.itkmitl.room.enums.EnumRoomState;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.portal.View;
import net.itkmitl.room.portal.content.MainContentView;
import net.itkmitl.room.portal.content.components.ReservationDialog;
import net.itkmitl.room.portal.content.components.ReservationDialogController;

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
    public static boolean dialogOpen;
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

    public void openReservationDialog(){
        SwingWorker<?, ?> worker = new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() throws Exception {
                ReservationDialogController rsvpd = new ReservationDialogController(null, boxRoom);
                MainContentView.glassPane.setSpinnerVisibility(false);
                MainContentView.glassPane.setText("");
                MainContentView.glassPane.setVisible(true);
                MainContentView.glassPane.setEnabled(true);
                rsvpd.view.setVisible(true);
                return null;
            }
            @Override
            protected void done(){
                dialogOpen = false;
            }

        };
        worker.execute();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!dialogOpen) {
            openReservationDialog();
            dialogOpen = true;
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (!dialogOpen) {
            openReservationDialog();
            dialogOpen = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setBackgroundColor(new Color(140, 187, 255));
        repaint();
        if (!dialogOpen) {
            openReservationDialog();
            dialogOpen = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setBackgroundColor(Color.LIGHT_GRAY);
        repaint();
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
