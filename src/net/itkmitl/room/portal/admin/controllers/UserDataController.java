package net.itkmitl.room.portal.admin.controllers;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.enums.EnumUserRole;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.util.FewPassword;
import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.phatsanphon.repository.UserRepository;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.admin.views.UserDataView;
import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.LoadingDialog;

public class UserDataController implements ActionListener, InternalFrameListener {
    UserDataView view;
    User user;
    Object[] data;
    int mode;
    DataListEditableController dlec;

    public UserDataController(int mode, int userID, DataListEditableController dlec)  throws Exception {
        this.mode = mode;
        this.dlec = dlec;
        view = new UserDataView();
        user = new User();
        switch (mode) {
            case 0: // Just Load
                view.getFrame().setTitle("View User");
                view.pageTitle.setText("User Records Viewer");
                view.dataPanel.add(view.resetPasswordButton, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0, 4, 6, new Insets(10, 0, 10, 10)).getGBC());
                view.getFrame().pack();
                view.saveButton.setEnabled(false);
                view.cancelButton.addActionListener(this);
                databaseLoader(userID);
                break;
            case 1: // Update
                view.getFrame().setTitle("Update User");
                view.pageTitle.setText("User Records Update");
                view.dataPanel.add(view.resetPasswordButton, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0, 4, 6, new Insets(10, 0, 10, 10)).getGBC());
                view.getFrame().pack();
                view.saveButton.addActionListener(this);
                view.cancelButton.addActionListener(this);
                view.resetPasswordButton.addActionListener(this);
                databaseLoader(userID);
                break;
            case 2: // Create
                view.dataPanel.add(view.passwordLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.05, 0, 6, new Insets(0, 10, 5, 5)).getGBC());
                view.dataPanel.add(view.passwordField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 6, new Insets(0, 0, 5, 5)).setColumnSpan(3, 1));
                view.dataPanel.add(view.resetPasswordButton, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.05, 4, 6, new Insets(0, 0, 5, 10)).getGBC());
                view.saveButton.addActionListener(this);
                view.cancelButton.addActionListener(this);
                view.resetPasswordButton.setEnabled(false);
                view.getFrame().setTitle("User Creation");
                view.pageTitle.setText("User Records Creator");
                view.pageSubtitle.setText("Create new user records");
                view.saveButton.setText("Create");
                view.getFrame().pack();
                break;
            case 3: // Delete
                user.setId(userID);
                databaseCommiter(user, 1);
                break;
        }
        view.getFrame().addInternalFrameListener(this);

    }

    public void databaseLoader(int userID) {
        SwingWorker<?, ?> worker = new SwingWorker<>() {
            LoadingDialog ld = new LoadingDialog();

            @Override
            protected String doInBackground() throws Exception{
                String errorMessage;
                ld.dialog.setVisible(true);
                BaseWindow.progressBar.setIndeterminate(true);
                FewQuery db = LaewTaeDB.getDB();
                try {
                    User myUser = new UserRepository(db).getUserById(userID);
                    data = new Object[]{true, myUser};
                } catch (Exception ex) {
                    errorMessage = ex.getMessage();
                    data = new Object[]{false, errorMessage};
                }
                return "";
            }

            @Override
            protected void done() {
                dataLoader();
                ld.dialog.dispose();
                BaseWindow.progressBar.setIndeterminate(false);
            }
        };
        worker.execute();
    }

    public void databaseCommiter(User user, int mode) {
    	SwingWorker<?, ?> worker = new SwingWorker<>() {
            LoadingDialog ld = new LoadingDialog();

            @Override
            protected String doInBackground() throws Exception {
                String errorMessage;
                ld.dialog.setVisible(true);
                BaseWindow.progressBar.setIndeterminate(true);
                FewQuery db = LaewTaeDB.getDB();
                UserRepository myUser = new UserRepository(db);
                switch (mode) {
                    case 0: // Update
                        try {
                            myUser.updateUser(user);
                            data = new Object[]{true, myUser};
                        } catch (Exception ex) {
                            errorMessage = ex.getMessage();
                            data = new Object[]{false, errorMessage};
                        }
                        break;
                    case 1: // Delete
                        try {
                            myUser.deleteUserById(user.getId());
                            data = new Object[]{true, myUser};
                        } catch (Exception ex) {
                            errorMessage = ex.getMessage();
                            data = new Object[]{false, errorMessage};
                        }
                        break;
                    case 2: // Create
                        try {
                            myUser.createUser(user);
                            data = new Object[]{true, myUser};
                        } catch (Exception ex) {
                            errorMessage = ex.getMessage();
                            data = new Object[]{false, errorMessage};
                        }
                        break;
                }

                return "";
            }

            @Override
            protected void done() {
                ld.dialog.dispose();
                BaseWindow.progressBar.setIndeterminate(false);
                if (mode != 1) {
                    view.getFrame().dispose();
                }
                dlec.reloadData();
//                DatabaseLoader dbl = new DatabaseLoader();
//                dbl.databaseLoader(1, 99, "", true, dlec);

            }
        };
        worker.execute();
    }

    private void dataLoader() {
        if ((boolean) data[0]) {
            User myUser = (User) data[1];
            user = (User) data[1];
            view.pageSubtitle.setText("User records for User ID " + myUser.getId());
            view.idField.setText(user.getId() + "");
            view.firstNameField.setText(user.getFirstname());
            view.lastNameField.setText(user.getLastname());
            view.telnumField.setText(user.getTelephoneNumber());
            view.emailField.setText(user.getEmail());
            view.activeSelect.setSelectedIndex(user.isActive() ? 0 : 1);
            switch (user.getRole().getLevel()) {
                case 1:
                    view.roleSelect.setSelectedIndex(0);
                    break;
                case 10:
                    view.roleSelect.setSelectedIndex(1);
                    break;
                case 99:
                    view.roleSelect.setSelectedIndex(2);
                    break;
            }
            view.createdOnField.setText(user.getCreatedOn().toString());

            switch (mode) {
                case 0:
                    view.firstNameField.setEnabled(false);
                    view.lastNameField.setEnabled(false);
                    view.telnumField.setEnabled(false);
                    view.activeSelect.setEnabled(false);
                    view.roleSelect.setEnabled(false);
                    view.emailField.setEnabled(false);
                    view.resetPasswordButton.setEnabled(false);
                    break;
            }
            view.getFrame().pack();
        } else {
            view.getFrame().dispose();
            JOptionPane.showMessageDialog(BaseWindow.baseFrame, data[1].toString(), "Database Query Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.saveButton)) {
            user.setFirstname(view.firstNameField.getText());
            user.setLastname(view.lastNameField.getText());
            user.setEmail(view.emailField.getText());
            user.setTelephoneNumber(view.telnumField.getText());
            if (view.activeSelect.getSelectedIndex() == 0) {
                user.setActive(true);
            } else if (view.activeSelect.getSelectedIndex() == 1) {
                user.setActive(false);
            }
            switch (view.roleSelect.getSelectedIndex()) {
                case 0:
                    user.setRole(EnumUserRole.STUDENT);
                    break;
                case 1:
                    user.setRole(EnumUserRole.STAFF);
                    break;
                case 2:
                    user.setRole(EnumUserRole.ADMIN);
                    break;
            }
            switch (mode) {
                case 1: // Update
                    databaseCommiter(user, 0);
                    break;
                case 2: // Create
                    user.setPasswordHash(FewPassword.getSalt(String.valueOf(view.passwordField.getPassword())));
                    databaseCommiter(user, 2);
                    break;
            }

        } else if (e.getSource().equals(view.cancelButton)) {
            view.getFrame().dispose();
        } else if (e.getSource().equals(view.resetPasswordButton)) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            JPanel titlePanel = new JPanel();
            JLabel pageTitle = new JLabel("Reset User Password");
            JLabel pageSubtitle = new JLabel("Enter new password below.");

            titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
            titlePanel.add(pageTitle);
            titlePanel.add(pageSubtitle);
            pageTitle.setFont(new Font("Arial", Font.BOLD, 25));
            titlePanel.setBorder(new EmptyBorder(0, 0, 5, 10));
            pageTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
            pageSubtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

            JPasswordField passwordField = new JPasswordField(20);
            panel.add(titlePanel, BorderLayout.NORTH);
            panel.add(passwordField, BorderLayout.CENTER);
            String[] options = new String[]{"OK", "Cancel"};
            int option = JOptionPane.showOptionDialog(BaseWindow.baseFrame, panel, "Reset Password", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon("resource/icons/key-32px.png"), options, options[0]);
            if (option == 0 && !passwordField.getPassword().equals("")) // pressing OK button
            {
                user.setPasswordHash(FewPassword.getSalt(new String(passwordField.getPassword())));
            }
        }
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        BaseWindow.statusLabel.setText(e.getInternalFrame().getTitle() + " was opened.");
        JMenuItem newItem = new JMenuItem(e.getInternalFrame().getTitle());
        BaseWindow.windowList.put(e.getInternalFrame(), newItem);
        newItem.setIcon(e.getInternalFrame().getFrameIcon());
        BaseWindow.windowMenu.add(newItem);
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
        try {
            BaseWindow.windowMenu.remove(BaseWindow.windowList.get(e.getInternalFrame()));
            BaseWindow.windowList.remove(e.getInternalFrame());
//            System.out.println("Removal Success: " + e.getInternalFrame().getTitle() + " " + e.getInternalFrame().getClass().getCanonicalName() + " " + this.getClass().getSimpleName());
        } catch (Exception ex) {
            System.out.println("Removal Failure: " + e.getInternalFrame().getTitle() + " " + e.getSource().toString() + " " + e.getInternalFrame().getClass().getCanonicalName() + " " + this.getClass().getSimpleName());
//            ex.printStackTrace();
        }
    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
    }
}
