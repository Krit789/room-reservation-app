package net.itkmitl.room.portal.content.components.history;

import net.itkmitl.room.portal.components.ButtonGradient;
import net.itkmitl.room.portal.components.RoundedPanel;
import net.itkmitl.room.portal.components.TransparentPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.Serial;

public class HistoryLeftPanel extends RoundedPanel {
    @Serial
    private static final long serialVersionUID = -4015833019658837000L;
    private final JPanel btnPanel;
    private final JPanel southPanel;
    public JLabel totalRsvLabel, totalRsv, cancelledRsvLabel, cancelledRsv, title;
    //    private final HistoryLeftButton successBtn;
//    private final HistoryLeftButton pendingBtn;
//    private final HistoryLeftButton canceledBtn;
    private ButtonGradient backButton;

    public HistoryLeftPanel() {
        super(40, 40);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(30, 30, 30, 30));
        btnPanel = new TransparentPanel();
        btnPanel.setLayout(new GridBagLayout());
        southPanel = new TransparentPanel();
        southPanel.setLayout(new FlowLayout());

        title = new JLabel("Reservation Stats", SwingConstants.CENTER);
        title.setFont(new Font("Cousine", Font.BOLD, 29));

        totalRsvLabel = new JLabel("Total Reservation", SwingConstants.CENTER);
        totalRsvLabel.setFont(new Font("Cousine", Font.PLAIN, 20));
        totalRsv = new JLabel("-", SwingConstants.CENTER);
        totalRsv.setFont(new Font("Cousine", Font.BOLD, 48));
        cancelledRsvLabel = new JLabel("Total Cancelled", SwingConstants.CENTER);
        cancelledRsvLabel.setFont(new Font("Cousine", Font.PLAIN, 20));
        cancelledRsv = new JLabel("-", SwingConstants.CENTER);
        cancelledRsv.setFont(new Font("Cousine", Font.BOLD, 48));


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.anchor = GridBagConstraints.NORTHWEST;
//
        btnPanel.setPreferredSize(new Dimension(350, getHeight()));
        btnPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        btnPanel.add(totalRsvLabel, constraints);
        btnPanel.add(totalRsv, constraints);
        btnPanel.add(Box.createVerticalStrut(20), constraints);
        btnPanel.add(cancelledRsvLabel, constraints);
        btnPanel.add(cancelledRsv, constraints);
//        btnPanel.add(successBtn, constraints);
//        btnPanel.add(pendingBtn, constraints);
//        btnPanel.add(canceledBtn, constraints);

        southPanel();

        add(title, BorderLayout.NORTH);
        add(btnPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void southPanel() {
        backButton = new ButtonGradient("Back");
        backButton.setActionCommand("Back to Dashboard");

        southPanel.add(backButton);
        southPanel.setBorder(new EmptyBorder(10, 20, 10, 10));
    }

    public void setNum(int rsv, int cRsv) {
        totalRsv.setText(String.valueOf(rsv));
        cancelledRsv.setText(String.valueOf(cRsv));
        revalidate();
    }
//    public HistoryLeftButton getSuccessBtn() {
//        return successBtn;
//    }
//
//    public HistoryLeftButton getPendingBtn() {
//        return pendingBtn;
//    }
//
//    public HistoryLeftButton getCanceledBtn() {
//        return canceledBtn;
//    }

    public ButtonGradient getBackButton() {
        return backButton;
    }
}
