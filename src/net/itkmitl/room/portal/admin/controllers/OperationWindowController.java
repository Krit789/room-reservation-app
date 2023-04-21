package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.phatsanphon.repository.UserRepository;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.admin.models.DataListTableModel;
import net.itkmitl.room.portal.admin.views.DataListTableView;
import net.itkmitl.room.portal.admin.views.OperationWindowView;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OperationWindowController implements ActionListener, InternalFrameListener {
    OperationWindowView view;
    public static final int USER = 1;
    public static final int ROOM = 2;
    public static final int RESERVATION = 3;

    public static final int FEEDBACK = 4;

    public OperationWindowController() {
        view = new OperationWindowView();
        view.viewUser.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.viewUser)) {
            spawnTableView(USER);
        }
    }

    public OperationWindowView getView() {
        return view;
    }

    public DataListTableController spawnTableView(int whichTable) {
        boolean dbReady = false;
        DataListTableController table;
        FewQuery db = RVDB.getDB();
        DataListTableModel model = new DataListTableModel();

        if (whichTable == 1) {
            try {
                ArrayList<User> users_list = new UserRepository(db).getUsers();
                model.setTitle("User Data");
                model.setPageTitle("User DataTable");
                model.setPageSubtitle(users_list.size() + " records was retrieved from database.");
                DefaultTableModel dtm = new DefaultTableModel(null, new String[]{"ID", "First Name", "Last Name", "Active", "Phone Number", "E-Mail", "Role", "Created On", "Staff"});
                for (User u : users_list) {
                    dtm.addRow(new Object[]{u.getId(), u.getFirstname(), u.getLastname(), u.isActive(), u.getEmail(), u.getTelephoneNumber(), u.getRole(), u.getCreatedOn(), u.isStaff()});
                }
                model.setDtm(dtm);
                dbReady = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(BaseWindow.baseFrame, e.getMessage(), "Database Query Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (dbReady) {
            table = new DataListTableController(model);
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
        return null;
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
