package net.itkmitl.room.portal.content.components.history;

import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.portal.content.MainContentView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FeedBackDialogueController implements ActionListener, WindowListener {
    public FeedBackDialogue view;
    private final Room myRoom;

    public FeedBackDialogueController(JFrame parent, Room room) {
        myRoom = room;
        view = new FeedBackDialogue(parent/*, room*/);
        dataFetch();
        view.addWindowListener(this);
        view.submitBtn.addActionListener(this);
        view.cancelBtn.addActionListener(this);
        view.ratingGroup.clearSelection();
        view.rating00.addActionListener(this);
        view.rating10.addActionListener(this);
        view.rating15.addActionListener(this);
        view.rating20.addActionListener(this);
        view.rating25.addActionListener(this);
        view.rating30.addActionListener(this);
        view.rating35.addActionListener(this);
        view.rating40.addActionListener(this);
        view.rating45.addActionListener(this);
        view.rating50.addActionListener(this);
    }

    private void disableGlassPane() {
        MainContentView.glassPane.setVisible(false);
        MainContentView.glassPane.setEnabled(false);
    }

    private void dataFetch() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.cancelBtn)) {
            view.dispose();
            disableGlassPane();
        } else if (e.getSource() instanceof JRadioButton & !view.submitBtn.isEnabled()) {
            view.submitBtn.setEnabled(true);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        disableGlassPane();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
