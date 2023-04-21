package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.admin.views.DataListTableView;
import net.itkmitl.room.portal.admin.views.OperationWindowView;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperationWindowController implements ActionListener, InternalFrameListener {
    OperationWindowView view;
    public OperationWindowController(){
        view = new OperationWindowView();
        view.viewUser.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(view.viewUser)){
            spawnTableView();
        }
    }
    public OperationWindowView getView(){
        return view;
    }

    public DataListTableController spawnTableView() {
        DataListTableController table = new DataListTableController();
        Dimension desktopSize = BaseWindow.getDesktop().getSize();
        Dimension jInternalFrameSize = table.view.getFrame().getSize();
        table.view.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        table.view.getFrame().addInternalFrameListener(this);
        table.view.getFrame().show();
        table.view.getFrame().setVisible(true);
        BaseWindow.getDesktop().add(table.view.getFrame());
        table.view.getFrame().moveToFront();
        return table;
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


}
