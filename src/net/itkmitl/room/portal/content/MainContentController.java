package net.itkmitl.room.portal.content;

import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.store.AppStore;
import net.itkmitl.room.portal.Controller;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.components.AboutDialog;
import net.itkmitl.room.portal.components.LeftSelectorBox;
import net.itkmitl.room.portal.components.MainRoomSelectionBox;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static net.itkmitl.room.portal.components.LeftSelectorPanel.finishedLoading;
import static net.itkmitl.room.portal.content.components.dashboard.ReservationPanel.buttonBox;


public class MainContentController extends Controller implements ActionListener, MouseListener {
    public static MainContentView view = null;
    private final AppStore store = AppStore.getAppStore();
    public static boolean running = false;
    private LeftSelectorBox selectedBox;

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

        SwingWorker<?, ?> worker = new SwingWorker<>() {
            @Override
            protected Object doInBackground() throws Exception {
                while (!finishedLoading) {
                    synchronized(this) {
                        Thread.onSpinWait();
                    }
                }
                return null;
            }

            @Override
            protected void done() {
                for (int i = getView().getSelector().getSelectorPanel().leftBoxHolder.size() - 1; i >= 0; i--) {
                    getView().getSelector().getSelectorPanel().leftBoxHolder.get(i).addActionListener(getAction());
                    getView().getSelector().getSelectorPanel().leftBoxHolder.get(i).addMouseListener(getMouse());
                }
                selectedBox = getView().getSelector().getSelectorPanel().leftBoxHolder.get(0);
                selectedBox.setContentAreaFilled(true);
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
            getView().getHistory().getRightPanel().populate();
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

        for (LeftSelectorBox box : this.getView().getSelector().getSelectorPanel().leftBoxHolder) {
            System.out.println(box.getText() + " " + name);
            if (box.getText().equals(name) & !box.equals(selectedBox)) {
                box.setText("√ " + name);
                selectedBox = box;
                box.setContentAreaFilled(true);
                box.revalidate();
            } else {
                if (!name.equals(selectedBox.getText()) & box.getText().startsWith("√ ") & !box.getText().equals(name)) {
                    box.setText(box.getText().substring(2, box.getText().length()));
                    box.setContentAreaFilled(false);
                    box.revalidate();
                }
            }
        }
    }

    protected ActionListener getAction() {
        return this;
    }
    protected MouseListener getMouse() {
        return this;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        for (LeftSelectorBox box : this.getView().getSelector().getSelectorPanel().leftBoxHolder) {
//            System.out.println(box.getText() + " " + name);
            if (e.getSource().equals(box) & !e.getSource().equals(selectedBox)) {
                box.setContentAreaFilled(true);
                box.revalidate();
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for (LeftSelectorBox box : this.getView().getSelector().getSelectorPanel().leftBoxHolder) {
            if (e.getSource().equals(box) & !e.getSource().equals(selectedBox)) {
                box.setContentAreaFilled(false);
                box.revalidate();
            }
        }
    }
}
