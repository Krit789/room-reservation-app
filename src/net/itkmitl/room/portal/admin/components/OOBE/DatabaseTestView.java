package net.itkmitl.room.portal.admin.components.OOBE;

import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DatabaseTestView extends JPanel {
    private JLabel title, description, connectionLabel, credentialLabel, databaseLabel, tableLabel, yesLabel, noLabel;
    private JPanel topPanel;
    protected JPanel resultPanel;

    public DatabaseTestView() {
        this.setLayout(new BorderLayout());
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(15, 10 ,0 ,15));
        title = new JLabel("Database Connection Test");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        description = new JLabel("<html><p style=\"width:225px\">" +
                "Please wait, we're trying to connect to your MySQL Database." + "</p></html>");
        description.setFont(new Font("SansSerif", Font.PLAIN, 12));
        topPanel.add(title);
        topPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        topPanel.add(description);
        topPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        resultPanel = new JPanel();
        resultPanel.setLayout(new GridBagLayout());
        connectionLabel = new JLabel("Database Connection");
        credentialLabel = new JLabel("Credential Authentication");
        databaseLabel = new JLabel("Database Exist");
        tableLabel = new JLabel("Table Exist");
        yesLabel = new JLabel(new ImageIcon("resource/icons/yes-16px.png"));
        noLabel = new JLabel(new ImageIcon("resource/icons/no-16px.png"));

        resultPanel.add(connectionLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.99, 0, 0, new Insets(0,5,5,5)).getGBC());

        resultPanel.add(credentialLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.99, 0, 1, new Insets(5,5,5,5)).getGBC());

        resultPanel.add(databaseLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.99, 0, 2, new Insets(5,5,5,5)).getGBC());

        resultPanel.add(tableLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.99, 0, 3, new Insets(5,5,5,5)).getGBC());

        resultPanel.setBorder(new EmptyBorder(0, 25, 0 ,25));
        this.add(topPanel, BorderLayout.NORTH);
        this.add(resultPanel, BorderLayout.CENTER);
    }
}
