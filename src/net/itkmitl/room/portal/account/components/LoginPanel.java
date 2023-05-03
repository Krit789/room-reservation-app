package net.itkmitl.room.portal.account.components;

import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Arrays;

public class LoginPanel extends JPanel{
    protected JTextField emailField;
    protected JLabel emailText, passwordText, loginHeader, imageLabel;
    public JLabel warningLabel;
    protected JPasswordField passwordField;
    protected JPanel buttonBox, loginPanel;
    public JButton loginButton, registerButton;



    public LoginPanel(){
        super(new GridBagLayout());
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        emailField = new JTextField();
        passwordField = new JPasswordField();
        emailText = new JLabel("Email : ", SwingConstants.RIGHT);
        passwordText = new JLabel("Password : ", SwingConstants.RIGHT);
        loginHeader = new JLabel("Login", SwingConstants.CENTER);
        loginHeader.setFont(new Font("SansSerif", Font.BOLD, 26));

        buttonBox = new JPanel();
        buttonBox.setLayout(new FlowLayout(FlowLayout.RIGHT));
        warningLabel = new JLabel("", SwingConstants.RIGHT);
        warningLabel.setForeground(Color.red);

        buttonBox.add(registerButton);
        buttonBox.add(loginButton);

        loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 1;
//        c.gridx = 1;
//        c.gridy = 0;
//        c.gridwidth = 3;
//        c.ipady = 10;
//        c.ipadx = 50;
//        c.insets = new Insets(5, 0, 5, 45);
        loginPanel.add(loginHeader, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 0, new Insets(5, 0, 5, 45)).setAnchor(GridBagConstraints.WEST));
//        c.weightx = 0.05;
//        c.gridx = 1;
//        c.gridy = 1;
//        c.gridwidth = 1;
        loginPanel.add(emailText, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 1, new Insets(5, 0, 5, 45)).setAnchor(GridBagConstraints.WEST));
//        c.weightx = 0.57;
//        c.gridx = 2;
//        c.gridy = 1;
        loginPanel.add(emailField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 2, new Insets(5, 0, 5, 45)).getGBC());
//        c.weightx = 0.03;
//        c.gridx = 1;
//        c.gridy = 2;
        loginPanel.add(passwordText, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 3, new Insets(5, 0, 5, 45)).setAnchor(GridBagConstraints.WEST));
//        c.weightx = 0.57;
//        c.gridx = 2;
//        c.gridy = 2;
        loginPanel.add(passwordField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 4, new Insets(5, 0, 5, 45)).getGBC());
//        c.gridx = 2;
//        c.gridy = 3;
//        loginPanel.add(buttonBox, new GBCBuilder(GridBagConstraints.CENTER, 1, 0, 5, new Insets(5, 0, 5, 45)).getGBC());
//        c.gridx = 2;
//        c.gridy = 4;
        loginPanel.add(warningLabel, new GBCBuilder(GridBagConstraints.CENTER, 1, 0, 6, new Insets(5, 0, 5, 45)).getGBC());
//        c.fill = GridBagConstraints.VERTICAL;
//        c.weightx = 0.4;
//        c.gridx = 0;
//        c.gridy = 1;
//        c.gridheight = 5;
//        imageLabel = new JLabel();
//        Image banner = new ImageIcon("resource/account/banner/banner3-entry-25.png").getImage();
//        System.out.println(loginPanel.getImageLabel().getWidth() + " " + loginPanel.getImageLabel().getHeight());
        this.add(new ImagePanel(new ImageIcon("resource/account/banner/banner3-entry-25.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.2, 0, 0, new Insets(5, 0, 5, 45)).getGBC());//Image wont go in
        this.add(loginPanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.8, 1, 0, new Insets(5, 0, 5, 45)).getGBC());
    }

    public String getEmail(){
        return emailField.getText();
    }
    public String getPassword(){
        return Arrays.toString(passwordField.getPassword());
    }
}
