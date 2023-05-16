package net.itkmitl.room.portal.account.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.itkmitl.room.portal.components.BetterJPasswordField;
import net.itkmitl.room.portal.components.BetterJTextField;
import net.itkmitl.room.portal.components.ButtonGradient;
import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.RoundedPanel;

public class LoginPanel extends RoundedPanel {
    private static final long serialVersionUID = 4047655800877345668L;
    private final BetterJTextField emailField;
    private final JLabel loginHeader, registerLabel1;
    private final JLabel warningLabel, registerLabel2;
    private final BetterJPasswordField passwordField;
    private final JPanel loginPanel, operationPanel;
    private final ButtonGradient loginButton;
    private final ImagePanel imagePanel;

    public LoginPanel() {
        super(30, 30, Color.WHITE);
        this.setLayout(new GridBagLayout());
        loginButton = new ButtonGradient("Login", new Color(44, 102, 188), new Color(94, 135, 197));
        loginButton.setFont(new Font("Cousine", Font.BOLD, 16));

        emailField = new BetterJTextField("E-Mail");
        passwordField = new BetterJPasswordField("Password");

        loginHeader = new JLabel("Login", SwingConstants.CENTER);
        loginHeader.setFont(new Font("Cousine", Font.BOLD, 48));

        warningLabel = new JLabel(" ", SwingConstants.RIGHT);
        warningLabel.setFont(new Font("Cousine", Font.PLAIN, 16));
        warningLabel.setForeground(Color.red);

        loginPanel = new JPanel();
        loginPanel.setBackground(Color.white);
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.add(loginHeader, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 0, new Insets(15, 0, 40, 45)).setAnchor(GridBagConstraints.WEST));
        loginPanel.add(emailField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 1, new Insets(0, 0, 25, 45)).getGBC());
        loginPanel.add(passwordField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 2, new Insets(0, 0, 5, 45)).getGBC());
        loginPanel.add(warningLabel, new GBCBuilder(GridBagConstraints.CENTER, 1, 0, 3, new Insets(5, 0, 0, 45)).getGBC());
        operationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        operationPanel.setBackground(Color.white);
        registerLabel1 = new JLabel("Don't have an account?");
        registerLabel2 = new JLabel("<html><p><u>Register Here!</u></p></html>");
        registerLabel1.setFont(new Font("Cousine", Font.BOLD, 16));
        registerLabel1.setForeground(Color.LIGHT_GRAY);
        registerLabel2.setFont(new Font("Cousine", Font.BOLD, 16));
        registerLabel2.setForeground(new Color(94, 135, 197));

        operationPanel.add(registerLabel1);
        operationPanel.add(registerLabel2);
        operationPanel.add(Box.createRigidArea(new Dimension(35, 0)));
        operationPanel.add(loginButton);
        imagePanel = new ImagePanel(new ImageIcon("resource/account/banner/banner3-entry-25.png"));
        loginButton.setEnabled(false);
        loginPanel.add(operationPanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 7, new Insets(5, 0, 5, 45)).getGBC());
        this.add(imagePanel, new GBCBuilder(GridBagConstraints.BOTH, 0.37, 1, 0, 0).getGBC());
        this.add(loginPanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.63, 1, 1, 0, new Insets(0, 25, 0, 60)).setAnchor(GridBagConstraints.NORTHWEST));
        this.setBorder(new EmptyBorder(25, 25, 25, 25));
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return String.valueOf(passwordField.getPassword());
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public ButtonGradient getLoginButton() {
        return loginButton;
    }

    public JLabel getWarningLabel() {
        return warningLabel;
    }

    public JLabel getRegisterLabel1() {
        return registerLabel1;
    }

    public JLabel getRegisterLabel2() {
        return registerLabel2;
    }
}