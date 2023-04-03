package net.itkmitl.room.portal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.itkmitl.room.libs.peeranat.util.*;


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

    public Login() {
        outerPane = new JFrame("Login Page");
        outerPane.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        outerPane.setExtendedState(outerPane.getExtendedState() | outerPane.MAXIMIZED_BOTH);

        innerPane = new JFrame();
        usernameField = new JTextField();
        passwordField = new JTextField();
        usernameText = new JLabel("Username : ");
        passwordText = new JLabel("Password : ");
        loginHeader = new JLabel("Login");
        img_itbuilding = new ImageIcon(FewFile.getImage("account/img/it_building.png"));


        outerPane.setLayout(new BorderLayout());
        outerPane.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new Login();
    }
}