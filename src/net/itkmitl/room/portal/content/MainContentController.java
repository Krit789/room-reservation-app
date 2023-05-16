package net.itkmitl.room.portal.content;

import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.store.AppStore;
import net.itkmitl.room.portal.Controller;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.components.AboutDialog;
import net.itkmitl.room.portal.components.LeftSelectorBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.itkmitl.room.portal.components.LeftSelectorPanel.finishedLoading;
import static net.itkmitl.room.portal.content.components.dashboard.ReservationPanel.buttonBox;


public class MainContentController extends Controller implements ActionListener {
    public static MainContentView view = null;
    private final AppStore store = AppStore.getAppStore();
    public static boolean running = false;

    public MainContentController(MainContentView view) {
        MainContentController.view = view;
    }

    public MainContentView getView() {
        return view;
    }

    @Override
    protected void start() {
        this.initialize();
    }

    @Override
    protected void initialize() {
        this.initializeListener();
    }

    @Override
    protected void initializeListener() {
        if (((User) store.select("user")).getRole().getLevel() >= 10) {
            this.getView().optionMenuItem1.addActionListener(this);
        }
        running = true;
        this.getView().helpMenuItem1.addActionListener(this);
        buttonBox[0].addActionListener(this);
        buttonBox[1].addActionListener(this);
        this.getView().getSelector().getSelectorPanel().backButton.addActionListener(this);
        this.getView().getDashboard().getWelcomePanel().bookingButton.addActionListener(this);

        // History
        getView().getHistory().getLeftPanel().getBackButton().addActionListener(this);
        getView().getHistory().getLeftPanel().getSuccessBtn().addActionListener(this);
        getView().getHistory().getLeftPanel().getPendingBtn().addActionListener(this);
        getView().getHistory().getLeftPanel().getCanceledBtn().addActionListener(this);

        SwingWorker<?, ?> worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                while (!finishedLoading) {
                    Thread.onSpinWait();
                }
                return null;
            }

            @Override
            protected void done() {
//                JOptionPane.showMessageDialog(null, "Finished Loading", "Notice", JOptionPane.INFORMATION_MESSAGE);
                for (int i = getView().getSelector().getSelectorPanel().leftBoxHolder.size() - 1; i >= 0; i--) {
                    getView().getSelector().getSelectorPanel().leftBoxHolder.get(i).addActionListener(getAction());
                }
                MainContentView.glassPane.setVisible(false);
                MainContentView.glassPane.setEnabled(false);
            }
        };
        worker.execute();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.getView().optionMenuItem1) && ((User) store.select("user")).getRole().getLevel() >= 10) {
            this.getView().setVisible(false);
            BaseWindow.main(new String[]{""});
        } else if (e.getSource().equals(this.getView().helpMenuItem1)) {
            new AboutDialog(this.getView());
        }
        if (e.getActionCommand().equals("History")) {
            this.changeCard("History");
        } else if (e.getActionCommand().equals("Booking")) {
            this.changeCard("Selector");
        } else if (e.getActionCommand().equals("Back to Dashboard")) {
            this.changeCard("Dashboard");
        } else if (e.getSource() instanceof LeftSelectorBox) {
            this.changeBuildingCard(e.getActionCommand());
        }
    }

    protected void changeCard(String name) {
        CardLayout cl = (CardLayout) (this.getView().getContentPanel().getLayout());
        cl.show(this.getView().getContentPanel(), name);
    }

    protected void changeBuildingCard(String name) {
        CardLayout cl = (CardLayout) (this.getView().getSelector().cardsOfBuildingsFloorPanel.getLayout());
        cl.show(this.getView().getSelector().cardsOfBuildingsFloorPanel, name);
    }

    protected ActionListener getAction() {
        return this;
    }
}
