package net.itkmitl.room.portal.dashboard;

import net.itkmitl.room.portal.Controller;
import net.itkmitl.room.portal.admin.BaseWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardController extends Controller implements ActionListener {
    private final DashboardView view;

    public DashboardController(DashboardView view) {
        this.view = view;
    }

    public DashboardView getView() {
        return view;
    }

    public void start() {
        this.initialize();
    }

    public void initialize() {
        this.initializeListener();
    }

    @Override
    public void initializeListener() {
        this.getView().optionMenuItem1.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.getView().optionMenuItem1)) {
            this.getView().dispose();
            String[] arguments = new String[]{""};
            BaseWindow.main(arguments);
        }
    }
}
