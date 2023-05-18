package net.itkmitl.room.portal.content.components.dashboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

import javax.swing.*;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.libs.peeranat.FewDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.util.FewFile;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.libs.phatsanphon.repository.ReservationRepository;
import net.itkmitl.room.libs.phatsanphon.repository.RoomRepository;
import net.itkmitl.room.portal.components.ButtonGradient;
import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.RoundedPanel;
import net.itkmitl.room.portal.content.MainContentView;
import net.itkmitl.room.portal.content.components.ReservationDialogController;

public class WelcomePanel extends RoundedPanel implements ActionListener {
    @Serial
    private static final long serialVersionUID = -4015833019658837000L;
    public ButtonGradient bookingButton;
    private final JLabel t1;
    private final JLabel t2;
    private final JLabel t3;
    private JLabel rightLabel;
    private JPanel p1, p2, p3;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private final JPanel txt;
    private JPanel iconPanel;
    private final BoxIcon welcomeIcon;

    public WelcomePanel() {
        super();

//        this.setLayout(new GridLayout(1, 2));
//        leftPanel = new JPanel();
//        rightPanel = new JPanel();
        txt = new RoundedPanel(100, 100);

//        txt.setBackground(new Color(0,0,0,0));
        t1 = new JLabel("Welcome to Laew Tae Hong");
        t2 = new JLabel("Simple and Convenient");
        t3 = new JLabel("Get started");

        bookingButton = new ButtonGradient();

        t1.setFont(new Font("Cousine", Font.BOLD, 13));
        t1.setForeground(Color.BLACK);
        t1.setBounds(79, 99, 505, 90);
        t1.setHorizontalAlignment(JLabel.LEFT);
        t1.setVerticalAlignment(JLabel.CENTER);

        t2.setFont(new Font("Cousine", Font.BOLD, 22));
        t2.setForeground(Color.BLACK);
        t2.setBounds(0, 0, 505, 90);
        t2.setHorizontalAlignment(JLabel.LEFT);
        t2.setVerticalAlignment(JLabel.CENTER);
//        p2.add(t2);

        t3.setFont(new Font("Inter", Font.BOLD, 13));
        t3.setForeground(Color.decode("#336B9E"));
        t3.setBounds(79, 99, 505, 90);
        t3.setHorizontalAlignment(JLabel.LEFT);
        t3.setVerticalAlignment(JLabel.CENTER);

        txt.setBackground(Color.WHITE);
        txt.add(t1);
        txt.add(t2);
        txt.add(t3);
        txt.add(bookingButton);
//        txt.setBorder(BorderFactory.createEmptyBorder(, 60, 60, ));
        txt.setLayout(new GridLayout(4, 1));

        welcomeIcon = new BoxIcon(FewFile.getImage("content/dashboard/welcomeImage.png"));
//        welcomeIcon.setSize(600, 600);
        welcomeIcon.setPreferredSize(new Dimension(500, 500));

        bookingButton.setSizeSpeed(40f);
        bookingButton.setColor1(new Color(39, 77, 191, 153));
        bookingButton.setColor2(new Color(45, 125, 216));
        bookingButton.setText("Book Randomly Now!");
        bookingButton.setFont(new Font("Cousine", Font.BOLD, 18));
        bookingButton.addActionListener(this);
//        bookingButton.setPreferredSize(new Dimension(50, 50));

        this.setLayout(new GridBagLayout());
        this.add(txt, new GBCBuilder(GridBagConstraints.NONE, 0, 1, 0, 0, new Insets(0, 0, 0, 20)).setAnchor(GridBagConstraints.WEST));
        this.add(welcomeIcon, new GBCBuilder(GridBagConstraints.NONE, 0, 1, 1, 0).setAnchor(GridBagConstraints.EAST));
//        this.add(t1);
//        this.add(t2);
//        this.add(t3);

        this.setBackground(new Color(255, 255, 255));
//        this.setMaximumSize(new Dimension(1300, 400));
        this.setAlignmentX(CENTER_ALIGNMENT);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(bookingButton)) {
            bookRandomly();
        }
    }

    private void bookRandomly() {
        SwingWorker<?, ?> worker = new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                try {
                    MainContentView.glassPane.setSpinnerVisibility(true);
                    MainContentView.glassPane.setText("Randomly selecting room for you");
                    MainContentView.glassPane.setVisible(true);
                    MainContentView.glassPane.setEnabled(true);
                    FewQuery db = LaewTaeDB.getDB();
                    RoomRepository roomRepo = new RoomRepository(db);
                    Room randomRoom = roomRepo.getRandomRoom();
                    MainContentView.glassPane.setSpinnerVisibility(false);
                    MainContentView.glassPane.setText("");
                    ReservationDialogController rsvpd = new ReservationDialogController(null, randomRoom);
                    rsvpd.view.setVisible(true);
                } catch (Exception e) {

                }
                return null;
            }
        };
        worker.execute();
    }
}
