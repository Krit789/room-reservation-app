package net.itkmitl.room.portal.dashboard.components;

import net.itkmitl.room.portal.components.GBCBuilder;

import java.awt.*;

import javax.swing.*;

import static net.itkmitl.room.portal.account.EntryController.currentUser;

public class RightPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 3515411566688472487L;
    public JLabel nameLabel;
    public BookingStatusPanel bookingPanel;
    private JPanel namePanel;

    public RightPanel() {
        super();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        this.setBackground(new Color(255, 255, 255));
        this.setPreferredSize(
                new Dimension(534, (int) this.getBounds().getSize().getHeight())
        );
        namePanel = new JPanel();
        namePanel.setLayout(new GridBagLayout());
        nameLabel = new JLabel("Hello " + currentUser.getFirstname() + " " + currentUser.getLastname());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

        namePanel.add(nameLabel, new GBCBuilder(GridBagConstraints.CENTER, 1, 1, 0, 0, new Insets(0, 0, 25, 0)).getGBC());
        namePanel.setBackground(Color.white);
        bookingPanel = new BookingStatusPanel();

        this.add(namePanel, BorderLayout.NORTH);
        this.add(bookingPanel, BorderLayout.CENTER);
    }
}
