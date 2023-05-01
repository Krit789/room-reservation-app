package net.itkmitl.room.portal.admin.components.OOBE;

import com.formdev.flatlaf.FlatIntelliJLaf;
import net.itkmitl.room.enums.EnumDBSchema;
import net.itkmitl.room.libs.jarukrit.ConfigManager;
import net.itkmitl.room.libs.peeranat.FewDB;
import net.itkmitl.room.libs.peeranat.config.FewConfig;
import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.dashboard.Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OOBEController implements ActionListener {
    OOBEView view;
    private int currentPage;

    public OOBEController() {
        FewConfig config = new FewConfig(new File("config.yml"));
        if (config.asString("first_run") == null) {
            view = new OOBEView();
            view.nextButton.addActionListener(this);
            view.backButton.addActionListener(this);
            view.cancelButton.addActionListener(this);
            view.backButton.setEnabled(false);
        } else {
            new Dashboard();
        }
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
        new OOBEController();
    }

    private void saveConfig() {
        ConfigManager.saveConnection(view.dbsp.dbNameTextField.getText(), view.dbsp.dbAddressTextField.getText(), (int) view.dbsp.dbPortSpinner.getValue(), view.dbsp.dbUserTextField.getText(), String.valueOf(view.dbsp.dbPasswordField.getPassword()));
    }

    private void initializeDatabase() {
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                try {
                    FewDB.createDatabase(view.dbsp.dbNameTextField.getText());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/yes-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 0, new Insets(0,0,5,5)).getGBC());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/yes-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 1, new Insets(0,0,5,5)).getGBC());
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/yes-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 2, new Insets(0,0,5,5)).getGBC());
                    FewDB.createTable(EnumDBSchema.USER);
                    FewDB.createTable(EnumDBSchema.ROOM);
                    FewDB.createTable(EnumDBSchema.RESERVATION);
                    FewDB.createTable(EnumDBSchema.FEEDBACK);
                    FewDB.createTable(EnumDBSchema.FEEDBACK_INDEX);
                    FewDB.createTable(EnumDBSchema.RESERVATION_INDEX);
                    FewDB.createTable(EnumDBSchema.FEEDBACK_RELATIONSHIP);
                    FewDB.createTable(EnumDBSchema.RESERVATION_RELATIONSHIP);
                    view.dbt.resultPanel.add(new JLabel(new ImageIcon("resource/icons/yes-16px.png")), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 3, new Insets(0,0,5,5)).getGBC());
                    view.dbt.revalidate();
                } catch (Exception ex){
                    throw ex;
                }
                return null;
            }
            @Override
            protected void done(){
            }
        };

        worker.execute();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.nextButton)) {
            if (currentPage < OOBEView.PANEL.length - 1) {
                CardLayout c = (CardLayout) view.contentPanel.getLayout();
                c.show(view.contentPanel, OOBEView.PANEL[currentPage + 1]);

                if (currentPage == 1) {
                    saveConfig();
                    initializeDatabase();
                }

                currentPage++;
                view.backButton.setEnabled(true);
                if (currentPage == OOBEView.PANEL.length - 1) {
                    view.nextButton.setEnabled(false);
                }
            } else {
                view.nextButton.setEnabled(false);
            }

        } else if (e.getSource().equals(view.backButton)) {
            if (currentPage > 0) {
                CardLayout c = (CardLayout) view.contentPanel.getLayout();
                c.show(view.contentPanel, OOBEView.PANEL[currentPage - 1]);
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
}
