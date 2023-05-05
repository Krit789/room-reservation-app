package net.itkmitl.room.portal.account.components;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.itkmitl.room.portal.components.BetterJPasswordField;
import net.itkmitl.room.portal.components.BetterJTextField;
import net.itkmitl.room.portal.components.ButtonGradient;
import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.RoundedPanel;

public class RegisterPanel extends RoundedPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6536055055833326316L;
	private final JPanel fieldPanel, operationPanel, twoColumnPanel;
    private final BetterJTextField emailField, firstNameField, lastNameField, telField;
    private final JLabel registerHeader, loginLabel1;
    private final JLabel loginLabel2, warningLabel;
    private final BetterJPasswordField passwordField, confirmPasswordField;
    private final ButtonGradient registerButton;

    public RegisterPanel() {
        super(30, 30, Color.WHITE);
        this.setLayout(new GridBagLayout());

        //fieldSize = 18
        int textSize = 16;
        firstNameField = new BetterJTextField("First Name");
        lastNameField = new BetterJTextField("Last Name");
        emailField = new BetterJTextField("E-Mail");
        passwordField = new BetterJPasswordField("Password");
        confirmPasswordField = new BetterJPasswordField("Confirm Password");
        telField = new BetterJTextField("Phone Number");

        registerHeader = new JLabel("Register");
        registerHeader.setFont(new Font("Cousine", Font.BOLD, 48));

        loginLabel1 = new JLabel("Already have a account?");
        loginLabel2 = new JLabel("<html><p><u>Login Here!</u></p></html>");
        loginLabel1.setFont(new Font("Cousine", Font.BOLD, textSize));
        loginLabel1.setForeground(Color.LIGHT_GRAY);
        loginLabel2.setFont(new Font("Cousine", Font.BOLD, textSize));

        registerButton = new ButtonGradient();
        registerButton.setText("Register");
        registerButton.setFont(new Font("Cousine", Font.BOLD, textSize));
        registerButton.setColor1(new Color(44, 102, 188));
        registerButton.setColor2(new Color(94, 135, 197));

        warningLabel = new JLabel("");
        warningLabel.setForeground(Color.red);

        fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridBagLayout());
        fieldPanel.add(registerHeader, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 0, 0, new Insets(15, 0, 40, 0)).setAnchor(GridBagConstraints.WEST));

        twoColumnPanel = new JPanel();
        twoColumnPanel.setLayout(new GridBagLayout());
        twoColumnPanel.setBackground(Color.white);

        twoColumnPanel.add(firstNameField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 0, 0, 0, new Insets(5, 0, 25, 5)).getGBC());
        twoColumnPanel.add(lastNameField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 0, 1, 0, new Insets(5, 10, 25, 45)).getGBC());

        twoColumnPanel.add(emailField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5,  0, 1, new Insets(5, 0, 25, 5)).getGBC());
        twoColumnPanel.add(telField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 1, 1, new Insets(5, 10, 25, 45)).getGBC());
        twoColumnPanel.add(passwordField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5,  0, 2, new Insets(5, 0, 5, 5)).getGBC());
        twoColumnPanel.add(confirmPasswordField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5,  1, 2, new Insets(5, 10, 5, 45)).getGBC());

        operationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        operationPanel.setBackground(Color.white);
        operationPanel.add(loginLabel1);
        operationPanel.add(loginLabel2);
        loginLabel2.setForeground(new Color(94, 135, 197));

        fieldPanel.add(twoColumnPanel, new GBCBuilder(GridBagConstraints.BOTH, 1,  0, 1).getGBC());
        fieldPanel.add(warningLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0, 0, 2, new Insets(5, 0, 5, 5)).getGBC());

        operationPanel.add(Box.createRigidArea(new Dimension(35, 0)));
        operationPanel.add(registerButton);
        fieldPanel.add(operationPanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0, 0, 11, new Insets(5, 0, 5, 45)).getGBC());
        fieldPanel.setBackground(Color.white);
        ImagePanel imagePanel = new ImagePanel(new ImageIcon("resource/account/banner/banner3-entry-25.png"));
        this.add(imagePanel, new GBCBuilder(GridBagConstraints.BOTH, 0.37, 1, 0, 0).getGBC());
        this.add(fieldPanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.63, 1, 1, 0, new Insets(0, 25, 0, 60)).setAnchor(GridBagConstraints.NORTHWEST));
        this.setBorder(new EmptyBorder(25, 25, 25, 25));
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return Arrays.toString(passwordField.getPassword());
    }

    public String getConfirmPassword() {
        return Arrays.toString(confirmPasswordField.getPassword());
    }

    public String getFirstName() {
        return firstNameField.getText();
    }

    public String getLastName() {
        return lastNameField.getText();
    }

    public String getTelephone() {
        return telField.getText();
    }

    public JLabel getLoginLabel2() {
        return loginLabel2;
    }

    public JLabel getWarningLabel() {
        return warningLabel;
    }

    public ButtonGradient getRegisterButton() {
        return registerButton;
    }
}
