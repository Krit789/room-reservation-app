package net.itkmitl.room.portal.admin.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreferenceWindow extends InternalFrame {
    private final JInternalFrame frame;
    private final JTabbedPane settingTab;
    private final JPanel databasePanel, instancePanel, titlePanel, frameButtonPanel;
    private final JButton okButton, cancelButton, applyButton;
    private final JLabel pageTitle, dbAddressLabel, dbPortLabel, dbNameLabel, dbUserLabel, dbPasswordLabel;

    public PreferenceWindow() {
        frame = new JInternalFrame("Preferences", true, true, false, false);
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
        dbAddressLabel = new JLabel("Address");
        dbPortLabel = new JLabel("Port");
        dbNameLabel = new JLabel("Name");
        dbUserLabel = new JLabel("Username");
        dbPasswordLabel = new JLabel("Password");

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
