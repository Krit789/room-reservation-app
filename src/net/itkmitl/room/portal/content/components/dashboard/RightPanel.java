package net.itkmitl.room.portal.content.components.dashboard;

import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.store.AppStore;
import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class RightPanel extends JPanel {
    @Serial
    private static final long serialVersionUID = 3515411566688472487L;
    public JLabel nameLabel;
    public BookingStatusPanel bookingPanel;
    private final AppStore store = AppStore.getAppStore();
    private final JPanel namePanel;

    public RightPanel() {
        super();

        User user = (User) store.select("user");

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        this.setBackground(new Color(255, 255, 255));
        this.setPreferredSize(
                new Dimension(534, (int) this.getBounds().getSize().getHeight())
        );
        namePanel = new JPanel();
        namePanel.setLayout(new GridBagLayout());

//        System.out.println(user.getFirstname());

        nameLabel = new JLabel("Hello " + user.getFirstname() + " " + user.getLastname());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

        namePanel.add(nameLabel, new GBCBuilder(GridBagConstraints.CENTER, 1, 1, 0, 0, new Insets(0, 0, 25, 0)).getGBC());
        namePanel.setBackground(Color.white);
        bookingPanel = new BookingStatusPanel();

        this.add(namePanel, BorderLayout.NORTH);
        this.add(bookingPanel, BorderLayout.CENTER);
    }
}
