package net.itkmitl.room.portal.account.components;


import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class RegisterPanel extends JPanel{
    protected GridBagConstraints c;
    private JPanel namePanel, buttonBox;
    private JTextField usernameField, studentIdField, firstNameField, lastNameField, telField;
    private JLabel usernameText, passwordText, registerHeader, studentIdText, firstNameText, lastNameText, telText;
    private JPasswordField passwordField;
    public JButton loginButton, registerButton;
    public RegisterPanel(){
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        studentIdField = new JTextField();
        firstNameField = new JTextField(30);
        lastNameField = new JTextField(30);
        telField = new JTextField();
        usernameText = new JLabel("Username : ", SwingConstants.LEFT);
        passwordText = new JLabel("Password : ", SwingConstants.LEFT);
        studentIdText = new JLabel("Student ID : ", SwingConstants.LEFT);
        firstNameText = new JLabel("FirstName : ", SwingConstants.LEFT);
        lastNameText = new JLabel("LastName : ", SwingConstants.LEFT);
        telText = new JLabel("Telephone :", SwingConstants.LEFT);
        registerHeader = new JLabel("Register", SwingConstants.CENTER);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        buttonBox = new JPanel();
        buttonBox.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonBox.add(loginButton);
        buttonBox.add(registerButton);

        namePanel = new JPanel();
        namePanel.setLayout(new GridBagLayout());

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.025;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.ipady = 10;
        c.insets = new Insets(5, 0, 5, 5);
        namePanel.add(firstNameText, c);
        c.weightx = 0.475;
        c.gridx = 2;
        namePanel.add(firstNameField, c);
        c.weightx = 0.025;
        c.gridx = 3;
        namePanel.add(lastNameText, c);
        c.weightx = 0.475;
        c.gridx = 4;
        namePanel.add(lastNameField, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 3;
        c.ipady = 10;
        c.ipadx = 50;
        c.insets = new Insets(5, 0, 5, 45);
        this.add(registerHeader, c);
        c.weightx = 0.01;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        this.add(usernameText, c);
        c.weightx = 0.59;
        c.gridx = 2;
        c.gridy = 1;
        this.add(usernameField, c);
        c.weightx = 0.01;
        c.gridx = 1;
        c.gridy = 2;
        this.add(passwordText, c);
        c.weightx = 0.59;
        c.gridx = 2;
        c.gridy = 2;
        this.add(passwordField, c);
        c.weightx = 0.01;
        c.gridx = 1;
        c.gridy = 3;
        this.add(studentIdText, c);
        c.weightx = 0.59;
        c.gridx = 2;
        c.gridy = 3;
        this.add(studentIdField, c);
        c.weightx = 0.60;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 4;
        this.add(namePanel, c);
        c.weightx = 0.01;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 5;
        this.add(telText, c);
        c.weightx = 0.59;
        c.gridx = 2;
        c.gridy = 5;
        this.add(telField, c);
        c.gridx = 2;
        c.gridy = 6;
        this.add(buttonBox, c);
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.4;
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 5;
        this.add(new JLabel("building goes here"), c);//Image wont go in
        c.gridheight = 1;
    }
    public String getData(){
        return usernameField.getText() + Arrays.toString(passwordField.getPassword()) +
                studentIdField.getText() + firstNameField.getText() + lastNameField.getText() +
                telField.getText();
    }
}
