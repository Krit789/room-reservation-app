package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.libs.phatsanphon.repository.RoomRepository;
import net.itkmitl.room.portal.admin.components.DatabaseLoader;
import net.itkmitl.room.portal.admin.models.DataListTableModel;
import net.itkmitl.room.portal.admin.views.DataListEditableView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;

public class DataListEditableController extends DataListTableController {
    public DataListEditableView view;
    public DataListTableModel model;
    public DataListEditableController(){
        this(new DataListTableModel());
    }
    public DataListEditableController(DataListTableModel model){
        this.view = new DataListEditableView();
        this.model = model;
        view.getFrame().setTitle(model.getTitle());
        view.pageTitle.setText(model.getPageTitle());
        view.pageSubtitle.setText(model.getPageSubtitle());
        view.table.setModel(model.getDtm());
        view.editSelectedButton.setEnabled(false);
        view.deletedSelectedButton.setEnabled(false);
        view.viewEntryButton.setEnabled(false);
        view.table.getSelectionModel().addListSelectionListener(this);
        view.viewEntryButton.addActionListener(this);
        view.editSelectedButton.addActionListener(this);
        view.deletedSelectedButton.addActionListener(this);
        view.createButton.addActionListener(this);
        view.getFrame().addInternalFrameListener(this);

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && view.table.getSelectedRow() != -1){
            view.editSelectedButton.setEnabled(true);
            view.deletedSelectedButton.setEnabled(true);
            view.viewEntryButton.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (model.getPageTitle().contains("User")){
            if (e.getSource().equals(view.viewEntryButton)){
                spawnUserDataEditor(0, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()), this);
            } else if (e.getSource().equals(view.editSelectedButton)){
                spawnUserDataEditor(1, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()), this);
            } else if (e.getSource().equals(view.deletedSelectedButton)){
                if (JOptionPane.showConfirmDialog(view.getFrame(), "Are you sure that you want to delete this record?", "Warning",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    spawnUserDataEditor(3, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()), this);
                    if (view.pageTitle.getText().contains("User")){
                    }
                }
            } else if (e.getSource().equals(view.createButton)) {
                spawnUserDataEditor(2, -1, this);
            }
        }
    }
}
