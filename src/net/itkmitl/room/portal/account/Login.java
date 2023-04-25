package net.itkmitl.room.portal.account;

import com.formdev.flatlaf.FlatIntelliJLaf;
import net.itkmitl.room.MacConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends BaseClass implements ActionListener {
    private JTextField usernameField;
    private JLabel usernameText, passwordText, loginHeader;
    private JPasswordField passwordField;


    //มี loginButton, registerButton นะ เรียกใช้ได้เลย

    public Login() {
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        usernameText = new JLabel("Username : ", SwingConstants.RIGHT);
        passwordText = new JLabel("Password : ", SwingConstants.RIGHT);
        loginHeader = new JLabel("Login", SwingConstants.CENTER);

        innerPane.setBorder(BorderFactory.createEmptyBorder(newInsets.top, newInsets.left, newInsets.bottom, newInsets.right));
        insets.top = newInsets.top;
        insets.left = newInsets.left;
        insets.bottom = newInsets.bottom;
        insets.right = newInsets.right;
        //Tried using GridBag Layout (Card Layout sounds interesting too, but are too complicated)
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 3;
        c.ipady = 10;
        c.ipadx = 50;
        c.insets = new Insets(5, 0, 5, 45);
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
//        c.fill = GridBagConstraints.VERTICAL;
//        c.weightx = 0.4;
//        c.gridx = 0;
//        c.gridy = 1;
//        c.gridheight = 5;
//        floatingBox.add(new JLabel("building goes here"), c);//Image wont go in
//        c.gridheight = 1;
    }

    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        if (buttonClicked.equals(loginButton)) {
            loginHeader.setText(baseFrame.getSize() + "");
        } else if (buttonClicked.equals(registerButton)) {
            baseFrame.dispose();
            String[] arguments = new String[]{""};
            Register.main(arguments);
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
        new Login();
    }
}