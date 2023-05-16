package net.itkmitl.room.portal.content.components.dashboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.Serial;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.util.FewFile;
import net.itkmitl.room.libs.phatsanphon.entity.Reservation;
import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.phatsanphon.repository.ReservationRepository;
import net.itkmitl.room.libs.store.AppStore;
import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.RoundedPanel;
import net.itkmitl.room.portal.components.TransparentPanel;

public class NotificationPanel extends RoundedPanel {
    @Serial
    private static final long serialVersionUID = 4305337873118944635L;
    public JLabel notificationTextLabel, calendarIcon;
    private final JPanel panelPanel;

    public NotificationPanel() {
        super();
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(255, 255, 255, 100));

        panelPanel = new TransparentPanel();
        panelPanel.setLayout(new FlowLayout());
        calendarIcon = new JLabel(FewFile.getImage("icons/calendar-32px.png"));
        notificationTextLabel = new JLabel("You don't have any upcoming reservation");
        panelPanel.add(calendarIcon);
        panelPanel.add(Box.createHorizontalStrut(10));
        panelPanel.add(notificationTextLabel);
        panelPanel.setBackground(Color.white);
        this.setMaximumSize(new Dimension(1300, 50));

        notificationTextLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        this.add(panelPanel, new GBCBuilder(GridBagConstraints.NONE, 1, 1, 0, 0, new Insets(0, 10, 0, 0)).setAnchor(GridBagConstraints.WEST));
        updateLabel();
    }

    private void updateLabel() {
        SwingWorker<?, ?> worker = new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() {
                try {
                    FewQuery db = LaewTaeDB.getDB();
                    ReservationRepository rsvprp = new ReservationRepository(db);
                    Reservation resList = rsvprp.getUpcomingReservationsByUserId(((User) AppStore.getAppStore().select("user")).getId());
                    if (resList != null) {
                        notificationTextLabel.setText(String.format("<html><p>You have upcoming reservation for<br> %s, %s at %d:%02d on %s %d, %s %s</p></html>", resList.getRoom().getName(), resList.getRoom().getBuilding(), resList.getStartTime().getHours(), resList.getStartTime().getMinutes(), resList.getStartTime().toLocalDate().getDayOfWeek(), resList.getStartTime().getDate(), resList.getStartTime().toLocalDate().getMonth(), resList.getStartTime().getYear()));
                    } else {
                        notificationTextLabel.setText("You don't have any upcoming reservation");
                    }
                } catch (Exception ex) {
                    notificationTextLabel.setText("ERROR: " + ex.getMessage());
                }
                return null;
            }
        };
        worker.execute();
    }
}
