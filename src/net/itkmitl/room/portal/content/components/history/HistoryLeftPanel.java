package net.itkmitl.room.portal.content.components.history;

import net.itkmitl.room.portal.components.ButtonGradient;
import net.itkmitl.room.portal.components.RoundedPanel;
import net.itkmitl.room.portal.components.TransparentPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HistoryLeftPanel extends RoundedPanel {
    private JPanel btnPanel, southPanel;
    private HistoryLeftButton successBtn, pendingBtn, canceledBtn;
    private ButtonGradient backButton;

    public HistoryLeftPanel() {
        super(40, 40);
        setLayout(new BorderLayout());
        btnPanel = new JPanel(new GridBagLayout());
        southPanel = new JPanel(new FlowLayout());

        successBtn = new HistoryLeftButton("resource/content/history/checkmark.png", "Success");
        pendingBtn = new HistoryLeftButton("resource/content/history/loading.png", "Pending");
        canceledBtn = new HistoryLeftButton("resource/content/history/cancelled.png", "Cancelled");

//        btnPanel.add(successBtn);
//        btnPanel.add(pendingBtn);
//        btnPanel.add(canceledBtn);
//        btnPanel.setBorder(new EmptyBorder(230,25,230,25));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0; // Set the column index
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.anchor = GridBagConstraints.NORTHWEST;

        btnPanel.setPreferredSize(new Dimension(350, getHeight()));
        btnPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        btnPanel.add(successBtn, constraints);
        btnPanel.add(pendingBtn, constraints);
        btnPanel.add(canceledBtn, constraints);

        southPanel();

        add(btnPanel);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void southPanel() {
        backButton = new ButtonGradient("Back");
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
