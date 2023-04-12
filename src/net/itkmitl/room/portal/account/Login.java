package net.itkmitl.room.portal.account;

import net.itkmitl.room.MacConfig;
import net.itkmitl.room.libs.peeranat.util.*;
import net.itkmitl.room.portal.admin.BaseWindow;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class Login implements ActionListener {
    private JFrame baseFrame;
    private OutPane outerPane;
    private JPanel innerPane, paddingN;
    private JTextField usernameField, passwordField;
    private JLabel usernameText, passwordText, loginHeader;
    private ImageIcon img_itbuilding;
    private JMenuBar menuBar;
    private JMenu fileMenu, windowMenu, optionMenu, helpMenu;
    private JMenuItem fileMenuItem1, fileMenuItem2, fileMenuItem3;
    private JMenuItem optionMenuItem1, helpMenuItem1;
    private JMenu newWindowMenu;
    private JMenuItem windowMenuItem1, windowMenuItem2, windowMenuItem3;
    private ArrayList<Image> multiIcon;


    private void outerPaneInitialize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

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

        outerPane = new OutPane();
        outerPane.setPreferredSize(screenSize);
//        ImageIcon backgroundImage = new ImageIcon(FewFile.getImage("account/img/account_background.png")); // Load your image
//        JLabel backgroundLabel = new JLabel(backgroundImage);

    }

    private void innerPaneInitialize() {
        innerPane = new JPanel(new BorderLayout());
        innerPane.setPreferredSize(new Dimension(400, 600));
        innerPane.setBackground(Color.WHITE);
        innerPane.setOpaque(false);
    }

    public Login() {
        //JFrame and MenuBar Initialization
        baseFrame = new JFrame();
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
//        baseFrame.setSize(screenSize);
        baseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        baseFrame.setMinimumSize(new Dimension(640, 480));
        baseFrame.setSize(new Dimension(1280, 720));
        baseFrame.setExtendedState(baseFrame.getExtendedState() | baseFrame.MAXIMIZED_BOTH);

        outerPaneInitialize();
        innerPaneInitialize();

        usernameField = new JTextField();
        passwordField = new JTextField();
        usernameText = new JLabel("Username : ");
        passwordText = new JLabel("Password : ");
        loginHeader = new JLabel("Login");
        img_itbuilding = new ImageIcon(FewFile.getImage("account/img/it_building.png"));

        outerPane.add(usernameText);

        outerPane.add(innerPane);

        baseFrame.add(outerPane);
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