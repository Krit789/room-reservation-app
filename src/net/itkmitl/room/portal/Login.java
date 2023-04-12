package net.itkmitl.room.portal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.itkmitl.room.GUIStarter;
import net.itkmitl.room.MacConfig;
import net.itkmitl.room.libs.peeranat.util.*;
import net.itkmitl.room.portal.admin.BaseWindow;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Login implements ActionListener {
    private JFrame outerPane, innerPane;
    private JTextField usernameField, passwordField;
    private JLabel usernameText, passwordText, loginHeader;
    private ImageIcon img_itbuilding;
    private JMenuBar menuBar;
    private JMenu fileMenu, windowMenu, optionMenu, helpMenu;
    private JMenuItem fileMenuItem1, fileMenuItem2, fileMenuItem3;
    private JMenuItem optionMenuItem1, helpMenuItem1;
    private JMenu newWindowMenu;
    private JMenuItem windowMenuItem1, windowMenuItem2, windowMenuItem3;

    public Login() {
        outerPane = new JFrame("Login Page");
        outerPane.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        outerPane.setExtendedState(outerPane.getExtendedState() | outerPane.MAXIMIZED_BOTH);

        //Menubar
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
                outerPane.dispose();
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

        innerPane = new JFrame();
        usernameField = new JTextField();
        passwordField = new JTextField();
        usernameText = new JLabel("Username : ");
        passwordText = new JLabel("Password : ");
        loginHeader = new JLabel("Login");
        img_itbuilding = new ImageIcon(FewFile.getImage("account/img/it_building.png"));


        outerPane.setLayout(new BorderLayout());
        outerPane.setJMenuBar(menuBar);
//        outerPane.add(menuBar, BorderLayout.NORTH);
        outerPane.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        MacConfig.menuBar();
        new Login();
    }
}