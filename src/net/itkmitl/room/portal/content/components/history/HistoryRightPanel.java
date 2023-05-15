package net.itkmitl.room.portal.content.components.history;

import net.itkmitl.room.portal.CardView;
import net.itkmitl.room.portal.components.TransparentPanel;

import javax.swing.*;

public class HistoryRightPanel extends CardView {
    private JLabel text;
    public JScrollPane historyHolder;
    public TransparentPanel reservationHistoryPanel;

    private ReservationHistoryBox boxes;
    public HistoryRightPanel(){
        text = new JLabel("Contents go here!");
        add(text);
        boxes = new ReservationHistoryBox("TestRoom", "12:30-16:30", "21 Jan 2023");
        reservationHistoryPanel = new TransparentPanel();
        reservationHistoryPanel.add(boxes);
        historyHolder = new JScrollPane(reservationHistoryPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        historyHolder.setAlignmentY(JScrollPane.TOP_ALIGNMENT);
        historyHolder.setOpaque(false);
        historyHolder.getViewport().setOpaque(false);
        historyHolder.setBorder(null);
        add(historyHolder);
    }

}
