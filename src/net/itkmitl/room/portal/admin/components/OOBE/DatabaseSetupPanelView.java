package net.itkmitl.room.portal.admin.components.OOBE;

import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DatabaseSetupPanelView extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = -7942736605197573612L;
    private final JLabel title;
    private final JLabel description;
    private final JLabel dbAddressLabel;
    private final JLabel dbPortLabel;
    private final JLabel dbNameLabel;
    private final JLabel dbUserLabel;
    private final JLabel dbPasswordLabel;
    private final JPanel databasePanel;
    private final JPanel dbCredPanel;
    private final JPanel dbConnectionPanel;
    private final JPanel topPanel;
    protected JTextField dbAddressTextField, dbNameTextField, dbUserTextField;
    protected JPasswordField dbPasswordField;
    protected JSpinner dbPortSpinner;

    public DatabaseSetupPanelView() {
        this.setLayout(new BorderLayout());

        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(15, 10, 0, 15));
        title = new JLabel("Database Connection Setup");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        description = new JLabel("<html><p style=\"width:225px\">" +
                "First, let's connect Laew Tae Hong to your MySQL Database." + "</p></html>");
        description.setFont(new Font("SansSerif", Font.PLAIN, 12));
        topPanel.add(title);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topPanel.add(description);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(topPanel, BorderLayout.NORTH);
        // Database Panel
        databasePanel = new JPanel();
        databasePanel.setLayout(new GridBagLayout());


        dbCredPanel = new JPanel();
        dbCredPanel.setLayout(new GridBagLayout());
        dbCredPanel.setBorder(BorderFactory.createTitledBorder("Credentials"));

        dbConnectionPanel = new JPanel();
        dbConnectionPanel.setLayout(new GridBagLayout());
        dbConnectionPanel.setBorder(BorderFactory.createTitledBorder("MySQL Connections"));

        dbAddressLabel = new JLabel("Address");
        dbPortLabel = new JLabel("Port");
        dbNameLabel = new JLabel("Database Name");
        dbUserLabel = new JLabel("Username");
        dbPasswordLabel = new JLabel("Password");

        dbAddressTextField = new JTextField();
        dbPortSpinner = new JSpinner(new SpinnerNumberModel(3306, 0, 65535, 1));
        dbPortSpinner.setEditor(new JSpinner.NumberEditor(dbPortSpinner, "#"));
        dbNameTextField = new JTextField();
        dbUserTextField = new JTextField();
        dbPasswordField = new JPasswordField();

        dbCredPanel.add(dbUserLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 0).setInset(new Insets(5, 10, 5, 0)));
        dbCredPanel.add(dbUserTextField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 0).setInset(new Insets(0, 0, 5, 10)));

        dbCredPanel.add(dbPasswordLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 1).setInset(new Insets(0, 10, 10, 0)));
        dbCredPanel.add(dbPasswordField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 1).setInset(new Insets(0, 0, 10, 10)));

        dbConnectionPanel.add(dbAddressLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 0).setInset(new Insets(5, 10, 5, 0)));
        dbConnectionPanel.add(dbAddressTextField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 0).setInset(new Insets(0, 0, 5, 10)));
        dbConnectionPanel.add(dbPortLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 1).setInset(new Insets(0, 10, 5, 0)));
        dbConnectionPanel.add(dbPortSpinner, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 1).setInset(new Insets(0, 0, 5, 10)));

        dbConnectionPanel.add(dbNameLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 2).setInset(new Insets(0, 10, 5, 0)));
        dbConnectionPanel.add(dbNameTextField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 2).setInset(new Insets(0, 0, 5, 10)));

        databasePanel.add(dbCredPanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 0, 0).setInset(new Insets(0, 0, 5, 0)));
        databasePanel.add(dbConnectionPanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 0, 1).setInset(new Insets(0, 0, 0, 0)));

        this.add(databasePanel);
    }
}
