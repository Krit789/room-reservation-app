package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.portal.admin.components.DatabaseLoader;
import net.itkmitl.room.portal.admin.models.DataSearchModel;
import net.itkmitl.room.portal.admin.views.DataSearchView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class DataSearchController implements ActionListener {
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
            if (!view.searchField.getText().equals("")) {
                if (view.pageTitle.getText().equals("User")) {
                    dbl.databaseLoader(1, getSelectedButtonIndex(view.searchType), view.searchField.getText());
                    System.out.println(getSelectedButtonIndex(view.searchType));
                } else if (view.pageTitle.getText().equals("Room")) {
                    dbl.databaseLoader(2, getSelectedButtonIndex(view.searchType), view.searchField.getText());
                } else if (view.pageTitle.getText().equals("Reservation")) {
                    dbl.databaseLoader(3, getSelectedButtonIndex(view.searchType), view.searchField.getText());
                } else if (view.pageTitle.getText().equals("Feedback")) {
                    dbl.databaseLoader(4, getSelectedButtonIndex(view.searchType), view.searchField.getText());
                }
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
}
