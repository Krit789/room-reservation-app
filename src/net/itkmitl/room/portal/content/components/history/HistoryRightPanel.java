package net.itkmitl.room.portal.content.components.history;

import net.itkmitl.room.portal.CardView;
import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.TransparentPanel;

import javax.swing.*;
import java.awt.*;

public class HistoryRightPanel extends CardView {
    private JLabel text;
    public JScrollPane historyHolder;
    public TransparentPanel reservationHistoryPanel;

    private ReservationHistoryBox boxes;
    public HistoryRightPanel(){
        text = new JLabel("Contents go here!");
        add(text);
        boxes = new ReservationHistoryBox("TestRoom1", "12:30-16:30", "21 Jan 2023", true);
        reservationHistoryPanel = new TransparentPanel();
        reservationHistoryPanel.setLayout(new GridBagLayout());
        reservationHistoryPanel.add(boxes, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0.25, 0, 0, new Insets(10, 20, 0, 20)).getGBC());
        reservationHistoryPanel.add(
                new ReservationHistoryBox("TestRoom2", "12:30-16:30", "21 Dec 2023", false),
                new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0.25, 0, 1, new Insets(10, 20, 0, 20)).getGBC()
        );
        historyHolder = new JScrollPane(reservationHistoryPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        historyHolder.setAlignmentY(JScrollPane.TOP_ALIGNMENT);
        historyHolder.setOpaque(false);
        historyHolder.getViewport().setOpaque(false);
        historyHolder.setBorder(null);
        add(historyHolder);
    }

}
