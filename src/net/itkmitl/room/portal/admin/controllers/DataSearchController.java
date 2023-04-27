package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.portal.admin.components.DatabaseLoader;
import net.itkmitl.room.portal.admin.models.DataSearchModel;
import net.itkmitl.room.portal.admin.views.DataSearchView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class DataSearchController implements ActionListener, DocumentListener {
    protected DataSearchView view;
    private DataSearchModel model;
    private DatabaseLoader dbl;

    public DataSearchController() {
        this(new DataSearchModel());
    }

    public DataSearchController(DataSearchModel dsm) {
        model = dsm;
        view = new DataSearchView();
        dbl = new DatabaseLoader();
        initializer();
    }

    public void initializer() {
        view.searchButton.addActionListener(this);
        view.searchField.getDocument().addDocumentListener(this);
        view.pageTitle.setText(model.getTitle());
        int count = 0;
        for (Enumeration<AbstractButton> buttons = view.searchType.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (count < model.getRadioButtonLabel().size()) {
                button.setText(model.getRadioButtonLabel().get(count));
            } else {
                button.setVisible(false);
            }
            count++;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.searchButton)) {
            if (!view.searchField.getText().equals("") && numOnlyTextField()) {
                if (view.pageTitle.getText().equals("User")) {
                    dbl.databaseLoader(1, getSelectedButtonIndex(view.searchType), view.searchField.getText(), true);
                } else if (view.pageTitle.getText().equals("Room")) {
                    dbl.databaseLoader(2, getSelectedButtonIndex(view.searchType), view.searchField.getText(), true);
                } else if (view.pageTitle.getText().equals("Reservation")) {
                    dbl.databaseLoader(3, getSelectedButtonIndex(view.searchType), view.searchField.getText(), true);
                } else if (view.pageTitle.getText().equals("Feedback")) {
                    dbl.databaseLoader(4, getSelectedButtonIndex(view.searchType), view.searchField.getText(), true);
                }
                view.searchField.setText("");
            }
        }
    }


    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    public int getSelectedButtonIndex(ButtonGroup buttonGroup) {
        int count = 0;
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return count;
            }
            count++;
        }
        return -1;
    }

    public void insertUpdate(DocumentEvent e) {
        numOnlyTextField();
    }

    public void removeUpdate(DocumentEvent e) {
        numOnlyTextField();
    }

    public void changedUpdate(DocumentEvent e) {
        numOnlyTextField();
    }

    public boolean numOnlyTextField() {
        if (getSelectedButtonText(view.searchType).contains("ID") || getSelectedButtonText(view.searchType).equals("Ratings") || getSelectedButtonText(view.searchType).equals("Phone Number")) {
            try {
                Integer.parseInt(view.searchField.getText());
            } catch (Exception ex) {
                if (!view.searchField.getText().equals("")){
                    view.errorLabel.setText(String.format("'%s' can only contains numbers!", getSelectedButtonText(view.searchType)));

                }
                return false;
            }
        } else {
            view.errorLabel.setText("");
        }
        return true;
    }
}

