package net.itkmitl.room.portal.admin.views;

import com.formdev.flatlaf.FlatIntelliJLaf;
import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class AuthView {
    private JFrame frame;
    private JPanel loginPanel, buttonPanel;
    public JButton loginButton, cancelButton;
    private JLabel userLabel, passwordLabel;
    public JLabel alertLabel;
    public JTextField loginField;
    public JPasswordField passwordField;

    public AuthView(){
        frame = new JFrame("Laew Tae Hong Authentication");
        ArrayList<Image> multiIcon = new ArrayList<>();
        multiIcon.add(new ImageIcon("resource/icons/icon-208px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-128px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-64px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-56px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-48px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-40px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-20px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-16px.png").getImage());
        frame.setLayout(new BorderLayout());
        frame.add(new JLabel(new ImageIcon("resource/account/banner/banner1-50.png")), BorderLayout.NORTH);
        frame.setIconImages(multiIcon);
        frame.setResizable(false);
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        userLabel = new JLabel("E-Mail");
        passwordLabel = new JLabel("Password");
        alertLabel = new JLabel(" ");
        loginField = new JTextField();
        passwordField = new JPasswordField();
        loginPanel.add(userLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 0, new Insets(10, 10, 5, 5)).getGBC());
        loginPanel.add(loginField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 0, new Insets(10, 0, 5, 10)).getGBC());

        loginPanel.add(passwordLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 1, new Insets(10, 10, 5, 5)).getGBC());
        loginPanel.add(passwordField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 1, new Insets(10, 0, 5, 10)).getGBC());

        loginPanel.add(new JPanel(), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 2, new Insets(0, 10, 10, 5)).getGBC());
        loginPanel.add(alertLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 2, new Insets(0, 0, 10, 10)).getGBC());

        buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(0, 0 ,5 ,5));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);

        frame.add(loginPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.getRootPane().setDefaultButton(loginButton);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JFrame getFrame(){
        return frame;
    }
}
