package net.itkmitl.room.portal.content.components.dashboard;

import net.itkmitl.room.libs.peeranat.util.FewFile;
import net.itkmitl.room.libs.phatsanphon.date.DateTime;
import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.store.AppStore;
import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.content.MainContentView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

public class RightPanel extends JPanel implements ActionListener {
    @Serial
    private static final long serialVersionUID = 3515411566688472487L;
    public static JLabel nameLabel;
    public BookingStatusPanel bookingPanel;
    private final AppStore store = AppStore.getAppStore();
    private final JPanel namePanel;
    private final JButton editProfile;

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
        editProfile = new JButton();
        editProfile.setIcon(FewFile.getImage("icons/pencil-16px.png"));
        editProfile.addActionListener(this);

        nameLabel = new JLabel();
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        setName((User) store.select("user"));
        namePanel.add(nameLabel, new GBCBuilder(GridBagConstraints.CENTER, 0.99, 1, 0, 0, new Insets(0, 0, 25, 0)).getGBC());
        namePanel.add(editProfile, new GBCBuilder(GridBagConstraints.NONE, 0.01, 1, 1, 0, new Insets(0, 5, 25, 0)).getGBC());
        namePanel.setBackground(Color.white);
        bookingPanel = new BookingStatusPanel();

        this.add(namePanel, BorderLayout.NORTH);
        this.add(bookingPanel, BorderLayout.CENTER);
    }

    public static void setName(User user){
        String word;
        DateTime dt = new DateTime(System.currentTimeMillis());
        if (dt.getHours() >= 0 && dt.getHours() < 6) {
            word = "Good Early Morning, ";
        } else if (dt.getHours() >= 6 && dt.getHours() < 12) {
            word = "Good Morning, ";
        } else if (dt.getHours() >= 12 && dt.getHours() < 18) {
            word = "Good Afternoon, ";
        } else if (dt.getHours() >= 18) {
            word = "Good Evening, ";
        } else {
            word = "Hello, ";
        }
        nameLabel.setText(word + user.getFirstname() + " " + user.getLastname());
        nameLabel.revalidate();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(editProfile)) {
            MainContentView.glassPane.setText("");
            MainContentView.glassPane.setSpinnerVisibility(false);
            MainContentView.glassPane.setVisible(true);
            MainContentView.glassPane.setEnabled(true);
            new ProfileEditor(((User) AppStore.getAppStore().select("user")));
        }
    }
}
