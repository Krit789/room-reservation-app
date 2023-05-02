package net.itkmitl.room.portal.admin.controllers;

import com.formdev.flatlaf.FlatIntelliJLaf;
import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.util.FewPassword;
import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.phatsanphon.repository.UserRepository;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.admin.views.AuthView;
import net.itkmitl.room.portal.components.LoadingDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthController implements ActionListener {
    private AuthView view;
    public static boolean authenticated = false;
    private Object[] data;

    public AuthController() {
        view = new AuthView();
        view.loginButton.addActionListener(this);
        view.cancelButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.loginButton)) {
            view.alertLabel.setText("Processing...");
            authenticateUser(view.loginField.getText(), String.valueOf(view.passwordField.getPassword()));
        } else if (e.getSource().equals(view.cancelButton)) {
            view.getFrame().dispose();
        }
    }

    private void authenticateUser(String email, String password) {
        SwingWorker worker = new SwingWorker<>() {
            String errorMessage;

            @Override
            protected String doInBackground() {
                try {
                    FewQuery db = RVDB.getDB();
                    UserRepository userRepository = new UserRepository(db);

                    User myUser = userRepository.getUserByEmail(email);
                    if (myUser != null) {
                        if (FewPassword.checkPassword(password, myUser.getPasswordHash()) && myUser.getRole().getLevel() >= 10) {
                            AuthController.authenticated = true;
                            data = new Object[]{true, myUser};
                        } else {
                            view.alertLabel.setText("Invalid Password or Insufficient Permission!");
                            data = new Object[]{false, "Invalid Password or Insufficient Permission!"};
                        }
                    } else {
                        data = new Object[]{false, "User not found!"};
                        view.alertLabel.setText("User not found!");

                    }
                } catch (Exception ex) {
                    errorMessage = ex.getMessage();
                    data = new Object[]{false, errorMessage};
                }
                return "";
            }

            @Override
            protected void done() {
                if ((boolean) data[0]) {
                    BaseWindow.main(new String[]{""});
                    view.getFrame().dispose();
                } else {

                }
            }
        };
        worker.execute();
    }

    public static void main(String[] args) {
        try {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.application.name", "Laew Tae Hong");
            System.setProperty("apple.awt.application.appearance", "system");
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (Exception ignored) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            }
        } catch (Exception ignored) {
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AuthController();
            }
        });
    }
}
