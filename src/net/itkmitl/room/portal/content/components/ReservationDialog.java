package net.itkmitl.room.portal.content.components;

import net.itkmitl.room.portal.content.MainContentView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ReservationDialog extends JDialog implements WindowListener {
    private JComboBox<String> hourBox;
    private JComboBox<String> minuteBox;
    private boolean isCancelled;

    public ReservationDialog(JFrame parent) {
        super(parent, "Select Time", true); // true indicates modal dialog
        addWindowListener(this);
        isCancelled = true; // set cancelled flag to true by default
        setLayout(new BorderLayout());
        JPanel timePanel = new JPanel();
        timePanel.add(new JLabel("Hour:"));
        hourBox = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            hourBox.addItem(String.format("%02d", i));
        }
        timePanel.add(hourBox);
        timePanel.add(new JLabel("Minute:"));
        minuteBox = new JComboBox<>();
        for (int i = 0; i < 60; i += 5) {
            minuteBox.addItem(String.format("%02d", i));
        }
        timePanel.add(minuteBox);
        add(timePanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isCancelled = false;
                dispose(); // close the dialog
            }
        });
        buttonPanel.add(okButton);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // close the dialog
            }
        });
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
        pack(); // pack the components to fit the preferred size
        setLocationRelativeTo(parent); // center the dialog relative to the parent frame
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        MainContentView.glassPane.setVisible(false);
        MainContentView.glassPane.setEnabled(false);
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
