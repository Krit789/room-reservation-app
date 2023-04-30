package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.portal.admin.models.PreferenceWindowModel;
import net.itkmitl.room.portal.admin.views.PreferenceWindowView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PreferenceWindowController implements ChangeListener, ActionListener {
    private final PreferenceWindowView view;
    private final PreferenceWindowModel model;

    public PreferenceWindowView getView() {
        return this.view;
    }

    public PreferenceWindowController() {
        view = new PreferenceWindowView();
        model = new PreferenceWindowModel();

        view.fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".yml") ;
                }
            }

            @Override
            public String getDescription() {
                return "YAML Ain't Markup Language format (*.yml)";
            }
        });
        view.configPickerButton.setEnabled(false);
        view.settingTab.addChangeListener(this);
        view.okButton.addActionListener(this);
        view.cancelButton.addActionListener(this);
        view.applyButton.addActionListener(this);
        view.timeoutCheckBox.addActionListener(this);
        view.configPickerButton.addActionListener(this);
    }

    private void pushToModel() {
        model.setUsername(view.dbUserTextField.getText());
        model.setPassword(String.valueOf(view.dbPasswordField.getPassword()));
        model.setSqlDBName(view.dbNameTextField.getText());
        model.setSqlAddress(view.dbAddressTextField.getText());
        model.setSqlPort((Integer) view.dbPortSpinner.getValue());
        model.setTimeout((Integer) view.timeoutSpinner.getValue());
        model.setNeverTimeout(view.timeoutCheckBox.isSelected());
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.cancelButton)) {
            try {
                view.getFrame().setClosed(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource().equals(view.timeoutCheckBox)) {
            view.model.setNeverTimeout(view.timeoutCheckBox.isSelected());
            view.timeoutSpinner.setEnabled(!view.timeoutCheckBox.isSelected());
        } else if (e.getSource().equals(view.configPickerButton)) {
            int chose = view.fileChooser.showOpenDialog(null);
            if (chose == JFileChooser.APPROVE_OPTION) {
                PreferenceWindowModel.setConfigFile(new File(view.fileChooser.getSelectedFile().getAbsolutePath()));
                view.configDirectory.setText(view.fileChooser.getSelectedFile().getAbsolutePath());
            }
        } else if (e.getSource().equals(view.applyButton)) {
            pushToModel();
            model.writeToConfig();
        } else if (e.getSource().equals(view.okButton)) {
            pushToModel();
            model.writeToConfig();
            try {
                view.getFrame().setClosed(true);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        if (e.getSource().equals(view.settingTab)) {
            switch (view.settingTab.getSelectedIndex()) {
                case 0:
                    view.pageTitle.setText("Database");
                    break;
                case 1:
                    view.pageTitle.setText("Client");
                    break;
            }
        }
    }
}
