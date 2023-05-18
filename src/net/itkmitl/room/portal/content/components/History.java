package net.itkmitl.room.portal.content.components;

import net.itkmitl.room.portal.CardView;
import net.itkmitl.room.portal.content.components.history.HistoryLeftPanel;
import net.itkmitl.room.portal.content.components.history.HistoryRightPanel;

import java.awt.*;

public class History extends CardView {
    public HistoryLeftPanel leftPanel;
    private HistoryRightPanel rightPanel;

    public History() {
        leftPanel = new HistoryLeftPanel();
        rightPanel = new HistoryRightPanel(leftPanel);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel);
    }

    // Getter
    public HistoryLeftPanel getLeftPanel() {
        return leftPanel;
    }

    public HistoryRightPanel getRightPanel() {
        return rightPanel;
    }
}
