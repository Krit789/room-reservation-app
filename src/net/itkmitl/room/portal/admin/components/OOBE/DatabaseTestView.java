package net.itkmitl.room.portal.admin.components.OOBE;

import net.itkmitl.room.libs.peeranat.util.FewFile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DatabaseTestView extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 2479869772958246586L;
    private final JPanel topPanel;
    public JLabel title, description, connectionLabel, credentialLabel, databaseLabel, userTableLabel, roomTableLabel, reservationTableLabel, feedbackTableLabel, tableIndexLabel, tableRelationshipLabel, yesLabel, noLabel;
    public JScrollPane resultPane;
    protected JPanel resultPanel;

    public DatabaseTestView() {
        this.setLayout(new BorderLayout());
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(15, 10, 0, 15));
        title = new JLabel("Database Connection Test");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        description = new JLabel("<html><p style=\"width:225px\">" +
                "Please wait, we're trying to connect to your MySQL Database." + "</p></html>");
        description.setFont(new Font("SansSerif", Font.PLAIN, 12));
        topPanel.add(title);
        topPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        topPanel.add(description);
        topPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        resultPanel = new JPanel();
        resultPanel.setLayout(new GridBagLayout());
        connectionLabel = new JLabel("Database Connection");
        credentialLabel = new JLabel("Credential Authentication");
        databaseLabel = new JLabel("Database");
        userTableLabel = new JLabel("User Table");
        roomTableLabel = new JLabel("Room Table");
        reservationTableLabel = new JLabel("Reservation Table");
        feedbackTableLabel = new JLabel("Feedback Table");
        tableIndexLabel = new JLabel("Table Index");
        tableRelationshipLabel = new JLabel("Table Relationship");
        yesLabel = new JLabel(FewFile.getImage("icons/yes-16px.png"));
        noLabel = new JLabel(FewFile.getImage("icons/no-16px.png"));

        this.add(topPanel, BorderLayout.NORTH);
        resultPane = new JScrollPane(resultPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(resultPane, BorderLayout.CENTER);

    }

    public void setDescription(String description) {
        this.description.setText(String.format("<html><p style=\"width:225px\">%s</p></html>", description));
    }
}
