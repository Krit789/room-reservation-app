package net.itkmitl.room.portal.account.components;

import net.itkmitl.room.portal.components.ButtonGradient;
import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.RoundedPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;

public class LoginPanel extends RoundedPanel {
    protected JTextField emailField;
    protected JLabel emailText, passwordText, loginHeader, registerLabel1;
    public JLabel warningLabel, registerLabel2;
    protected JPasswordField passwordField;
    protected JPanel loginPanel, operationPanel;
    public ButtonGradient loginButton;
    public ImagePanel imagePanel;



    public LoginPanel(){
        super(30, 30, Color.WHITE);
        this.setLayout(new GridBagLayout());
        loginButton = new ButtonGradient();
        loginButton.setText("Login");
        loginButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        loginButton.setColor1(new Color(44, 102, 188));
        loginButton.setColor2(new Color(94, 135, 197));
//        registerButton = new JButton("Register");
        emailField = new JTextField();
        passwordField = new JPasswordField();
        emailText = new JLabel("Email", SwingConstants.RIGHT);
        emailText.setFont(new Font("SansSerif", Font.PLAIN, 16));

        passwordText = new JLabel("Password", SwingConstants.RIGHT);
        passwordText.setFont(new Font("SansSerif", Font.PLAIN, 16));

        loginHeader = new JLabel("Login", SwingConstants.CENTER);
        loginHeader.setFont(new Font("SansSerif", Font.BOLD, 48));

//        buttonBox = new JPanel();
//        buttonBox.setLayout(new FlowLayout(FlowLayout.RIGHT));
        warningLabel = new JLabel("", SwingConstants.RIGHT);
        warningLabel.setForeground(Color.red);

//        buttonBox.add(registerButton);
//        buttonBox.add(loginButton);

        loginPanel = new JPanel();
        loginPanel.setBackground(Color.white);
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.add(loginHeader, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 0, new Insets(5, 0, 5, 45)).setAnchor(GridBagConstraints.WEST));
        loginPanel.add(emailText, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 1, new Insets(5, 0, 5, 45)).setAnchor(GridBagConstraints.WEST));
        loginPanel.add(emailField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 2, new Insets(0, 0, 5, 45)).getGBC());
        loginPanel.add(passwordText, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 3, new Insets(5, 0, 5, 45)).setAnchor(GridBagConstraints.WEST));
        loginPanel.add(passwordField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 4, new Insets(0, 0, 5, 45)).getGBC());
        loginPanel.add(warningLabel, new GBCBuilder(GridBagConstraints.CENTER, 1, 0, 6, new Insets(5, 0, 5, 45)).getGBC());
        operationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        operationPanel.setBackground(Color.white);
        registerLabel1 = new JLabel("Don't have an account?");
        registerLabel2 = new JLabel("<html><p><u>Register Here!</u></p></html>");
        registerLabel1.setFont(new Font("SansSerif", Font.PLAIN, 16));
        registerLabel2.setFont(new Font("SansSerif", Font.PLAIN, 16));
        registerLabel2.setForeground(new Color(94, 135, 197));

        operationPanel.add(registerLabel1);
        operationPanel.add(registerLabel2);
        operationPanel.add(Box.createRigidArea(new Dimension(35, 0)));
        operationPanel.add(loginButton);
        imagePanel = new ImagePanel(new ImageIcon("resource/account/banner/banner3-entry-25.png"));
        loginPanel.add(operationPanel, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 7, new Insets(5, 0, 5, 45)).setAnchor(GridBagConstraints.EAST));
        this.add(imagePanel, new GBCBuilder(GridBagConstraints.BOTH, 0.35, 1, 0, 0, new Insets(0, 0, 0, 0)).getGBC()); //Image wont go in
        this.add(loginPanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.65, 1,1, 0, new Insets(0, 25, 0, 60)).setAnchor(GridBagConstraints.NORTHWEST));
        this.setBorder(new EmptyBorder(25, 25, 25 ,25));
    }

    public String getEmail(){
        return emailField.getText();
    }
    public String getPassword(){
        return String.valueOf(passwordField.getPassword());
    }
}
