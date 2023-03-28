package net.itkmitl.room.portal.admin.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OperationWindow {
    private JInternalFrame frame;
    private JPanel topPanel, contentPanel, userTabPanel, roomTabPanel, reservationTabPanel, feedbackTabPanel;
    private JLabel title, menuDesc;
    private JTabbedPane menuSelect;
    public OperationWindow(){
        frame = new JInternalFrame(("Main Menu"), false, false, false, true);
        frame.setFrameIcon(new ImageIcon("resource/icon.png"));
        frame.setSize(512, 300);
        frame.setLayout(new BorderLayout());

        topPanel = new JPanel();
        topPanel.setBorder(new EmptyBorder(10, 10, 5, 10));
        frame.add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Title Text
        title = new JLabel("Welcome to Laew Tae Hong System");
        menuDesc = new JLabel("Choose categories you want to manage below.");
        title.setMinimumSize(new Dimension(300, 70));
        title.setFont(new Font("Arial", Font.BOLD, 25));
        topPanel.add(title);
        topPanel.add(menuDesc);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        menuDesc.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Tabbed Menu Pane
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        menuSelect = new JTabbedPane();
        contentPanel.add(menuSelect, BorderLayout.CENTER);
        frame.add(contentPanel);

        // Menu Content
        userTabPanel = new JPanel();
        menuSelect.add("User", userTabPanel);
        roomTabPanel = new JPanel();
        menuSelect.add("Room", roomTabPanel);
        reservationTabPanel = new JPanel();
        menuSelect.add("Reservation", reservationTabPanel);
        feedbackTabPanel = new JPanel();
        menuSelect.add("Feedback", feedbackTabPanel);




    }

    public JInternalFrame getFrame(){
        return frame;
    }

}
