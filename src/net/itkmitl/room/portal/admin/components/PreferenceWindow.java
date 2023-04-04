package net.itkmitl.room.portal.admin.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreferenceWindow extends InternalFrame {
    private final JInternalFrame frame;
    private final JTabbedPane settingTab;
    private final JPanel databasePanel, instancePanel, titlePanel, frameButtonPanel, dbCredPanel, dbConnectionPanel;
    private final JButton okButton, cancelButton, applyButton;
    private final JLabel pageTitle, dbAddressLabel, dbPortLabel, dbNameLabel, dbUserLabel, dbPasswordLabel;
    private final JTextField dbAddressTextField, dbNameTextField, dbUserTextField;
    private final JCheckBox createDbCheckBox;
    private final JSpinner dbPortSpinner;
    private final JPasswordField dbPasswordField;

    public PreferenceWindow() {
        frame = new JInternalFrame("Preferences", true, true, false, false);
        frame.setFrameIcon(new ImageIcon("resource/icons/settings-16px.png"));
        frame.setMinimumSize(new Dimension(512, 400));
        frame.setSize(512, 400);
        pageTitle = new JLabel("Database");
        titlePanel = new JPanel();
        databasePanel = new JPanel();
        instancePanel = new JPanel();
        frameButtonPanel = new JPanel();
        titlePanel.add(pageTitle);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        pageTitle.setFont(new Font("Arial", Font.BOLD, 25));
        titlePanel.setBorder(new EmptyBorder(10, 10, 5, 10));
        frame.add(titlePanel, BorderLayout.NORTH);

        settingTab = new JTabbedPane();
        settingTab.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                switch (settingTab.getSelectedIndex()) {
                    case 0:
                        pageTitle.setText("Database");
                        break;
                    case 1:
                        pageTitle.setText("Client");
                        break;
                }
            }
        });

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.setClosed(true);

                } catch (Exception ignored) {
                }
            }
        });
        applyButton = new JButton("Apply");
        frameButtonPanel.add(okButton);
        frameButtonPanel.add(cancelButton);
        frameButtonPanel.add(applyButton);

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
        dbPortSpinner = new JSpinner(new SpinnerNumberModel(3306, 0, 65535 , 1));
        dbPortSpinner.setEditor(new JSpinner.NumberEditor(dbPortSpinner, "#" ));
        dbNameTextField = new JTextField();
        dbUserTextField = new JTextField();
        dbPasswordField = new JPasswordField();
        createDbCheckBox = new JCheckBox("Create new database with the specified name if the database doesn't exist");

        dbCredPanel.add(dbUserLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 0).setInset(new Insets(0,10,0,0)));
        dbCredPanel.add(dbUserTextField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 0).setInset(new Insets(0,0,0,10)));

        dbCredPanel.add(dbPasswordLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 1).setInset(new Insets(0,10,0,0)));
        dbCredPanel.add(dbPasswordField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 1).setInset(new Insets(0,0,0,10)));

        dbConnectionPanel.add(dbAddressLabel,  new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.2, 0, 0).setInset(new Insets(0,10,0,0)));
        dbConnectionPanel.add(dbAddressTextField,  new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 1, 0).setInset(new Insets(0,0,0,10)));
        dbConnectionPanel.add(dbPortLabel,  new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 2, 0).setInset(new Insets(0,10,0,0)));
        dbConnectionPanel.add(dbPortSpinner,  new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.2, 3, 0).setInset(new Insets(0,0,0,10)));

        dbConnectionPanel.add(dbNameLabel,  new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.2, 0, 1).setInset(new Insets(0,10,0,0)));
        dbConnectionPanel.add(dbNameTextField,  new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.8, 1, 1).setInset(new Insets(0,0,0,10)));
        dbConnectionPanel.add(createDbCheckBox, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.8, 0, 2, new Insets(0,10,0,0)).setColumnSpan(4, 1));

        databasePanel.add(dbCredPanel, new GBCBuilder(GridBagConstraints.BOTH, 1, 0.5, 0, 0).setInset(new Insets(10,10,0,10)));
        databasePanel.add(dbConnectionPanel, new GBCBuilder(GridBagConstraints.BOTH, 1, 0.4, 0, 1).setInset(new Insets(10,10,10,10)));
        databasePanel.add(new JSeparator(), new GBCBuilder(GridBagConstraints.BOTH, 1, 0.1, 0, 2).setInset(new Insets(10,0,0,0)));

        settingTab.add("Database", databasePanel);
        settingTab.add("Client", instancePanel);
        frame.add(settingTab, BorderLayout.CENTER);

        frame.add(frameButtonPanel, BorderLayout.SOUTH);
        frameButtonPanel.setLayout(new FlowLayout());
    }

    public JInternalFrame getFrame() {
        return frame;
    }

}
