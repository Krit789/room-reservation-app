package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.enums.EnumUserRole;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.util.FewPassword;
import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.phatsanphon.repository.UserRepository;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.admin.models.UserDataModel;
import net.itkmitl.room.portal.admin.views.UserDataView;
import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.LoadingDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserDataController implements ActionListener {
    UserDataView view;
    UserDataModel model;
    User user;
    Object[] data;
    int mode;

    public UserDataController(int mode, int userID) {
        this.mode = mode;
        view = new UserDataView();
        model = new UserDataModel();
        view.saveButton.addActionListener(this);
        view.cancelButton.addActionListener(this);
        view.resetPasswordButton.addActionListener(this);
        switch (mode) {
            case 0: // Just Load
            case 1: // Update
                view.dataPanel.add(view.resetPasswordButton, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0, 4, 6, new Insets(10, 0, 10, 10)).getGBC());
                view.getFrame().pack();
                databaseLoader(userID);
                break;
            case 2: // Create
                user = new User();
                view.dataPanel.add(view.passwordLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 6, new Insets(0, 10, 5, 5)).getGBC());
                view.dataPanel.add(view.passwordField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 6, new Insets(0, 0, 5, 10)).setColumnSpan(4, 1));
                view.saveButton.setText("Create");
                view.getFrame().pack();
                break;
            case 3: // Delete
                user = new User();
                user.setId(userID);
                databaseCommiter(user, 1);
                break;
        }
    }

    public void databaseLoader(int userID) {
        SwingWorker worker = new SwingWorker() {
            LoadingDialog ld = new LoadingDialog();

            @Override
            protected String doInBackground() {
                String errorMessage;
                ld.dialog.setVisible(true);
                BaseWindow.progressBar.setIndeterminate(true);
                FewQuery db = RVDB.getDB();
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
        SwingWorker worker = new SwingWorker() {
            LoadingDialog ld = new LoadingDialog();

            @Override
            protected String doInBackground() {
                String errorMessage;
                ld.dialog.setVisible(true);
                BaseWindow.progressBar.setIndeterminate(true);
                FewQuery db = RVDB.getDB();
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
                view.getFrame().dispose();

            }
        };
        worker.execute();
    }

    private void dataLoader() {
        if ((boolean) data[0]) {
            User myUser = (User) data[1];
            user = (User) data[1];
            model.setPageTitle("Record Data View");
            model.setPageSubtitle("For user ID " + myUser.getId());
            model.setUserID(myUser.getId());
            model.setFirstName(myUser.getFirstname());
            model.setLastName(myUser.getLastname());
            model.setPhoneNumber(myUser.getTelephoneNumber());
            model.setEmail(myUser.getEmail());
            model.setActivation(myUser.isActive());
            model.setRole(myUser.getRole().getLevel());
            model.setCreatedOn(myUser.getCreatedOn());

            view.getFrame().setTitle("Records Viewer");
            view.pageTitle.setText(model.getPageTitle());
            view.pageSubtitle.setText(model.getPageSubtitle());
            view.idField.setText(model.getUserID() + "");
            view.firstNameField.setText(model.getFirstName());
            view.lastNameField.setText(model.getLastName());
            view.telnumField.setText(model.getPhoneNumber());
            view.emailField.setText(model.getEmail());
            view.activeSelect.setSelectedIndex(model.isActivation() ? 0 : 1);
            switch (model.getRole()) {
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
            view.createdOnField.setText(model.getCreatedOn().toString());

            switch (mode) {
                case 0:
                    view.firstNameField.setEditable(false);
                    view.lastNameField.setEditable(false);
                    view.telnumField.setEditable(false);
                    view.activeSelect.setEnabled(false);
                    view.roleSelect.setEnabled(false);
                    view.emailField.setEnabled(false);
                    view.resetPasswordButton.setEnabled(false);
                    break;
            }
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
            System.out.println(view.roleSelect.getSelectedIndex());
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
            view.getFrame().dispose();

        } else if (e.getSource().equals(view.cancelButton)) {
            view.getFrame().dispose();
        }
    }
}
