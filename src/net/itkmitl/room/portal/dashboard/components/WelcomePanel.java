package net.itkmitl.room.portal.dashboard.components;

import net.itkmitl.room.portal.components.ButtonGradient;
import net.itkmitl.room.portal.components.RoundedPanel;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class WelcomePanel extends RoundedPanel {
    private JLabel t1, t2, t3, rightLabel;
    private JPanel p1, p2, p3;
    private JPanel leftPanel, rightPanel, txt, iconPanel;
    private BoxIcon welcomeIcon;
    private ButtonGradient bookingButton;


    private static final long serialVersionUID = -4015833019658837000L;

    public WelcomePanel() {
        super();

//        this.setLayout(new GridLayout(1, 2));
//        leftPanel = new JPanel();
//        rightPanel = new JPanel();
        txt = new RoundedPanel(100, 100);

//        txt.setBackground(new Color(0,0,0,0));
        t1 = new JLabel("Welcome to Laew Tae Hong");
        t2 = new JLabel("Simple and Convenient");
        t3 = new JLabel("Get started");

        bookingButton = new ButtonGradient();

        t1.setFont(new Font("Cousin", Font.BOLD, 12));
        t1.setForeground(Color.BLACK);
        t1.setBounds(79, 99, 505, 90);
        t1.setHorizontalAlignment(JLabel.LEFT);
        t1.setVerticalAlignment(JLabel.CENTER);

        t2.setFont(new Font("Cousin", Font.BOLD, 22));
        t2.setForeground(Color.BLACK);
        t2.setBounds(0, 0, 505, 90);
        t2.setHorizontalAlignment(JLabel.LEFT);
        t2.setVerticalAlignment(JLabel.CENTER);
//        p2.add(t2);

        t3.setFont(new Font("Inter", Font.BOLD, 12));
        t3.setForeground(Color.decode("#336B9E"));
        t3.setBounds(79, 99, 505, 90);
        t3.setHorizontalAlignment(JLabel.LEFT);
        t3.setVerticalAlignment(JLabel.CENTER);

        txt.setBackground(Color.WHITE);
        txt.add(t1);
        txt.add(t2);
        txt.add(t3);
        txt.add(bookingButton);
        txt.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));
        txt.setLayout(new GridLayout(4, 1));

        welcomeIcon = new BoxIcon(new ImageIcon("resource/icons/image27.png"));
        welcomeIcon.setSize(260, 260);

        bookingButton.getSizeSpeed();
        bookingButton.setColor1(new Color(39, 77, 191, 153));
        bookingButton.setColor2(new Color(45, 125, 216));
        bookingButton.setText("Booking Now!");
        bookingButton.setFont(new Font("Cousin", Font.BOLD, 11));
        bookingButton.setHorizontalTextPosition(SwingConstants.LEFT);
        bookingButton.setPreferredSize(new Dimension(2, 2));

        this.setLayout(new GridLayout(1,2));
        this.add(txt);
        this.add(welcomeIcon);
//        this.add(t1);
//        this.add(t2);
//        this.add(t3);

        this.setBackground(new Color(255, 255, 255));
        this.setMaximumSize(new Dimension(1300, 400));
        this.setAlignmentX(CENTER_ALIGNMENT);


    }
}
