package net.itkmitl.room.portal.content.components;

import net.itkmitl.room.portal.CardView;
import net.itkmitl.room.portal.components.MainPanel;
import net.itkmitl.room.portal.content.components.dashboard.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Dashboard extends CardView {
    private static final long serialVersionUID = 5559587513984125656L;
    public JPanel rightPanel, mainPanel, contentPanel, welcomePanel, notificationPanel, reservationPanel;

    public Dashboard() {
        super();
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
        contentPanel.setOpaque(false);

        JPanel titleBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lthIcon = new JLabel(new ImageIcon("resource/icons/icon-64px.png"));
        JLabel title = new JLabel("LAEW TAE HONG");

        titleBox.setOpaque(false);
        titleBox.setBorder(new EmptyBorder(30, 0, 30, 0));

        title.setFont(new Font("Cousine", Font.BOLD, 29));
        title.setForeground(Color.WHITE);

        titleBox.add(lthIcon);
        titleBox.add(Box.createHorizontalStrut(10));
        titleBox.add(title);

        mainPanel.add(titleBox, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
    }

    public MainPanel getMainPanel() {
        return (MainPanel) this.mainPanel;
    }

    public ReservationPanel getReservationPanel() {
        return (ReservationPanel) this.reservationPanel;
    }

    public WelcomePanel getWelcomePanel() {
        return (WelcomePanel) this.welcomePanel;
    }
}
