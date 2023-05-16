package net.itkmitl.room.portal.content.components.dashboard;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.util.FewPassword;
import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.phatsanphon.repository.UserRepository;
import net.itkmitl.room.libs.store.AppStore;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.content.MainContentController;
import net.itkmitl.room.portal.content.components.ReservationDialogController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ProfileEditor extends JDialog implements ActionListener, WindowListener {
    private final JPanel dataPanelPad, titlePanel, buttonPanel;
    public JPanel dataPanel;
    public JButton saveButton, cancelButton, resetPasswordButton;
    public JLabel pageTitle, pageSubtitle;
    private JLabel firstNameLabel, lastNameLabel, telnumLabel, createdOnLabel, emailLabel;
    public JLabel passwordLabel;
    public JTextField firstNameField, lastNameField, telnumField, createdOnField, emailField;
    public JPasswordField oldPasswordField;
    private final Font labelFont = new Font("Sansserif", Font.PLAIN, 16);
    private final User myUser;
    private final Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(44, 84, 144));

    public ProfileEditor(User myUser) {
        super(MainContentController.view, "Profile Editor", false);
        this.myUser = myUser;
        setBackground(Color.white);
        setIconImage(new ImageIcon("resource/icons/tableedit-16px.png").getImage());
        setLayout(new BorderLayout());

        titlePanel = new JPanel();
        titlePanel.setBackground(Color.white);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(new EmptyBorder(10, 10, 5, 10));

        pageTitle = new JLabel("Your Profile");
        pageSubtitle = new JLabel("Make changes to your profile data.");
        titlePanel.add(pageTitle);
        titlePanel.add(pageSubtitle);
        pageTitle.setFont(new Font("Cousine", Font.BOLD, 25));
        pageSubtitle.setFont(new Font("Cousine", Font.PLAIN, 16));
        pageTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        pageSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(titlePanel, BorderLayout.NORTH);
        dataPanel = new JPanel();
        dataPanelPad = new JPanel();
        dataPanelPad.setBackground(Color.white);
        dataPanelPad.setBorder(new EmptyBorder(5, 10, 10, 10));
        dataPanelPad.setLayout(new BorderLayout());
        dataPanel.setLayout(new GridBagLayout());
        dataPanel.setBackground(Color.white);


        firstNameLabel = new JLabel("First Name");
        firstNameLabel.setFont(labelFont);
        lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setFont(labelFont);
        firstNameField = new JTextField("");
        lastNameField = new JTextField("");
        firstNameField.setBorder(border);
        lastNameField.setBorder(border);

        dataPanel.add(firstNameLabel, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 0, new Insets(0, 0, 5, 0)).setAnchor(GridBagConstraints.WEST));
        dataPanel.add(firstNameField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 1, new Insets(0, 0, 10, 0)).getGBC());
        dataPanel.add(lastNameLabel, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 2, new Insets(0, 0, 5, 0)).setAnchor(GridBagConstraints.WEST));
        dataPanel.add(lastNameField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 3, new Insets(0, 0, 10, 0)).getGBC());


        telnumLabel = new JLabel("Phone Number");
        telnumLabel.setFont(labelFont);
        telnumField = new JTextField("");
        telnumField.setBorder(border);

        dataPanel.add(telnumLabel, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 4, new Insets(0, 0, 5, 0)).setAnchor(GridBagConstraints.WEST));
        dataPanel.add(telnumField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 5, new Insets(0, 0, 10, 0)).getGBC());

        emailLabel = new JLabel("E-Mail");
        emailLabel.setFont(labelFont);
        emailField = new JTextField("");
        emailField.setBorder(border);

        dataPanel.add(emailLabel, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 6, new Insets(0, 0, 5, 0)).setAnchor(GridBagConstraints.WEST));
        dataPanel.add(emailField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 7, new Insets(0, 0, 10, 0)).getGBC());

        createdOnLabel = new JLabel("Created On");
        createdOnLabel.setFont(labelFont);
        createdOnField = new JTextField("");
        createdOnField.setBorder(border);
        createdOnField.setEditable(false);

        dataPanel.add(createdOnLabel, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 8, new Insets(0, 0, 5, 0)).setAnchor(GridBagConstraints.WEST));
        dataPanel.add(createdOnField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 9, new Insets(0, 0, 10, 0)).getGBC());

        passwordLabel = new JLabel("Enter Password to Confirm Changes");
        passwordLabel.setFont(labelFont);
        oldPasswordField = new JPasswordField();
        oldPasswordField.setBorder(border);

        dataPanel.add(passwordLabel, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 10, new Insets(0, 0, 5, 0)).setAnchor(GridBagConstraints.WEST));
        dataPanel.add(oldPasswordField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 11, new Insets(0, 0, 10, 0)).getGBC());

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        resetPasswordButton = new JButton("Change Password");
        dataPanel.add(resetPasswordButton, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 12, new Insets(5, 10, 0, 10)).getGBC());

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);
        resetPasswordButton.addActionListener(this);

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dataPanelPad.add(dataPanel, BorderLayout.CENTER);

        add(dataPanelPad, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        populateFields();
        setVisible(true);
        addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)) {

            SwingWorker<?, ?> worker = new SwingWorker<Object, Object>() {
                boolean success = false;
                @Override
                protected Object doInBackground() {
                    try {
                        saveButton.setEnabled(false);
                        cancelButton.setEnabled(false);
                        resetPasswordButton.setEnabled(false);
                        firstNameField.setEnabled(false);
                        lastNameLabel.setEnabled(false);
                        telnumField.setEnabled(false);
                        emailField.setEnabled(false);
                        oldPasswordField.setEnabled(false);
                        FewQuery db = LaewTaeDB.getDB();
                        UserRepository ur = new UserRepository(db);
                        User oldUser = ur.getUserById(myUser.getId());
                        if (FewPassword.checkPassword(String.valueOf(oldPasswordField.getPassword()), oldUser.getPasswordHash())) {
                            myUser.setFirstname(firstNameField.getText());
                            myUser.setLastname(lastNameField.getText());
                            myUser.setTelephoneNumber(telnumField.getText());
                            myUser.setEmail(emailField.getText());
                            ur.updateUser(myUser);
                            AppStore.getAppStore().dispatch("user", myUser);
                            success = true;
                        } else {
                            success = false;
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    return null;
                }

                @Override
                protected void done() {
                    if (success) {
                        dispose();
                        ReservationDialogController.disableGlassPane();
                    } else {
                        JOptionPane.showMessageDialog(null, "Unable to update your profile. Please recheck your password and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        saveButton.setEnabled(true);
                        cancelButton.setEnabled(true);
                        resetPasswordButton.setEnabled(true);
                        firstNameField.setEnabled(true);
                        lastNameLabel.setEnabled(true);
                        telnumField.setEnabled(true);
                        emailField.setEnabled(true);
                        oldPasswordField.setEnabled(true);
                    }
                }
            };
            worker.execute();

        } else if (e.getSource().equals(cancelButton)) {


        } else if (e.getSource().equals(resetPasswordButton)) {
            JPanel panel = new JPanel();
            panel.setBackground(Color.white);
            panel.setLayout(new BorderLayout());
            JPanel titlePanel = new JPanel();
            JLabel pageTitle = new JLabel("Change your Password");
            JLabel pageSubtitle = new JLabel("Enter new password below.");

            titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
            titlePanel.add(pageTitle);
            titlePanel.add(pageSubtitle);
            pageTitle.setFont(new Font("Cousine", Font.BOLD, 25));
            pageSubtitle.setFont(new Font("Cousine", Font.PLAIN, 14));
            titlePanel.setBorder(new EmptyBorder(0, 0, 5, 10));
            pageTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
            pageSubtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
            JPasswordField passwordField = new JPasswordField(20);
            passwordField.setBorder(border);
            panel.add(titlePanel, BorderLayout.NORTH);
            panel.add(passwordField, BorderLayout.CENTER);
            String[] options = new String[]{"OK", "Cancel"};
            int option = JOptionPane.showOptionDialog(BaseWindow.baseFrame, panel, "Change Password", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon("resource/icons/key-32px.png"), options, options[0]);
            if (option == 0 && !passwordField.getPassword().equals("") && String.valueOf(passwordField.getPassword()).length() > 4) {
                myUser.setPasswordHash(FewPassword.getSalt(new String(passwordField.getPassword())));
            } else if (option == 0) {
                JOptionPane.showMessageDialog(null, "Password must be longer than 4 characters", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void populateFields() {
        firstNameField.setText(myUser.getFirstname());
        lastNameField.setText(myUser.getLastname());
        emailField.setText(myUser.getEmail());
        telnumField.setText(myUser.getTelephoneNumber());
        createdOnField.setText(myUser.getCreatedOn().toString());
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        ReservationDialogController.disableGlassPane();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
