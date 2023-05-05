package net.itkmitl.room.portal.dashboard.components;

import net.itkmitl.room.portal.components.RoundedPanel;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

public class WelcomePanel extends RoundedPanel {
    private JLabel t1, t2, t3;
    private JPanel p1, p2, p3;
    private JPanel leftPanel, rightPanel, txt;

    private static final long serialVersionUID = -4015833019658837000L;

    public WelcomePanel() {
        super();

        this.setLayout(new GridLayout(1, 2));
//        leftPanel = new JPanel();
//        rightPanel = new JPanel();
//        txt = new JPanel();

        t1 = new JLabel("Welcome to Laew Tae Hong");
        t2 = new JLabel("Simple and Convenient");
        t3 = new JLabel("Get started");

        t1.setFont(new Font("Cousin", Font.BOLD, 10));
        t1.setForeground(Color.BLACK);
        t1.setBounds(79, 99, 505, 45);
        t1.setHorizontalAlignment(JLabel.CENTER);
        t1.setVerticalAlignment(JLabel.CENTER);

        t2.setFont(new Font("Cousin", Font.BOLD, 40));
        t2.setForeground(Color.BLACK);
        t2.setBounds(0, 0, 505, 90);
        t2.setHorizontalAlignment(JLabel.CENTER);
        t2.setVerticalAlignment(JLabel.CENTER);
//        p2.add(t2);

        t3.setFont(new Font("Inter", Font.BOLD, 12));
        t3.setForeground(Color.decode("#336B9E"));
        t3.setBounds(79, 99, 505, 90);
        t3.setHorizontalAlignment(JLabel.LEFT);
        t3.setVerticalAlignment(JLabel.CENTER);

        this.add(t1);
        this.add(t2);
        this.add(t3);

        this.setBackground(new Color(255, 255, 255));
        this.setMaximumSize(new Dimension(1300, 400));
        this.setAlignmentX(CENTER_ALIGNMENT);


    }
}
