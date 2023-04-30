package net.itkmitl.room.portal.admin.views;

import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OperationWindowView {
    public final JInternalFrame frame;
    public final JPanel topPanel, contentPanel, userTabPanel, roomTabPanel, reservationTabPanel, feedbackTabPanel;
    public final JLabel title, menuDesc;
    public final JTabbedPane menuSelect;

    // User TabPane
    public final JButton viewUser, manageUser, lookupUser;
    public final JLabel viewUserLabel, manageUserLabel, lookupUserLabel;

    // User TabPane
    public final JButton viewRoom, manageRoom, lookupRoom;
    public final JLabel viewRoomLabel, manageRoomLabel, lookupRoomLabel;

    // Reservation TabPane
    public final JButton viewReservation, manageReservation, lookupReservation;
    public final JLabel viewReservationLabel, manageReservationLabel, lookupReservationLabel;

    // Feedback TabPane
    public final JButton viewFeedback, manageFeedback, lookupFeedback;
    public final JLabel viewFeedbackLabel, manageFeedbackLabel, lookupFeedbackLabel;

    public OperationWindowView() {
        frame = new JInternalFrame(("Main Menu"), false, false, false, true);
        frame.setFrameIcon(new ImageIcon("resource/icons/icon-16px.png"));
        frame.setSize(512, 300);
        frame.setLayout(new BorderLayout());
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        topPanel = new JPanel();
        topPanel.setBorder(new EmptyBorder(10, 10, 5, 10));
        frame.add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Title Text
        title = new JLabel("Welcome to Laew Tae Hong System");
        menuDesc = new JLabel("Choose categories you want to interact with below.");
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

        // User TabPanel
        userTabPanel.setLayout(new GridBagLayout());

        viewUser = new JButton("View");
        manageUser = new JButton("Manage");
        lookupUser = new JButton("Lookup");

        viewUserLabel = new JLabel("Display all users and their data.");
        manageUserLabel = new JLabel("Create, delete or make changes to users data.");
        lookupUserLabel = new JLabel("Lookup each users by their corresponding data.");

        userTabPanel.add(viewUser, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.3, 0, 0, new Insets(5, 10, 0, 0)).setPadding(20, 0));
        userTabPanel.add(viewUserLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.7, 1, 0, new Insets(0, 10, 0, 0)).getGBC());
        userTabPanel.add(manageUser, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.3, 0, 1, new Insets(5, 10, 0, 0)).setPadding(20, 0));
        userTabPanel.add(manageUserLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.7, 1, 1, new Insets(0, 10, 0, 0)).getGBC());
        userTabPanel.add(lookupUser, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.3, 0, 2, new Insets(5, 10, 0, 0)).setPadding(20, 0));
        userTabPanel.add(lookupUserLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.7, 1, 2, new Insets(0, 10, 0, 0)).getGBC());

        // Room TabPanel
        roomTabPanel.setLayout(new GridBagLayout());

        viewRoom = new JButton("View");
        manageRoom = new JButton("Manage");
        lookupRoom = new JButton("Lookup");

        viewRoomLabel = new JLabel("Display all rooms and their status.");
        manageRoomLabel = new JLabel("Create, delete or make changes to rooms data.");
        lookupRoomLabel = new JLabel("Lookup each rooms by their corresponding data.");

        roomTabPanel.add(viewRoom, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.3, 0, 0, new Insets(5, 10, 0, 0)).setPadding(20, 0));
        roomTabPanel.add(viewRoomLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.7, 1, 0, new Insets(0, 10, 0, 0)).getGBC());
        roomTabPanel.add(manageRoom, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.3, 0, 1, new Insets(5, 10, 0, 0)).setPadding(20, 0));
        roomTabPanel.add(manageRoomLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.7, 1, 1, new Insets(0, 10, 0, 0)).getGBC());
        roomTabPanel.add(lookupRoom, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.3, 0, 2, new Insets(5, 10, 0, 0)).setPadding(20, 0));
        roomTabPanel.add(lookupRoomLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.7, 1, 2, new Insets(0, 10, 0, 0)).getGBC());

        // Reservation TabPanel
        reservationTabPanel.setLayout(new GridBagLayout());

        viewReservation = new JButton("View");
        manageReservation = new JButton("Manage");
        lookupReservation = new JButton("Lookup");

        viewReservationLabel = new JLabel("Display all reservations.");
        manageReservationLabel = new JLabel("Create, delete or make changes to each reservations.");
        lookupReservationLabel = new JLabel("Lookup each reservations by their corresponding data.");

        reservationTabPanel.add(viewReservation, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.3, 0, 0, new Insets(5, 10, 0, 0)).setPadding(20, 0));
        reservationTabPanel.add(viewReservationLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.7, 1, 0, new Insets(0, 10, 0, 0)).getGBC());
        reservationTabPanel.add(manageReservation, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.3, 0, 1, new Insets(5, 10, 0, 0)).setPadding(20, 0));
        reservationTabPanel.add(manageReservationLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.7, 1, 1, new Insets(0, 10, 0, 0)).getGBC());
        reservationTabPanel.add(lookupReservation, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.3, 0, 2, new Insets(5, 10, 0, 0)).setPadding(20, 0));
        reservationTabPanel.add(lookupReservationLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.7, 1, 2, new Insets(0, 10, 0, 0)).getGBC());

        // Feedback TabPanel
        feedbackTabPanel.setLayout(new GridBagLayout());

        viewFeedback = new JButton("View");
        manageFeedback = new JButton("Manage");
        lookupFeedback = new JButton("Lookup");

        viewFeedbackLabel = new JLabel("Display all feedbacks and their properties.");
        manageFeedbackLabel = new JLabel("Manage, remove or make changes to feedbacks data.");
        lookupFeedbackLabel = new JLabel("Lookup each feedbacks by their corresponding attribute.");

        feedbackTabPanel.add(viewFeedback, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.3, 0, 0, new Insets(5, 10, 0, 0)).setPadding(20, 0));
        feedbackTabPanel.add(viewFeedbackLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.7, 1, 0, new Insets(0, 10, 0, 0)).getGBC());
        feedbackTabPanel.add(manageFeedback, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.3, 0, 1, new Insets(5, 10, 0, 0)).setPadding(20, 0));
        feedbackTabPanel.add(manageFeedbackLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.7, 1, 1, new Insets(0, 10, 0, 0)).getGBC());
        feedbackTabPanel.add(lookupFeedback, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.3, 0, 2, new Insets(5, 10, 0, 0)).setPadding(20, 0));
        feedbackTabPanel.add(lookupFeedbackLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.7, 1, 2, new Insets(0, 10, 0, 0)).getGBC());

    }
    public JInternalFrame getFrame() {
        return frame;
    }
}
