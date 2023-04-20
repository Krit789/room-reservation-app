package net.itkmitl.room.portal.dashboard;

import net.itkmitl.room.portal.View;
import net.itkmitl.room.portal.dashboard.components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardView extends View {
    public JPanel rightPanel, mainPanel, contentPanel, welcomePanel, notificationPanel, reservationPanel;

    public DashboardView() {
        super();
        this.initialize();
    }

    @Override
    public void initialize() {
        mainPanel = new MainPanel();

        contentPanel = new ContentPanel();
        rightPanel = new RightPanel();

        welcomePanel = new WelcomePanel();
        notificationPanel = new NotificationPanel();
        reservationPanel = new ReservationPanel();

        contentPanel.add(welcomePanel);
        contentPanel.add(Box.createVerticalStrut(50));
        contentPanel.add(notificationPanel);
        contentPanel.add(Box.createVerticalStrut(50));
        contentPanel.add(reservationPanel);

        JPanel titleBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel title = new JLabel("LAEW TAE HONG");

        titleBox.setOpaque(false);
        titleBox.setBorder(new EmptyBorder(30, 0, 30, 0));

        title.setFont(new Font("Cousine", Font.BOLD, 29));
        title.setForeground(Color.WHITE);

        titleBox.add(title);

        mainPanel.add(titleBox, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
    }
}
