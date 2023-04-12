package net.itkmitl.room.portal.admin.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreferenceWindow extends InternalFrame implements ChangeListener, ActionListener {
    private final JInternalFrame frame;
    private final JTabbedPane settingTab;
    private final JPanel databasePanel, instancePanel, titlePanel, frameButtonPanel, dbCredPanel, dbConnectionPanel, timeoutPanel, resetPanel;
    private final JButton okButton, cancelButton, applyButton, resetButton;
    private final JLabel pageTitle, dbAddressLabel, dbPortLabel, dbNameLabel, dbUserLabel, dbPasswordLabel, timeoutLabel1, timeoutLabel2, resetLabel;
    private final JTextField dbAddressTextField, dbNameTextField, dbUserTextField;
    private final JCheckBox createDbCheckBox, timeoutCheckBox;
    private final JSpinner dbPortSpinner, timeoutSpinner;
    private final JPasswordField dbPasswordField;
    private boolean neverTimeout;

    public PreferenceWindow() {
        frame = new JInternalFrame("Preferences", false, true, false, false);
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
        settingTab.addChangeListener(this);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        applyButton = new JButton("Apply");
        frameButtonPanel.add(okButton);
        frameButtonPanel.add(cancelButton);
        frameButtonPanel.add(applyButton);

        // Database Panel
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
        createDbCheckBox = new JCheckBox("Create new database with the specified name if the database doesn't exist");

        dbCredPanel.add(dbUserLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 0).setInset(new Insets(0, 10, 5, 0)));
        dbCredPanel.add(dbUserTextField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 0).setInset(new Insets(0, 0, 5, 10)));

        dbCredPanel.add(dbPasswordLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 1).setInset(new Insets(0, 10, 0, 0)));
        dbCredPanel.add(dbPasswordField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 1).setInset(new Insets(0, 0, 0, 10)));

        dbConnectionPanel.add(dbAddressLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.2, 0, 0).setInset(new Insets(0, 10, 5, 0)));
        dbConnectionPanel.add(dbAddressTextField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 1, 0).setInset(new Insets(0, 0, 5, 10)));
        dbConnectionPanel.add(dbPortLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 2, 0).setInset(new Insets(0, 10, 5, 0)));
        dbConnectionPanel.add(dbPortSpinner, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.2, 3, 0).setInset(new Insets(0, 0, 5, 10)));

        dbConnectionPanel.add(dbNameLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.2, 0, 1).setInset(new Insets(0, 10, 5, 0)));
        dbConnectionPanel.add(dbNameTextField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.8, 1, 1).setInset(new Insets(0, 0, 5, 10)));
        dbConnectionPanel.add(createDbCheckBox, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.8, 0, 2, new Insets(0, 10, 0, 0)).setColumnSpan(4, 1));

        databasePanel.add(dbCredPanel, new GBCBuilder(GridBagConstraints.BOTH, 1, 0.5, 0, 0).setInset(new Insets(10, 10, 0, 10)));
        databasePanel.add(dbConnectionPanel, new GBCBuilder(GridBagConstraints.BOTH, 1, 0.4, 0, 1).setInset(new Insets(10, 10, 10, 10)));
        databasePanel.add(new JSeparator(), new GBCBuilder(GridBagConstraints.BOTH, 1, 0.1, 0, 2).setInset(new Insets(10, 0, 0, 0)));


        // Instance Panel
        instancePanel.setLayout(new GridBagLayout());
        timeoutPanel = new JPanel();
        timeoutPanel.setLayout(new GridBagLayout());
        timeoutPanel.setBorder(BorderFactory.createTitledBorder("Timeout Parameters"));
        timeoutSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1440, 1));
        timeoutLabel1 = new JLabel("Automatically timeout logged-in user in");
        timeoutLabel2 = new JLabel("Minute(s)");

        timeoutPanel.add(timeoutLabel1, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 0, new Insets(10, 10, 5, 0)).getGBC());
        timeoutPanel.add(timeoutSpinner, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 1, 0, new Insets(10, 5, 5, 0)).getGBC());
        timeoutPanel.add(timeoutLabel2, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 2, 0, new Insets(10, 5, 5, 0)).getGBC());
        timeoutPanel.add(new JPanel(), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.7, 3, 0, new Insets(10, 0, 5, 10)).getGBC());

        timeoutCheckBox = new JCheckBox("Never timeout users");
        timeoutCheckBox.addActionListener(this);
        timeoutPanel.add(timeoutCheckBox, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 2, new Insets(0, 10, 10, 10)).setColumnSpan(4, 1));


        instancePanel.add(timeoutPanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0.1, 0, 0, new Insets(10, 10, 0, 10)).getGBC());


        resetPanel = new JPanel();
        resetPanel.setLayout(new GridBagLayout());

        resetLabel = new JLabel("Reset configuration data and start over. This action can't be revert!");
        resetPanel.add(resetLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 0, 0).getGBC());

        instancePanel.add(new JPanel(), new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0.7, 0, 2).getGBC());


        resetButton = new JButton("Reset");
        resetPanel.add(resetButton, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 1, 0).getGBC());
        instancePanel.add(resetPanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0.1, 0, 3, new Insets(0, 10, 0, 10)).getGBC());



        settingTab.add("Database", databasePanel);
        settingTab.add("Client", instancePanel);
        frame.add(settingTab, BorderLayout.CENTER);

        frame.add(frameButtonPanel, BorderLayout.SOUTH);
        frameButtonPanel.setLayout(new FlowLayout());
    }


    public void stateChanged(ChangeEvent e) {
        if (e.getSource().equals(settingTab)) {
            switch (settingTab.getSelectedIndex()) {
                case 0:
                    pageTitle.setText("Database");
                    break;
                case 1:
                    pageTitle.setText("Client");
                    break;
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancelButton)) {
            try {
                frame.setClosed(true);

            } catch (Exception ignored) {
            }
        } else if (e.getSource().equals(timeoutCheckBox)){
            if (timeoutCheckBox.isSelected()){
                neverTimeout = true;
                timeoutSpinner.setEnabled(false);
            } else {
                neverTimeout = false;
                timeoutSpinner.setEnabled(true);
            }
        }
    }

    public JInternalFrame getFrame() {
        return frame;
    }

}
