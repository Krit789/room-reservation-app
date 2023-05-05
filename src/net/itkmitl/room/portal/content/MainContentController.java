package net.itkmitl.room.portal.content;

import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.store.AppStore;
import net.itkmitl.room.portal.Controller;
import net.itkmitl.room.portal.components.LeftSelectorBox;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.itkmitl.room.portal.components.LeftSelectorPanel.finishedLoading;
import static net.itkmitl.room.portal.dashboard.components.ReservationPanel.buttonBox;


public class MainContentController extends Controller implements ActionListener {
    private final MainContentView view;
    private AppStore store = AppStore.getAppStore();
    public MainContentController(MainContentView view){
        this.view = view;
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
        if (((User)store.select("user")).getRole().getLevel() >= 10){
            this.getView().optionMenuItem1.addActionListener(this);
        }
        this.getView().helpMenuItem1.addActionListener(this);
        buttonBox[0].addActionListener(this);
        buttonBox[1].addActionListener(this);
        this.getView().getSelector().getSelectorPanel().backButton.addActionListener(this);

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
                JOptionPane.showMessageDialog(null, "Finished Loading", "Notice", JOptionPane.INFORMATION_MESSAGE);
                for (int i = getView().getSelector().getSelectorPanel().leftBoxHolder.size() - 1; i >= 0; i--) {
                    getView().getSelector().getSelectorPanel().leftBoxHolder.get(i).addActionListener(getAction());
                }
            }
        };
        worker.execute();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("History")) {
            System.out.println("go to history page");
        } else if (e.getActionCommand().equals("Booking")) {
            this.changeCard("Selector");
        } else if (e.getActionCommand().equals("Back to Dashboard")){
            this.changeCard("Dashboard");
        } else if (e.getSource() instanceof LeftSelectorBox) {
            this.getView().getSelector().testLabel.setText("Building = " + e.getActionCommand());
        }
    }
    protected void changeCard(String name) {
        CardLayout cl = (CardLayout) (((JPanel)this.getView().getContentPanel()).getLayout());
        cl.show(this.getView().getContentPanel(), name);
    }
    protected ActionListener getAction(){
        return this;
    }
}
