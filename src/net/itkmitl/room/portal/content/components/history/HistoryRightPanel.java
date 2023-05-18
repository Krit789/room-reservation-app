package net.itkmitl.room.portal.content.components.history;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.entity.Reservation;
import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.phatsanphon.repository.ReservationRepository;
import net.itkmitl.room.libs.store.AppStore;
import net.itkmitl.room.portal.CardView;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.TransparentPanel;
import net.itkmitl.room.portal.content.MainContentController;
import net.itkmitl.room.portal.content.MainContentPortal;
import net.itkmitl.room.portal.content.MainContentView;
import net.itkmitl.room.portal.content.components.History;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.ArrayList;

public class HistoryRightPanel extends CardView {
    @Serial
    private static final long serialVersionUID = -4015833019658837000L;
    public JScrollPane historyHolder;
    public TransparentPanel reservationHistoryPanel;
    private JLabel text;
    private ReservationHistoryBox boxes;
    public int rsvNum, cancelledRsvNum;
    public HistoryLeftPanel leftPanel;

    public HistoryRightPanel(HistoryLeftPanel leftPanel) {
        this.leftPanel = leftPanel;
        setLayout(new BorderLayout());
        reservationHistoryPanel = new TransparentPanel();
        reservationHistoryPanel.setLayout(new GridBagLayout());

        historyHolder = new JScrollPane(reservationHistoryPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        historyHolder.getVerticalScrollBar().setUnitIncrement(16);
        historyHolder.setAlignmentY(JScrollPane.TOP_ALIGNMENT);
        historyHolder.setOpaque(false);
        historyHolder.getViewport().setOpaque(false);
        historyHolder.setBorder(null);
        add(historyHolder);
//        reservationHistoryPanel.add(new ReservationHistoryBox("TestRoom1", "12:30-16:30", "21 Jan 2023", true, false, null), new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 0, new Insets(15,0,0,0)).getGBC());
    }

    public void populate(){
        reservationHistoryPanel.removeAll();
        getReservationList();
    }
    private void getReservationList() {
        SwingWorker<?, ?> worker = new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground(){
                try {
                    MainContentView.glassPane.setText("Loading History");
                    MainContentView.glassPane.setSpinnerVisibility(true);
                    MainContentView.glassPane.setVisible(true);
                    MainContentView.glassPane.setEnabled(true);
                    FewQuery db = LaewTaeDB.getDB();
                    ReservationRepository reservationRepository = new ReservationRepository(db);
                    ArrayList<Reservation> res = reservationRepository.getReservationsByUserId(((User) AppStore.getAppStore().select("user")).getId());
                    ArrayList<Reservation> cRes = new ArrayList<>();
                    int count = 1;
                    for (Reservation r : res) {
                        reservationHistoryPanel.add(new ReservationHistoryBox(
                                r.getRoom().getName(),
                                String.format("%d:%02d-%d:%02d", r.getStartTime().getHours(), r.getStartTime().getMinutes(), r.getEndTime().getHours(), r.getEndTime().getMinutes()),
                                String.format("%d %s %d", r.getStartTime().getDate(), r.getStartTime().toLocalDate().getMonth().toString(), r.getStartTime().getYear()),
                                System.currentTimeMillis() >= r.getEndTime().getTime(),
                                r.isCancelled(),
                                r
                        ),
                                new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, count, new Insets(15,0,0,0)).getGBC());
                        count++;
                        if (r.isCancelled()){
                            cRes.add(r);
                        }
                        rsvNum = res.size();
                        cancelledRsvNum = cRes.size();
                        leftPanel.setNum(rsvNum, cancelledRsvNum);
                        reservationHistoryPanel.revalidate();
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(BaseWindow.baseFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                return null;

            }
            @Override
            protected void done(){
                MainContentView.glassPane.setVisible(false);
                MainContentView.glassPane.setEnabled(false);
            }
        };
        worker.execute();
    }

}
