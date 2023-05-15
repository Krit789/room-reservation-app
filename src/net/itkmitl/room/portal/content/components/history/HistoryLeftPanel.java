package net.itkmitl.room.portal.content.components.history;

import net.itkmitl.room.portal.components.ButtonGradient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HistoryLeftPanel extends JPanel {
    private JPanel btnPanel, southPanel;
    private HistoryLeftButton successBtn, pendingBtn, canceledBtn;
    private JButton backButton;

    public HistoryLeftPanel() {
        setLayout(new BorderLayout());
        btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel = new JPanel();

        successBtn = new HistoryLeftButton("src/net/itkmitl/room/portal/history/asset/checkMark.png", "Success");
        pendingBtn = new HistoryLeftButton("src/net/itkmitl/room/portal/history/asset/loading.png", "Pending");
        canceledBtn = new HistoryLeftButton("src/net/itkmitl/room/portal/history/asset/cancelled.png", "Cancelled");

        btnPanel.add(successBtn);
        btnPanel.add(pendingBtn);
        btnPanel.add(canceledBtn);

        southPanel();

        add(btnPanel);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void southPanel() {
        backButton = new ButtonGradient();
        backButton.setText("Back");
        backButton.setActionCommand("Back to Dashboard");

        southPanel.add(backButton);
        southPanel.setBorder(new EmptyBorder(10, 20, 10, 10));
    }
}
