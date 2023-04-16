package net.itkmitl.room.portal.admin;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.formdev.flatlaf.FlatIntelliJLaf;
import net.itkmitl.room.GUIStarter;
import net.itkmitl.room.MacConfig;
import net.itkmitl.room.portal.admin.components.*;

public class BaseWindow extends ComponentAdapter implements ActionListener, InternalFrameListener {
    private OperationWindow mainMenu;
    private final JFrame baseFrame;
    private final JPanel statusBar;
    private final JMenuBar menuBar;
    private final JMenu fileMenu, windowMenu, optionMenu, helpMenu;
    private final JMenuItem fileMenuItem1, fileMenuItem2, fileMenuItem3;
    private final JMenuItem optionMenuItem1, optionMenuItem2, optionMenuItem3, optionMenuItem4;
    private final JMenu newWindowMenu, windowListMenu;
    private final JMenuItem windowMenuItem1, windowMenuItem2, windowMenuItem3;
    private final JCheckBoxMenuItem windowCheckBoxMenuItem1;
    private final JMenuItem aboutMenuItem1;
    private static JDesktopPane desktop;
    private final JProgressBar progressBar;
    private final JLabel statusLabel;
    private ArrayList<Image> multiIcon;
    private HashMap<JInternalFrame, JMenuItem> windowList = new HashMap<>();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    ;

    private boolean autoCenterMainMenu = true;

    public BaseWindow() {
        baseFrame = new JFrame("Laew Tae Hong Management");
        multiIcon = new ArrayList<>();
        multiIcon.add(new ImageIcon("resource/icons/icon-208px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-128px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-64px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-56px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-48px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-40px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-20px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-16px.png").getImage());
        baseFrame.setIconImages(multiIcon);
        baseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        baseFrame.setMinimumSize(new Dimension(640, 480));
        baseFrame.setSize(screenSize);
        baseFrame.addComponentListener(this);
        baseFrame.setExtendedState(baseFrame.getExtendedState() | baseFrame.MAXIMIZED_BOTH);

        // Menu Components declaration
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        optionMenu = new JMenu("Options");
        windowMenu = new JMenu("Window");
        helpMenu = new JMenu("Help");

        // 'File' Menu Components declaration
        fileMenuItem1 = new JMenuItem("Status");
        fileMenuItem2 = new JMenuItem("View Logs");
        fileMenuItem3 = new JMenuItem("Exit");
        fileMenuItem3.addActionListener(this);
        // 'Options' Menu Components declaration
        optionMenuItem1 = new JMenuItem("Connect");
        optionMenuItem2 = new JMenuItem("Disconnect");
        optionMenuItem3 = new JMenuItem("Settings");
        optionMenuItem3.addActionListener(this);
        optionMenuItem4 = new JMenuItem("Switch to User Mode");
        optionMenuItem4.addActionListener(this);

        windowCheckBoxMenuItem1 = new JCheckBoxMenuItem("Auto Center Main Menu");
        windowCheckBoxMenuItem1.setState(autoCenterMainMenu);
        windowCheckBoxMenuItem1.setToolTipText("Automatically center Main Menu when Main Window is resized");
        windowCheckBoxMenuItem1.addActionListener(this);
        windowMenuItem2 = new JMenuItem("Cascade Window");
        windowMenuItem3 = new JMenuItem("Close all Window");
        newWindowMenu = new JMenu("New");
        windowListMenu = new JMenu("Opened Window");
        windowMenuItem1 = new JMenuItem("Main Menu");
        windowMenuItem1.setEnabled(false);
        windowMenuItem1.addActionListener(this);

        // 'About' Menu Components declaration
        aboutMenuItem1 = new JMenuItem("About Us");

        // Set JFrame layout to BorderLayout
        baseFrame.setLayout(new BorderLayout());

        // Adding Sub menu to menu items
        menuBar.add(fileMenu);
        fileMenu.add(fileMenuItem1);
        fileMenu.add(fileMenuItem2);
        fileMenu.add(new JSeparator());
        fileMenu.add(fileMenuItem3);

        menuBar.add(optionMenu);
        optionMenu.add(optionMenuItem4);
        optionMenu.add(new JSeparator());
        optionMenu.add(optionMenuItem1);
        optionMenu.add(optionMenuItem2);
        optionMenu.add(optionMenuItem3);

        menuBar.add(windowMenu);
        windowMenu.add(newWindowMenu);
        newWindowMenu.add(windowMenuItem1);
        windowMenu.add(windowListMenu);
        windowMenu.add(new JSeparator());
        windowMenu.add(windowCheckBoxMenuItem1);
        windowMenu.add(windowMenuItem2);
        windowMenuItem2.addActionListener(this);
        windowMenu.add(windowMenuItem3);
        menuBar.add(helpMenu);
        aboutMenuItem1.addActionListener(this);
        helpMenu.add(aboutMenuItem1);

        baseFrame.setJMenuBar(menuBar);


        desktop = new JDesktopPane();
        desktop.setBackground(new Color(51, 126, 185));
        baseFrame.add(desktop, BorderLayout.CENTER);

        // create the status bar panel
        statusBar = new JPanel();
        statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        // set itself to the bottom of the page
        baseFrame.add(statusBar, BorderLayout.SOUTH);
        statusBar.setPreferredSize(new Dimension(baseFrame.getWidth(), 24));

        // Status bar
        statusBar.setLayout(new GridBagLayout());
        statusLabel = new JLabel("Ready.");
        statusLabel.setMinimumSize(new Dimension(128, statusBar.getHeight()));

        // Adding JLabel to the left of statusBar
        statusBar.add(statusLabel, new GBCBuilder(GridBagConstraints.BOTH, 0.3, 0, 0, new Insets(0, 10, 0, 0)).setAnchor(GridBagConstraints.WEST));

        // Adding JPanel next to the JLabel as a filler
        statusBar.add(new JPanel(), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 1, 0).getGBC());

        // Adding JProgressBar to the right of statusBar
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(false);
        statusBar.add(progressBar, new GBCBuilder(GridBagConstraints.NONE, 0.2, 2, 0, new Insets(0, 10, 0, 10)).setAnchor(GridBagConstraints.EAST));


        // Setting Window size and boilerplate code
        baseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainMenu = spawnMainMenu();


        baseFrame.setVisible(true);
    }

    private OperationWindow spawnMainMenu() {
        OperationWindow oper = new OperationWindow();
        Dimension desktopSize = desktop.getSize();
        Dimension jInternalFrameSize = oper.getFrame().getSize();
        oper.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        oper.getFrame().addInternalFrameListener(this);
        oper.getFrame().show();
        oper.getFrame().setVisible(true);
        desktop.add(oper.getFrame());
        oper.getFrame().moveToFront();
        return oper;
    }

    private PreferenceWindowView spawnPreference() {
        PreferenceWindowView pref = new PreferenceWindowView();
        Dimension desktopSize = desktop.getSize();
        Dimension jInternalFrameSize = pref.getFrame().getSize();
        pref.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        pref.getFrame().addInternalFrameListener(this);
        pref.getFrame().show();
        pref.getFrame().setVisible(true);
        desktop.add(pref.getFrame());
        pref.getFrame().moveToFront();
        return pref;
    }

    public TableView spawnTableView() {
        TableView table = new TableView();
        Dimension desktopSize = desktop.getSize();
        Dimension jInternalFrameSize = table.getFrame().getSize();
        table.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        table.getFrame().addInternalFrameListener(this);
        table.getFrame().show();
        table.getFrame().setVisible(true);
        desktop.add(table.getFrame());
        table.getFrame().moveToFront();
        return table;
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(aboutMenuItem1)) {
            new AboutDialog(baseFrame);
        } else if (e.getSource().equals(fileMenuItem3)) {
            baseFrame.dispose();
            System.exit(0);
        } else if (e.getSource().equals(optionMenuItem3)) {
            spawnPreference();
        } else if (e.getSource().equals(optionMenuItem4)) {
            baseFrame.dispose();
            String[] arguments = new String[]{""};
            GUIStarter.main(arguments);
        } else if (e.getSource().equals(windowCheckBoxMenuItem1)) {
            windowCheckBoxMenuItem1.setState(!autoCenterMainMenu);
            autoCenterMainMenu = !autoCenterMainMenu;
            if (autoCenterMainMenu) {
                Dimension desktopSize = desktop.getSize();
                Dimension jInternalFrameSize = mainMenu.getFrame().getSize();
                mainMenu.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                        (desktopSize.height - jInternalFrameSize.height) / 2);
            }
        } else if (e.getSource().equals(windowMenuItem1)) {
            spawnMainMenu();
        } else if (e.getSource().equals(windowMenuItem2)) {
            for (JInternalFrame i : desktop.getAllFrames()
            ) {
                System.out.println(i.getTitle());
            }
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        try {
            if ((mainMenu != null) && (autoCenterMainMenu) && (!mainMenu.getFrame().isIcon())) {
                Dimension desktopSize = desktop.getSize();
                Dimension jInternalFrameSize = mainMenu.getFrame().getSize();
                mainMenu.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                        (desktopSize.height - jInternalFrameSize.height) / 2);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void internalFrameOpened(InternalFrameEvent e) {
        JMenuItem newItem = new JMenuItem(e.getInternalFrame().getTitle());
        windowList.put(e.getInternalFrame(), newItem);
        windowListMenu.add(newItem);
    }

    public void internalFrameClosing(InternalFrameEvent e) {
    }

    public void internalFrameClosed(InternalFrameEvent e) {
        windowListMenu.remove(windowList.get(e.getInternalFrame()));
        windowList.remove(e.getInternalFrame());
    }

    public void internalFrameIconified(InternalFrameEvent e) {
    }

    public void internalFrameDeiconified(InternalFrameEvent e) {
    }

    public void internalFrameActivated(InternalFrameEvent e) {
    }

    public void internalFrameDeactivated(InternalFrameEvent e) {
    }

    public static JDesktopPane getDesktop(){
        return desktop;
    }

    public static void main(String[] args) {
        try {
            MacConfig.menuBar("Laew Tae Hong Management");
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (Exception ignored) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            }

        } catch (Exception ignored) {

        }
        new BaseWindow(); //start the application
    }

    class OperationWindow extends InternalFrame implements ActionListener {
        private final JInternalFrame frame;
        private final JPanel topPanel, contentPanel, userTabPanel, roomTabPanel, reservationTabPanel, feedbackTabPanel;
        private final JLabel title, menuDesc;
        private final JTabbedPane menuSelect;

        // User TabPane
        private final JButton viewUser, manageUser, lookupUser;
        private final JLabel viewUserLabel, manageUserLabel, lookupUserLabel;

        // User TabPane
        private final JButton viewRoom, manageRoom, lookupRoom;
        private final JLabel viewRoomLabel, manageRoomLabel, lookupRoomLabel;

        // Reservation TabPane
        private final JButton viewReservation, manageReservation, lookupReservation;
        private final JLabel viewReservationLabel, manageReservationLabel, lookupReservationLabel;

        // Feedback TabPane
        private final JButton viewFeedback, manageFeedback, lookupFeedback;
        private final JLabel viewFeedbackLabel, manageFeedbackLabel, lookupFeedbackLabel;

        public OperationWindow() {
            frame = new JInternalFrame(("Main Menu"), false, false, false, true);

            frame.setFrameIcon(new ImageIcon("resource/icons/icon-16px.png"));
            frame.setSize(512, 300);
            frame.setLayout(new BorderLayout());

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
            viewUser.addActionListener(this);
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
        public void actionPerformed(ActionEvent e){
            if (e.getSource().equals(viewUser)){
                spawnTableView();
            }
        }
        @Override
        public JInternalFrame getFrame() {
            return frame;
        }

    }

}



