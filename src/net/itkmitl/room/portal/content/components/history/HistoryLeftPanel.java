package net.itkmitl.room.portal.content.components.history;

import net.itkmitl.room.portal.components.ButtonGradient;
import net.itkmitl.room.portal.components.TransparentPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HistoryLeftPanel extends TransparentPanel {
    private JPanel btnPanel, southPanel;
    private HistoryLeftButton successBtn, pendingBtn, canceledBtn;
    private ButtonGradient backButton;

    public HistoryLeftPanel() {
        setLayout(new BorderLayout());
        btnPanel = new TransparentPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel = new JPanel(new FlowLayout());

        successBtn = new HistoryLeftButton("src/net/itkmitl/room/portal/content/components/history/asset/checkmark.png", "Success");
        pendingBtn = new HistoryLeftButton("src/net/itkmitl/room/portal/content/components/history/asset/loading.png", "Pending");
        canceledBtn = new HistoryLeftButton("src/net/itkmitl/room/portal/content/components/history/asset/cancelled.png", "Cancelled");

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

    public HistoryLeftButton getSuccessBtn() {
        return successBtn;
    }

    public HistoryLeftButton getPendingBtn() {
        return pendingBtn;
    }

    public HistoryLeftButton getCanceledBtn() {
        return canceledBtn;
    }

    public ButtonGradient getBackButton() {
        return backButton;
    }
}
