package net.itkmitl.room.portal.dashboard.components;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ReservationBox extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 3285944903070661887L;
    public JButton redirectButton;

    public ReservationBox(String title) {
        super(new BorderLayout());
        this.setMaximumSize(new Dimension(200, 200));
        this.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        redirectButton = new JButton(title.toUpperCase());

        this.add(titleLabel, BorderLayout.CENTER);
        this.add(redirectButton, BorderLayout.SOUTH);
    }
}
