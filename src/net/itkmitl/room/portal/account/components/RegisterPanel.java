package net.itkmitl.room.portal.account.components;


import net.itkmitl.room.portal.components.ButtonGradient;
import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.RoundedPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;

public class RegisterPanel extends RoundedPanel {
    private JPanel fieldPanel, operationPanel, twoColumnPanel;
    private JTextField emailField, firstNameField, lastNameField, telField;
    private JLabel emailText, passwordText, confirmPasswordText, registerHeader, firstNameText, lastNameText, telText, loginLabel1;
    public JLabel loginLabel2, warningLabel;
    private JPasswordField passwordField, confirmPasswordField;
    public ButtonGradient registerButton;
    public RegisterPanel(){
        super(30, 30, Color.WHITE);
        this.setLayout(new GridBagLayout());
        emailField = new JTextField("");
        passwordField = new JPasswordField("");
        confirmPasswordField = new JPasswordField("");
        firstNameField = new JTextField("");
        lastNameField = new JTextField("");
        telField = new JTextField("");
        emailText = new JLabel("E-Mail");
        emailText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        passwordText = new JLabel("Password");
        passwordText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        confirmPasswordText = new JLabel("Confirm Password");
        confirmPasswordText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        firstNameText = new JLabel("First Name");
        firstNameText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lastNameText = new JLabel("Last Name");
        lastNameText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        telText = new JLabel("Phone Number");
        telText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        registerHeader = new JLabel("Register");
        registerHeader.setFont(new Font("SansSerif", Font.BOLD, 48));

        loginLabel1 = new JLabel("Already have a account?");
        loginLabel2 = new JLabel("<html><p><u>Login Here!</u></p></html>");
        loginLabel1.setFont(new Font("SansSerif", Font.PLAIN, 16));
        loginLabel2.setFont(new Font("SansSerif", Font.PLAIN, 16));

        registerButton = new ButtonGradient();
        registerButton.setText("Register");
        registerButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        registerButton.setColor1(new Color(44, 102, 188));
        registerButton.setColor2(new Color(94, 135, 197));

        warningLabel = new JLabel("");
        warningLabel.setForeground(Color.red);

        fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridBagLayout());
        fieldPanel.add(registerHeader, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 0, 0, new Insets(15, 0, 40, 45)).setAnchor(GridBagConstraints.WEST));

//        namePanel = new JPanel();
//        namePanel.setLayout(new GridBagLayout());

        twoColumnPanel = new JPanel();
        twoColumnPanel.setLayout(new GridBagLayout());
        twoColumnPanel.setBackground(Color.white);

        twoColumnPanel.add(firstNameText, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 0, 0, 0, new Insets(5, 0, 0, 5)).getGBC());
        twoColumnPanel.add(firstNameField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 0, 0, 1, new Insets(5, 0, 5, 5)).getGBC());
        twoColumnPanel.add(lastNameText, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 0, 1, 0, new Insets(5, 0, 0, 5)).getGBC());
        twoColumnPanel.add(lastNameField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 0, 1, 1, new Insets(5, 0, 5, 5)).getGBC());

        // Convert Everything to two column panel
        twoColumnPanel.add(emailText, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5,  0, 2, new Insets(5, 0, 0, 5)).getGBC());
        twoColumnPanel.add(emailField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5,  0, 3, new Insets(5, 0, 5, 5)).getGBC());
        twoColumnPanel.add(telText, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 1, 2, new Insets(5, 0, 0, 5)).getGBC());
        twoColumnPanel.add(telField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 1, 3, new Insets(5, 0, 5, 5)).getGBC());
        twoColumnPanel.add(passwordText, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5,  0, 4, new Insets(5, 0, 0, 5)).getGBC());
        twoColumnPanel.add(passwordField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5,  0, 5, new Insets(5, 0, 5, 5)).getGBC());
        twoColumnPanel.add(confirmPasswordText, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5,  1, 4, new Insets(5, 0, 0, 5)).getGBC());
        twoColumnPanel.add(confirmPasswordField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5,  1, 5, new Insets(5, 0, 5, 5)).getGBC());

        operationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        operationPanel.setBackground(Color.white);
        operationPanel.add(loginLabel1);
        operationPanel.add(loginLabel2);
        loginLabel2.setForeground(new Color(94, 135, 197));

        fieldPanel.add(twoColumnPanel, new GBCBuilder(GridBagConstraints.BOTH, 1,  0, 1).getGBC());
        fieldPanel.add(warningLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0, 0, 2, new Insets(5, 0, 5, 5)).getGBC());

        operationPanel.add(Box.createRigidArea(new Dimension(35, 0)));
        operationPanel.add(registerButton);
        fieldPanel.add(operationPanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0, 0, 11, new Insets(5, 0, 5, 5)).getGBC());
        fieldPanel.setBackground(Color.white);
        ImagePanel imagePanel = new ImagePanel(new ImageIcon("resource/account/banner/banner3-entry-25.png"));
        this.add(imagePanel, new GBCBuilder(GridBagConstraints.BOTH, 0.35, 1, 0, 0).getGBC());
        this.add(fieldPanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.65, 1, 1, 0, new Insets(0, 25, 0, 60)).setAnchor(GridBagConstraints.NORTHWEST));
        this.setBorder(new EmptyBorder(25, 25, 25 ,25));

    }
    public String getEmail(){
        return emailField.getText();
    }
    public String getPassword(){
        return Arrays.toString(passwordField.getPassword());
    }
    public String getFirstName(){
        return firstNameField.getText();
    }
    public String getLastName(){
        return lastNameField.getText();
    }
    public String getTelephone(){
        return telField.getText();
    }
}
