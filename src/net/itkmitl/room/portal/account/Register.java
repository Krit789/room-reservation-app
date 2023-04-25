package net.itkmitl.room.portal.account;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatIntelliJLaf;

import net.itkmitl.room.MacConfig;

public class Register extends BaseClass implements ActionListener {

    private JPanel namePanel;
    private JTextField usernameField, studentIdField, firstNameField, lastNameField, telField;
    private JLabel usernameText, passwordText, registerHeader, studentIdText, firstNameText, lastNameText, telText;
    private JPasswordField passwordField;


    public Register() {
        //JFrame and MenuBar Initialization
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
        floatingBox.add(registerHeader, c);
        c.weightx = 0.01;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        floatingBox.add(usernameText, c);
        c.weightx = 0.59;
        c.gridx = 2;
        c.gridy = 1;
        floatingBox.add(usernameField, c);
        c.weightx = 0.01;
        c.gridx = 1;
        c.gridy = 2;
        floatingBox.add(passwordText, c);
        c.weightx = 0.59;
        c.gridx = 2;
        c.gridy = 2;
        floatingBox.add(passwordField, c);
        c.weightx = 0.01;
        c.gridx = 1;
        c.gridy = 3;
        floatingBox.add(studentIdText, c);
        c.weightx = 0.59;
        c.gridx = 2;
        c.gridy = 3;
        floatingBox.add(studentIdField, c);
        c.weightx = 0.60;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 4;
        floatingBox.add(namePanel, c);
        c.weightx = 0.01;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 5;
        floatingBox.add(telText, c);
        c.weightx = 0.59;
        c.gridx = 2;
        c.gridy = 5;
        floatingBox.add(telField, c);
        c.gridx = 2;
        c.gridy = 6;
        floatingBox.add(buttonBox, c);
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.4;
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 5;
        floatingBox.add(new JLabel("building goes here"), c);//Image wont go in
        c.gridheight = 1;

        innerPane.add(floatingBox);
        outerPane.add(innerPane);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        if (buttonClicked.equals(registerButton)) {
            registerHeader.setText(baseFrame.getSize() + "");
        } else if (buttonClicked.equals(loginButton)) {
            baseFrame.dispose();
            String[] arguments = new String[]{""};
            Login.main(arguments);
        }
    }

    public static void main(String[] args) {
        MacConfig.menuBar();
        try {
            MacConfig.menuBar("Laew Tae Hong Management");
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (Exception ignored) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            }
        } catch (Exception ignored) {
        }
        new Register();
    }

}
