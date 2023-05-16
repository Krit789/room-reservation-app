package net.itkmitl.room.portal.admin.views;

import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UserDataView {
    private final String[] roleList = new String[]{"User", "Staff", "Admin"};
    private final String[] activeList = new String[]{"Active", "Inactive"};
    public JPanel dataPanel;
    public JButton saveButton, cancelButton, resetPasswordButton;
    public JLabel pageTitle, pageSubtitle;
    public JLabel passwordLabel;
    public JTextField idField, firstNameField, lastNameField, telnumField, createdOnField, emailField;
    public JComboBox<String> roleSelect, activeSelect;
    public JPasswordField passwordField;
    private final JInternalFrame frame;
    private final JPanel dataPanelPad;
    private final JPanel titlePanel;
    private final JPanel buttonPanel;
    private final JPanel namePanel;
    private final JLabel idLabel;
    private final JLabel nameLabel;
    private final JLabel telnumLabel;
    private final JLabel activeLabel;
    private final JLabel createdOnLabel;
    private final JLabel roleLabel;
    private final JLabel emailLabel;

    public UserDataView() {
        frame = new JInternalFrame("User Data", false, true, false, true);
        frame.setFrameIcon(new ImageIcon("resource/icons/tableedit-16px.png"));
        frame.setLayout(new BorderLayout());

        titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(new EmptyBorder(10, 10, 5, 10));

        pageTitle = new JLabel("User Data");
        pageSubtitle = new JLabel("Viewing user data");
        titlePanel.add(pageTitle);
        titlePanel.add(pageSubtitle);
        pageTitle.setFont(new Font("Arial", Font.BOLD, 25));
        pageTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        pageSubtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        frame.add(titlePanel, BorderLayout.NORTH);
        dataPanel = new JPanel();
        dataPanelPad = new JPanel();
        dataPanelPad.setBorder(new EmptyBorder(5, 10, 10, 10));
        dataPanelPad.setLayout(new BorderLayout());
        dataPanel.setBorder(BorderFactory.createTitledBorder("User Data"));
        dataPanel.setLayout(new GridBagLayout());

        idLabel = new JLabel("User ID");
        idField = new JTextField("");
        dataPanel.add(idLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 0, new Insets(5, 10, 5, 5)).getGBC());
        dataPanel.add(idField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 0, new Insets(0, 0, 5, 5)).getGBC());


        namePanel = new JPanel();
        namePanel.setLayout(new GridBagLayout());

        nameLabel = new JLabel("Name");
        firstNameField = new JTextField("");
        lastNameField = new JTextField("");
        namePanel.add(nameLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 0, new Insets(0, 0, 0, 35)).getGBC());
        namePanel.add(firstNameField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.45, 1, 0, new Insets(0, 0, 0, 5)).getGBC());
        namePanel.add(lastNameField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.45, 2, 0, new Insets(0, 0, 0, 0)).getGBC());

        dataPanel.add(namePanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 1, new Insets(0, 10, 5, 10)).setColumnSpan(5, 1));

        telnumLabel = new JLabel("Phone Number");
        telnumField = new JTextField("");
        dataPanel.add(telnumLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 2, new Insets(0, 10, 5, 5)).getGBC());
        dataPanel.add(telnumField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 2, new Insets(0, 0, 5, 10)).setColumnSpan(4, 1));

        emailLabel = new JLabel("E-Mail");
        emailField = new JTextField("");
        dataPanel.add(emailLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 3, new Insets(0, 10, 5, 5)).getGBC());
        dataPanel.add(emailField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 3, new Insets(0, 0, 5, 10)).setColumnSpan(4, 1));


        activeLabel = new JLabel("Activation");
        activeSelect = new JComboBox<>(activeList);
        dataPanel.add(activeLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 4, new Insets(0, 10, 5, 5)).getGBC());
        dataPanel.add(activeSelect, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.4, 1, 4, new Insets(0, 0, 5, 5)).getGBC());

        roleLabel = new JLabel("Role");
        roleSelect = new JComboBox<>(roleList);
        dataPanel.add(roleLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 2, 4, new Insets(0, 10, 5, 5)).getGBC());
        dataPanel.add(roleSelect, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.4, 3, 4, new Insets(0, 0, 5, 5)).getGBC());

        createdOnLabel = new JLabel("Created On");
        createdOnField = new JTextField("");
        dataPanel.add(createdOnLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 5, new Insets(0, 10, 5, 5)).getGBC());
        dataPanel.add(createdOnField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 5, new Insets(0, 0, 5, 10)).setColumnSpan(4, 1));

        idField.setEditable(false);
        createdOnField.setEditable(false);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();

        resetPasswordButton = new JButton("Reset Password");
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");


        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dataPanelPad.add(dataPanel, BorderLayout.CENTER);

        frame.add(dataPanelPad, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
    }

    public JInternalFrame getFrame() {
        return frame;
    }
}
