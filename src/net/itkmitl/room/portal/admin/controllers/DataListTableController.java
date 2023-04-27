package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.admin.models.DataListTableModel;
import net.itkmitl.room.portal.admin.models.DataSearchModel;
import net.itkmitl.room.portal.admin.views.DataListTableView;
import net.itkmitl.room.portal.admin.views.DataSearchView;
import net.itkmitl.room.portal.admin.views.UserDataView;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataListTableController implements ListSelectionListener, InternalFrameListener, ActionListener {
    public DataListTableView view;
    public DataListTableModel model;

    public DataListTableController(){
        this(new DataListTableModel());
    }
    public DataListTableController(DataListTableModel model){
        this.view = new DataListTableView();
        this.model = model;
        view.getFrame().setTitle(model.getTitle());
        view.pageTitle.setText(model.getPageTitle());
        view.pageSubtitle.setText(model.getPageSubtitle());
        view.table.setModel(model.getDtm());
        view.viewEntryButton.setEnabled(false);
        if (!(this instanceof DataListEditableController)){
            view.table.getSelectionModel().addListSelectionListener(this);

        }
        view.viewEntryButton.addActionListener(this);

    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && view.table.getSelectedRow() != -1){
            view.viewEntryButton.setEnabled(true);
//            System.out.println(view.table.getValueAt(view.table.getSelectedRow(), 0).toString());
        }
    }

    public void internalFrameOpened(InternalFrameEvent e) {
        BaseWindow.statusLabel.setText(e.getInternalFrame().getTitle() + " was opened.");
        JMenuItem newItem = new JMenuItem(e.getInternalFrame().getTitle());
        BaseWindow.windowList.put(e.getInternalFrame(), newItem);
        newItem.setIcon(e.getInternalFrame().getFrameIcon());
        BaseWindow.windowMenu.add(newItem);
    }

    public void internalFrameClosing(InternalFrameEvent e) {
    }

    public void internalFrameClosed(InternalFrameEvent e) {
        BaseWindow.windowMenu.remove(BaseWindow.windowList.get(e.getInternalFrame()));
        BaseWindow.windowList.remove(e.getInternalFrame());
    }

    public void internalFrameIconified(InternalFrameEvent e) {
    }

    public void internalFrameDeiconified(InternalFrameEvent e) {
    }

    public void internalFrameActivated(InternalFrameEvent e) {
    }

    public void internalFrameDeactivated(InternalFrameEvent e) {
    }

    public void spawnUserDataEditor(int mode, int userID){
        UserDataController udc = new UserDataController(mode, userID);
        UserDataView view = udc.view;

        Dimension desktopSize = BaseWindow.getDesktop().getSize();
        Dimension jInternalFrameSize = view.getFrame().getSize();
        view.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        view.getFrame().addInternalFrameListener(this);
        view.getFrame().show();
        view.getFrame().setVisible(true);
        BaseWindow.getDesktop().add(view.getFrame());
        view.getFrame().moveToFront();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.viewEntryButton)){
            spawnUserDataEditor(0, Integer.parseInt(view.table.getValueAt(view.table.getSelectedRow(), 0).toString()));
        }
    }
}
