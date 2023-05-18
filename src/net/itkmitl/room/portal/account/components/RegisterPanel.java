package net.itkmitl.room.portal.account.components;


import net.itkmitl.room.libs.peeranat.util.FewFile;
import net.itkmitl.room.portal.components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;

public class RegisterPanel extends RoundedPanel {
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

        registerButton = new ButtonGradient("Register", new Color(44, 102, 188), new Color(94, 135, 197));
        registerButton.setFont(new Font("Cousine", Font.BOLD, textSize));

        warningLabel = new JLabel(" ");
        warningLabel.setForeground(Color.red);
        warningLabel.setFont(new Font("Cousine", Font.BOLD, textSize));
        warningLabel.setHorizontalAlignment(SwingConstants.CENTER);

        fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridBagLayout());
        fieldPanel.add(registerHeader, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 0, 0, new Insets(15, 0, 40, 0)).setAnchor(GridBagConstraints.WEST));

        twoColumnPanel = new JPanel();
        twoColumnPanel.setLayout(new GridBagLayout());
        twoColumnPanel.setBackground(Color.white);

        twoColumnPanel.add(firstNameField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 0, 0, 0, new Insets(5, 0, 25, 5)).getGBC());
        twoColumnPanel.add(lastNameField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 0, 1, 0, new Insets(5, 10, 25, 45)).getGBC());

        twoColumnPanel.add(emailField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 0, 1, new Insets(5, 0, 25, 5)).getGBC());
        twoColumnPanel.add(telField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 1, 1, new Insets(5, 10, 25, 45)).getGBC());
        twoColumnPanel.add(passwordField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 0, 2, new Insets(5, 0, 5, 5)).getGBC());
        twoColumnPanel.add(confirmPasswordField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 1, 2, new Insets(5, 10, 5, 45)).getGBC());

        operationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        operationPanel.setBackground(Color.white);
        operationPanel.add(loginLabel1);
        operationPanel.add(loginLabel2);
        loginLabel2.setForeground(new Color(94, 135, 197));

        fieldPanel.add(twoColumnPanel, new GBCBuilder(GridBagConstraints.BOTH, 1, 0, 1).getGBC());
        fieldPanel.add(warningLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0, 0, 2, new Insets(5, 0, 5, 5)).getGBC());

        operationPanel.add(Box.createRigidArea(new Dimension(35, 0)));
        operationPanel.add(registerButton);
        fieldPanel.add(operationPanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0, 0, 11, new Insets(5, 0, 5, 45)).getGBC());
        fieldPanel.setBackground(Color.white);
        ImagePanel imagePanel = new ImagePanel(FewFile.getImage("account/banner/banner3-entry-25.png"));
        this.add(imagePanel, new GBCBuilder(GridBagConstraints.BOTH, 0.37, 1, 0, 0).getGBC());
        this.add(fieldPanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.63, 1, 1, 0, new Insets(0, 25, 0, 60)).setAnchor(GridBagConstraints.NORTHWEST));
        registerButton.setEnabled(false);
        this.setBorder(new EmptyBorder(25, 25, 25, 25));
    }

    public BetterJPasswordField getPasswordField() {
        return passwordField;
    }

    public BetterJPasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public String getEmail() {
        return emailField.getText();
    }

    public BetterJTextField getEmailField() {
        return emailField;
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
