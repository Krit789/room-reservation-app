package net.itkmitl.room.portal.admin.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OperationWindow extends InternalFrame{
    private JInternalFrame frame;
    private JPanel topPanel, contentPanel, userTabPanel, roomTabPanel, reservationTabPanel, feedbackTabPanel;
    private JLabel title, menuDesc;
    private JTabbedPane menuSelect;
    private JButton viewUser, manageUser, lookupUser;
    private JLabel viewUserLabel, manageUserLabel, lookupUserLabel;
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

        userTabPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        viewUser = new JButton("View");
        manageUser = new JButton("Manage");
        lookupUser = new JButton("Lookup");

        viewUserLabel = new JLabel("View users");
        manageUserLabel = new JLabel("Make changes to users data");
        lookupUserLabel = new JLabel("Lookup each users by their data");

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        gbc.weightx = 0.3;
        gbc.gridx = 0;
        gbc.gridy = 0;
        userTabPanel.add(viewUser, gbc);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.7;
        gbc.insets = new Insets(0,10,0,0);
        gbc.gridx = 1;
        gbc.gridy = 0;
        userTabPanel.add(viewUserLabel, gbc);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        gbc.weightx = 0.3;
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        userTabPanel.add(manageUser, gbc);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.7;
        gbc.insets = new Insets(0,10,0,0);
        gbc.gridx = 1;
        gbc.gridy = 1;
        userTabPanel.add(manageUserLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        gbc.insets = new Insets(0,0,0,0);
        gbc.weightx = 0.3;
        gbc.gridx = 0;
        gbc.gridy = 2;
        userTabPanel.add(lookupUser, gbc);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.7;
        gbc.insets = new Insets(0,10,0,0);
        gbc.gridx = 1;
        gbc.gridy = 2;
        userTabPanel.add(lookupUserLabel, gbc);

    }

    @Override
    public JInternalFrame getFrame(){
        return frame;
    }

}
