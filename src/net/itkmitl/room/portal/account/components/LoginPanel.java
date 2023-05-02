package net.itkmitl.room.portal.account.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Arrays;

public class LoginPanel extends JPanel{
    protected GridBagConstraints c;
    protected JTextField emailField;
    protected JLabel emailText, passwordText, loginHeader;
    protected JPasswordField passwordField;
    protected JPanel buttonBox;
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
        buttonBox = new JPanel();
        buttonBox.setLayout(new FlowLayout(FlowLayout.RIGHT));

        buttonBox.add(registerButton);
        buttonBox.add(loginButton);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 3;
        c.ipady = 10;
        c.ipadx = 50;
        c.insets = new Insets(5, 0, 5, 45);
        this.add(loginHeader, c);
        c.weightx = 0.05;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        this.add(emailText, c);
        c.weightx = 0.57;
        c.gridx = 2;
        c.gridy = 1;
        this.add(emailField, c);
        c.weightx = 0.03;
        c.gridx = 1;
        c.gridy = 2;
        this.add(passwordText, c);
        c.weightx = 0.57;
        c.gridx = 2;
        c.gridy = 2;
        this.add(passwordField, c);
        c.gridx = 2;
        c.gridy = 3;
        this.add(buttonBox, c);
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.4;
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 5;
        this.add(new JLabel("building goes here"), c);//Image wont go in
        c.gridheight = 1;
    }

    public String getEmail(){
        return emailField.getText();
    }
    public String getPassword(){
        return Arrays.toString(passwordField.getPassword());
    }
}
