package net.itkmitl.room.portal.dashboard;

import net.itkmitl.room.portal.Controller;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.components.AboutDialog;
import net.itkmitl.room.portal.selectBuilding.SelectBuilding;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.itkmitl.room.portal.account.EntryController.currentUser;
import static net.itkmitl.room.portal.dashboard.components.ReservationPanel.buttonBox;

public class DashboardController extends Controller implements ActionListener {
    private final DashboardView view;

    public DashboardController(DashboardView view) {
        this.view = view;
    }

    public DashboardView getView() {
        return view;
    }

    @Override
    public void start() {
        this.initialize();
    }

    @Override
    public void initialize() {
        this.initializeListener();
    }

    @Override
    public void initializeListener() {
        this.getView().optionMenuItem1.addActionListener(this);
        this.getView().helpMenuItem1.addActionListener(this);
        buttonBox[0].addActionListener(this);
        buttonBox[1].addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.getView().optionMenuItem1) && currentUser.getRole().getLevel() >= 10) {
            this.getView().dispose();
            String[] arguments = new String[]{""};
            BaseWindow.main(arguments);
        } else if (e.getSource().equals(this.getView().helpMenuItem1)){
            new AboutDialog(this.getView());
        } else if (e.getActionCommand().equals("History")) {
            System.out.println("go to history page");
        } else if (e.getActionCommand().equals("Booking")) {
            changeFrame(getView(), new SelectBuilding());
        }
    }
}
