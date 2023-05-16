package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.libs.jarukrit.ProgramError;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.admin.models.DataListTableModel;
import net.itkmitl.room.portal.admin.views.DataListEditableView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;

public class DataListEditableController extends DataListTableController {
    public DataListEditableView view;

    public DataListEditableController() {
        this(new DataListTableModel());
    }

    public DataListEditableController(DataListTableModel model) {
        super();
        this.view = new DataListEditableView();
        super.view = this.view;
        super.model = model;
        view.getFrame().setTitle(model.getTitle());
        view.pageTitle.setText(model.getPageTitle());
        view.pageSubtitle.setText(model.getPageSubtitle());
        view.table.setModel(model.getDtm());
        view.reloadButton.addActionListener(this);
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
        if (!e.getValueIsAdjusting() && view.table.getSelectedRow() != -1) {
            view.editSelectedButton.setEnabled(true);
            view.deletedSelectedButton.setEnabled(true);
            view.viewEntryButton.setEnabled(true);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.reloadButton)) {
            view.reloadButton.setEnabled(false);
            view.reloadButton.revalidate();
            reloadData();
        } else {
            try {
                if (model.getPageTitle().contains("User")) {
                    if (e.getSource().equals(view.viewEntryButton)) {
                        spawnUserDataEditor(0, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()), this);
                    } else if (e.getSource().equals(view.editSelectedButton)) {
                        spawnUserDataEditor(1, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()), this);
                    } else if (e.getSource().equals(view.deletedSelectedButton)) {
                        if (JOptionPane.showConfirmDialog(view.getFrame(), "Are you sure that you want to delete this record?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            spawnUserDataEditor(3, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()), this);
                        }
                    } else if (e.getSource().equals(view.createButton)) {
                        spawnUserDataEditor(2, -1, this);
                    }
                } else if (model.getPageTitle().contains("Room")) {
                    if (e.getSource().equals(view.viewEntryButton)) {
                        spawnRoomDataEditor(0, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()), null);
                    } else if (e.getSource().equals(view.editSelectedButton)) {
                        spawnRoomDataEditor(1, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()), this);
                    } else if (e.getSource().equals(view.deletedSelectedButton)) {
                        if (JOptionPane.showConfirmDialog(view.getFrame(), "Are you sure that you want to delete this record?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            spawnRoomDataEditor(3, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()), this);
                        }
                    } else if (e.getSource().equals(view.createButton)) {
                        spawnRoomDataEditor(2, -1, this);
                    }
                } else if (model.getPageTitle().contains("Reservation")) {
                    if (e.getSource().equals(view.viewEntryButton)) {
                        spawnReservationDataEditor(0, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()), null);
                    } else if (e.getSource().equals(view.editSelectedButton)) {
                        spawnReservationDataEditor(1, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()), this);
                    } else if (e.getSource().equals(view.deletedSelectedButton)) {
                        if (JOptionPane.showConfirmDialog(view.getFrame(), "Are you sure that you want to delete this record?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            spawnReservationDataEditor(3, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()), this);
                        }
                    } else if (e.getSource().equals(view.createButton)) {
                        spawnReservationDataEditor(2, -1, this);
                    }
                } else if (model.getPageTitle().contains("Feedback")) {
                    if (e.getSource().equals(view.viewEntryButton)) {
                        spawnFeedbackDataEditor(0, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()), null);
                    } else if (e.getSource().equals(view.editSelectedButton)) {
                        spawnFeedbackDataEditor(1, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()), this);
                    } else if (e.getSource().equals(view.deletedSelectedButton)) {
                        if (JOptionPane.showConfirmDialog(view.getFrame(), "Are you sure that you want to delete this record?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            spawnFeedbackDataEditor(3, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()), this);
                        }
                    } else if (e.getSource().equals(view.createButton)) {
                        spawnFeedbackDataEditor(2, -1, this);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(BaseWindow.baseFrame, ProgramError.getStackTrace(ex), "Database Query Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
