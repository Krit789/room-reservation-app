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

    public HistoryRightPanel() {
        setLayout(new BorderLayout());
        reservationHistoryPanel = new TransparentPanel();
        reservationHistoryPanel.setLayout(new GridBagLayout());
//        boxes = new ReservationHistoryBox("TestRoom1", "12:30-16:30", "21 Jan 2023", true);
//        reservationHistoryPanel = new TransparentPanel();
//        reservationHistoryPanel.setLayout(new GridBagLayout());
//        reservationHistoryPanel.add(boxes, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0.25, 0, 0, new Insets(10, 20, 0, 20)).getGBC());
//        reservationHistoryPanel.add(
//                new ReservationHistoryBox("TestRoom2", "12:30-16:30", "21 Dec 2023", false),
//                new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0.25, 0, 1, new Insets(10, 20, 0, 20)).getGBC()
//        );

        historyHolder = new JScrollPane(reservationHistoryPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        historyHolder.setAlignmentY(JScrollPane.TOP_ALIGNMENT);
        historyHolder.setOpaque(false);
        historyHolder.getViewport().setOpaque(false);
        historyHolder.setBorder(null);
        add(historyHolder);
        getReservationList();


        reservationHistoryPanel.add(new ReservationHistoryBox("TestRoom1", "12:30-16:30", "21 Jan 2023", true), new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 0, new Insets(15,0,0,0)).getGBC());
//        reservationHistoryPanel.add(new ReservationHistoryBox("TestRoom1", "12:30-16:30", "21 Jan 2023", false), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0, 0, 1).getGBC());
//        reservationHistoryPanel.add(new ReservationHistoryBox("TestRoom1", "12:30-16:30", "21 Jan 2023", true), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0, 0, 2).getGBC());
//        reservationHistoryPanel.add(new ReservationHistoryBox("TestRoom1", "12:30-16:30", "21 Jan 2023", true), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0, 0, 3).getGBC());
//        reservationHistoryPanel.add(new ReservationHistoryBox("TestRoom1", "12:30-16:30", "21 Jan 2023", true), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0, 0, 4).getGBC());
//        reservationHistoryPanel.add(new ReservationHistoryBox("TestRoom1", "12:30-16:30", "21 Jan 2023", true), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0, 0, 5).getGBC());
//        reservationHistoryPanel.add(new ReservationHistoryBox("TestRoom1", "12:30-16:30", "21 Jan 2023", true), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0, 0, 6).getGBC());
    }

    private void getReservationList() {
        SwingWorker<?, ?> worker = new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() throws Exception {
                try {
                    FewQuery db = LaewTaeDB.getDB();
                    ReservationRepository reservationRepository = new ReservationRepository(db);
                    ArrayList<Reservation> res = reservationRepository.getReservationsByUserId(((User) AppStore.getAppStore().select("user")).getId());
                    int count = 1;
                    for (Reservation r : res) {
                        reservationHistoryPanel.add(new ReservationHistoryBox(r.getRoom().getName(), String.format("%d:%02d-%d:%02d", r.getStartTime().getHours(), r.getStartTime().getMinutes(), r.getEndTime().getHours(), r.getEndTime().getMinutes()), String.format("%d %s %d", r.getStartTime().getDate(), r.getStartTime().toLocalDate().getMonth().toString(), r.getStartTime().getYear()), System.currentTimeMillis() >= r.getEndTime().getTime()), new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, count, new Insets(15,0,0,0)).getGBC());
                        count++;
                        reservationHistoryPanel.revalidate();
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(BaseWindow.baseFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }
        };
        worker.execute();
    }

}
