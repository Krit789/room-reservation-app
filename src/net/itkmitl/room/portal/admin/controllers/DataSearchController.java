package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.portal.admin.views.DataSearchView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class DataSearchController implements ActionListener {
    DataSearchView view;
    public DataSearchController(){
        view = new DataSearchView();
        view.searchButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(view.searchButton)){
            getSelectedButtonText(view.searchType);
        }
    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
}
