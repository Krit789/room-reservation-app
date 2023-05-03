package net.itkmitl.room.portal.admin.components.OOBE;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.enums.EnumDBSchema;
import net.itkmitl.room.enums.EnumUserRole;
import net.itkmitl.room.libs.jarukrit.ConfigManager;
import net.itkmitl.room.libs.peeranat.FewDB;
import net.itkmitl.room.libs.peeranat.config.FewConfig;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.util.FewPassword;
import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.phatsanphon.repository.UserRepository;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLSyntaxErrorException;
import java.util.UUID;

public class OOBEController implements ActionListener, DocumentListener {
    OOBEView view;
    private int currentPage;
    SwingWorker worker;

    public OOBEController() {
        FewConfig config = new FewConfig(new File("config.yml"));
        if (config.asString("first_run") == null) {
            view = new OOBEView();
            view.nextButton.addActionListener(this);
            view.backButton.addActionListener(this);
            view.cancelButton.addActionListener(this);
            view.backButton.setEnabled(false);

            view.dbsp.dbUserTextField.getDocument().addDocumentListener(this);
            view.dbsp.dbPasswordField.getDocument().addDocumentListener(this);
            view.dbsp.dbNameTextField.getDocument().addDocumentListener(this);
            view.dbsp.dbAddressTextField.getDocument().addDocumentListener(this);

            CardLayout c = (CardLayout) view.contentPanel.getLayout();
            c.show(view.contentPanel, OOBEView.PANEL[currentPage]);
        } else {
            JOptionPane.showMessageDialog(null, "You have already setup Laew Tae Hong, we're forwarding you to admin interface", "Notice", JOptionPane.INFORMATION_MESSAGE);
            BaseWindow.main(new String[]{});
        }
    }

    private void initializeDatabase() {
        worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                try {
                    FewDB.createDatabase(view.dbsp.dbNameTextField.getText());
                    view.dbt.setDescription("We have established connection with your database, please wait while we're processing the following operations.");
                    view.dbt.revalidate();

                    view.dbt.resultPanel.add(view.dbt.connectionLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.99, 0, 0, new Insets(0,5,5,5)).getGBC());
                    view.dbt.resultPanel.add(view.dbt.credentialLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.99, 0, 1, new Insets(5,5,5,5)).getGBC());
                    view.dbt.resultPanel.add(view.dbt.databaseLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.99, 0, 2, new Insets(5,5,5,5)).getGBC());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/yes-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 0, new Insets(0, 0, 5, 5)).getGBC());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/yes-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 1, new Insets(0, 0, 5, 5)).getGBC());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/yes-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 2, new Insets(0, 0, 5, 5)).getGBC());
                    view.dbt.resultPanel.revalidate();

                    FewDB.createTable(EnumDBSchema.USER);
                    view.dbt.resultPanel.add(view.dbt.userTableLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.99, 0, 3, new Insets(5,5,5,5)).getGBC());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/yes-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 3, new Insets(0, 0, 5, 5)).getGBC());
                    view.dbt.resultPanel.revalidate();

                    FewDB.createTable(EnumDBSchema.ROOM);
                    view.dbt.resultPanel.add(view.dbt.roomTableLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.99, 0, 4, new Insets(5,5,5,5)).getGBC());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/yes-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 4, new Insets(0, 0, 5, 5)).getGBC());
                    view.dbt.resultPanel.revalidate();

                    FewDB.createTable(EnumDBSchema.RESERVATION);
                    view.dbt.resultPanel.add(view.dbt.reservationTableLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.99, 0, 5, new Insets(5,5,5,5)).getGBC());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/yes-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 5, new Insets(0, 0, 5, 5)).getGBC());
                    view.dbt.resultPanel.revalidate();

                    FewDB.createTable(EnumDBSchema.FEEDBACK);
                    view.dbt.resultPanel.add(view.dbt.feedbackTableLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.99, 0, 6, new Insets(5,5,5,5)).getGBC());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/yes-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 6, new Insets(0, 0, 5, 5)).getGBC());
                    view.dbt.resultPanel.revalidate();

                    FewDB.createTable(EnumDBSchema.FEEDBACK_INDEX);
                    FewDB.createTable(EnumDBSchema.RESERVATION_INDEX);
                    view.dbt.resultPanel.add(view.dbt.tableIndexLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.99, 0, 7, new Insets(5,5,5,5)).getGBC());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/yes-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 7, new Insets(0, 0, 5, 5)).getGBC());
                    view.dbt.resultPanel.revalidate();

                    FewDB.createTable(EnumDBSchema.FEEDBACK_RELATIONSHIP);
                    FewDB.createTable(EnumDBSchema.RESERVATION_RELATIONSHIP);
                    view.dbt.resultPanel.add(view.dbt.tableRelationshipLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.99, 0, 8, new Insets(5,5,0,5)).getGBC());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/yes-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 8, new Insets(0, 0, 5, 5)).getGBC());

                    view.dbt.setDescription("We have established connection with your database, operations completed without errors!");

                    view.nextButton.setEnabled(true);
                    view.dbt.revalidate();

                } catch (CommunicationsException ex) {
                    view.dbt.setDescription("We failed to establish connection with your database, please recheck your credentials, address and port and then try again.");
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/no-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 0, new Insets(0, 0, 5, 5)).getGBC());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/no-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 1, new Insets(0, 0, 5, 5)).getGBC());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/no-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 2, new Insets(0, 0, 5, 5)).getGBC());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/no-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 3, new Insets(0, 0, 5, 5)).getGBC());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/no-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 4, new Insets(0, 0, 5, 5)).getGBC());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/no-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 5, new Insets(0, 0, 5, 5)).getGBC());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/no-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 6, new Insets(0, 0, 5, 5)).getGBC());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/no-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 7, new Insets(0, 0, 5, 5)).getGBC());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/no-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 8, new Insets(0, 0, 5, 5)).getGBC());
                    view.dbt.revalidate();
                    JOptionPane.showMessageDialog(view.getFrame(), ex.getMessage(), "Unable to Proceed", JOptionPane.ERROR_MESSAGE);
                } catch (SQLSyntaxErrorException ex) {
                    view.dbt.setDescription("We have established connection with your database, but there was a SQL error if you have previously use Laew Tae Hong before you can ignore and continue.");
                    JOptionPane.showMessageDialog(view.getFrame(), ex.getMessage(), "Warning!", JOptionPane.WARNING_MESSAGE);
                    view.nextButton.setEnabled(true);
                }
                return null;
            }

            @Override
            protected void done() {
            }
        };
        worker.execute();
    }

    private void initializeAdministrator() {
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                try {
                    view.fn.firstPassWordField.setText("Waiting For Database");
                    view.fn.firstPassWordField.setFont(new Font("SansSerif", Font.ITALIC, 16));
                    view.fn.firstPassWordField.setForeground(new Color(115, 115, 115));
                    FewQuery db = RVDB.getDB();
                    UserRepository myUser = new UserRepository(db);
                    User admin = new User();
                    final String uuid = UUID.randomUUID().toString().replace("-", "");
                    admin.setPasswordHash(FewPassword.getSalt(uuid));
                    admin.setFirstname("Laew Tae");
                    admin.setLastname("Hong");
                    admin.setRole(EnumUserRole.ADMIN);
                    admin.setEmail("");
                    admin.setActive(true);
                    admin.setTelephoneNumber("0999999999");
                    myUser.createUser(admin);
                    view.fn.firstPassWordField.setText(uuid);
                    view.fn.firstPassWordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
                    view.fn.firstPassWordField.setForeground(new Color(0, 0, 0));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view.getFrame(), ex.getMessage(), "Database Query Error", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }

            @Override
            protected void done() {
                view.nextButton.setEnabled(true);
            }
        };
        worker.execute();
    }


    private void saveConfig() {
        ConfigManager.saveConnection(view.dbsp.dbNameTextField.getText(), view.dbsp.dbAddressTextField.getText(), (int) view.dbsp.dbPortSpinner.getValue(), view.dbsp.dbUserTextField.getText(), String.valueOf(view.dbsp.dbPasswordField.getPassword()));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.nextButton)) {
            if (currentPage + 1 == OOBEView.PANEL.length){
                currentPage++;
                FewConfig config = new FewConfig(new File("config.yml"));
                config.set("timeout_time", 5);
                config.set("never_timeout", false);
                config.set("first_run", false);
                config.saveConfig();
                view.getFrame().dispose();
                BaseWindow.main(new String[]{});
            }
            if (currentPage < OOBEView.PANEL.length - 1) {
                CardLayout c = (CardLayout) view.contentPanel.getLayout();
                c.show(view.contentPanel, OOBEView.PANEL[currentPage + 1]);

                if (currentPage == 1) {
                    saveConfig();
                    initializeDatabase();
                }
                currentPage++;
                 if (currentPage == 3) {
                    view.nextButton.setText("Finish");
                    initializeAdministrator();
                }
                view.backButton.setEnabled(true);
                if (currentPage == OOBEView.PANEL.length - 1 || currentPage >= 1) {
                    view.nextButton.setEnabled(false);
                }
            } else {
                view.nextButton.setEnabled(false);
            }

        } else if (e.getSource().equals(view.backButton)) {
            if (currentPage > 0) {
                CardLayout c = (CardLayout) view.contentPanel.getLayout();
                c.show(view.contentPanel, OOBEView.PANEL[currentPage - 1]);
                if (currentPage == 2){
                    worker.cancel(true);
                }
                currentPage--;
                view.nextButton.setEnabled(true);
                if (currentPage == 0) {
                    view.backButton.setEnabled(false);
                }
            } else {
                view.backButton.setEnabled(false);
            }

        } else if (e.getSource().equals(view.cancelButton)) {
            view.getFrame().dispose();
        }
    }

    private void validateTextField(){
        if (
        (view.dbsp.dbUserTextField.getText().length() > 0) &&
        (String.valueOf(view.dbsp.dbPasswordField.getPassword()).length() > 0) &&
        (view.dbsp.dbNameTextField.getText().length() > 0) &&
        (view.dbsp.dbAddressTextField.getText().length() > 0)
        ) {
            view.nextButton.setEnabled(true);
        } else {
            view.nextButton.setEnabled(false);
        }

    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        validateTextField();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        validateTextField();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        validateTextField();
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

        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run() {
                new OOBEController(); }
        } );

    }
}
