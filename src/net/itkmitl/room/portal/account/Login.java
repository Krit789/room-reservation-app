package net.itkmitl.room.portal.account;

import net.itkmitl.room.MacConfig;
import net.itkmitl.room.libs.peeranat.util.*;
import net.itkmitl.room.portal.admin.BaseWindow;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Login implements ActionListener {
    private JFrame baseFrame;
    private OutPane outerPane;
    private JButton loginButton;
    private JPanel innerPane, floatingBox, buttonBox;
    private JTextField usernameField;
    private JLabel usernameText, passwordText, loginHeader;
    private ImageIcon img_itbuilding;
    private JMenuBar menuBar;
    private JMenu fileMenu, windowMenu, optionMenu, helpMenu;
    private JMenuItem fileMenuItem1, fileMenuItem2, fileMenuItem3;
    private JMenuItem optionMenuItem1, helpMenuItem1;
    private JMenu newWindowMenu;
    private JMenuItem windowMenuItem1, windowMenuItem2, windowMenuItem3;
    private ArrayList<Image> multiIcon;
    private JPanel paneN, paneW, paneS, paneE;
    private JPasswordField passwordField;

    private final Insets insets = new Insets(400, 600, 400, 600);
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();;


    private void menuBarInitialize() {
        //----------------------- Menubar -----------------------
        // Menu Components declaration
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        optionMenu = new JMenu("Options");
        windowMenu = new JMenu("Window");
        helpMenu = new JMenu("Help");

        // 'File' Menu Components declaration
        fileMenuItem1 = new JMenuItem("New");
        fileMenuItem2 = new JMenuItem("View Rooms");
        fileMenuItem3 = new JMenuItem("Exit");
        fileMenuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // 'Options' Menu Components declaration
        optionMenuItem1 = new JMenuItem("Switch to Admin Mode");
        optionMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.dispose();
                String[] arguments = new String[]{""};
                BaseWindow.main(arguments);
            }
        });

        // 'Window' Menu Components declaration
        windowMenuItem1 = new JMenuItem("1");
        windowMenuItem2 = new JMenuItem("2");
        windowMenuItem3 = new JMenuItem("3");


        // 'Help' Menu Components declaration
        helpMenuItem1 = new JMenuItem("About Us");

        // Adding Sub menu to menu items
        menuBar.add(fileMenu);
        fileMenu.add(fileMenuItem1);
        fileMenu.add(fileMenuItem2);
        fileMenu.add(new JSeparator());
        fileMenu.add(fileMenuItem3);

        menuBar.add(optionMenu);
        optionMenu.add(optionMenuItem1);

        menuBar.add(windowMenu);
        windowMenu.add(windowMenuItem1);
        windowMenu.add(windowMenuItem2);
        windowMenu.add(windowMenuItem3);

        menuBar.add(helpMenu);
        helpMenu.add(helpMenuItem1);
    }

    private void outerPaneInitialize() {

        outerPane = new OutPane();
        outerPane.setPreferredSize(screenSize);
        outerPane.setBackground(new Color(15,27,47));

        outerPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Get the current size of the panel
                Dimension size = outerPane.getSize();
                int width = size.width;
                int height = size.height;

                // Calculate the new padding based on the current size
                int paddingSize = Math.min(width, height) / 5;
                Insets newInsets = new Insets(paddingSize, paddingSize, paddingSize, paddingSize);

                // Update the padding of the nested panel
                innerPane.setBorder(BorderFactory.createEmptyBorder(newInsets.top, newInsets.left, newInsets.bottom, newInsets.right));
                insets.top = newInsets.top;
                insets.left = newInsets.left;
                insets.bottom = newInsets.bottom;
                insets.right = newInsets.right;

                // Repaint the panel to update the layout
                outerPane.revalidate();
                outerPane.repaint();
            }
        });


    }

    private void innerPaneInitialize() {
        innerPane = new JPanel(new BorderLayout());
//        insets = ;
        innerPane.setBorder(BorderFactory.createEmptyBorder(insets.top, insets.left, insets.bottom, insets.right));
        JLabel label = new JLabel("Content", SwingConstants.CENTER);
        innerPane.add(label, BorderLayout.CENTER);
        innerPane.setMaximumSize(new Dimension(400, 600));
//        innerPane.setPreferredSize(new Dimension(400, 600));
//        innerPane.setBackground(Color.WHITE);
//        Insets insets = new Insets(10, 10, 10, 10);
//        innerPane.setBorder(BorderFactory.createEmptyBorder());
//        JLabel labelNorth = new JLabel("North", SwingConstants.CENTER);
//        innerPane.add(labelNorth, BorderLayout.NORTH);
//        JLabel labelSouth = new JLabel("South", SwingConstants.CENTER);
//        innerPane.add(labelSouth, BorderLayout.SOUTH);
//        JLabel labelEast = new JLabel("East", SwingConstants.CENTER);
//        innerPane.add(labelEast, BorderLayout.EAST);
//        JLabel labelWest = new JLabel("West", SwingConstants.CENTER);
//        innerPane.add(labelWest, BorderLayout.WEST);
////        JLabel labelCenter = new JLabel("Center", SwingConstants.CENTER);
////        innerPane.add(labelCenter, BorderLayout.CENTER);
//        innerPane.setMaximumSize(new Dimension(100, 100));
        innerPane.setOpaque(false);
    }

    public Login() {
        //JFrame and MenuBar Initialization
        menuBarInitialize();
        baseFrame = new JFrame();
        baseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        baseFrame.setMinimumSize(new Dimension(640, 480));
        baseFrame.setSize(screenSize);
        baseFrame.setExtendedState(baseFrame.getExtendedState() | baseFrame.MAXIMIZED_BOTH);
        baseFrame.setJMenuBar(menuBar);
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

        floatingBox = new JPanel();
        floatingBox.setSize(new Dimension(400, 500));
        floatingBox.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        outerPaneInitialize();
        innerPaneInitialize();

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        usernameText = new JLabel("Username : ", SwingConstants.RIGHT);
        passwordText = new JLabel("Password : ", SwingConstants.RIGHT);
        loginButton = new JButton("Login");
        loginHeader = new JLabel("Login", SwingConstants.CENTER);
        img_itbuilding = new ImageIcon(FewFile.getImage("account/img/it_building.png"));

        buttonBox = new JPanel();
        buttonBox.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonBox.add(loginButton);
//        innerPane.add(usernameText);

        //Tried using GridBag Layout (Card Layout sounds interesting too, but are too complicated)
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.ipady = 10;
        c.insets = new Insets(5, 0, 5, 40);
        floatingBox.add(loginHeader, c);
        c.weightx = 0.05;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        floatingBox.add(usernameText, c);
        c.weightx = 0.57;
        c.gridx = 2;
        c.gridy = 1;
        floatingBox.add(usernameField, c);
        c.weightx = 0.03;
        c.gridx = 1;
        c.gridy = 2;
        floatingBox.add(passwordText, c);
        c.weightx = 0.57;
        c.gridx = 2;
        c.gridy = 2;
        floatingBox.add(passwordField, c);
        c.gridx = 2;
        c.gridy = 3;
        floatingBox.add(buttonBox, c);
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.40;
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 5;
        floatingBox.add(new JLabel("building goes here"), c);//Image wont go in
        c.gridheight = 1;

        innerPane.add(floatingBox);
        outerPane.add(innerPane);

        baseFrame.setContentPane(outerPane);
        baseFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        MacConfig.menuBar();
        new Login();
    }
}