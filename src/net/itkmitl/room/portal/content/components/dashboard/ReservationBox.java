package net.itkmitl.room.portal.content.components.dashboard;

import static net.itkmitl.room.portal.content.components.dashboard.ReservationPanel.buttonBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.Serial;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import net.itkmitl.room.libs.peeranat.util.FewFile;
import net.itkmitl.room.portal.components.ButtonGradient;
import net.itkmitl.room.portal.components.RoundedPanel;

public class ReservationBox extends RoundedPanel {
    public static final int iconWidth = 140, iconHeight = 140;
    @Serial
    private static final long serialVersionUID = 3285944903070661887L;
    public ButtonGradient redirectButton;
    private BoxIcon icon;

    public ReservationBox(String title) {
        super(30, 30);
        this.setLayout(new BorderLayout());
        this.setMaximumSize(new Dimension(200, 200));
        this.setBorder(new EmptyBorder(30, 30, 30, 30));

        redirectButton = new ButtonGradient(title.toUpperCase(), new Color(44, 102, 188), new Color(94, 135, 197));
        redirectButton.setFont(new Font("Cousine", Font.BOLD, 18));
        redirectButton.setSizeSpeed(30f);
        redirectButton.setActionCommand(title);
        if (title.equals("History")) {
            ImageIcon icon = resizeIcon(FewFile.getImage("content/dashboard/history.png"), iconWidth, iconHeight);
            this.add(new BoxIcon(icon), BorderLayout.CENTER);
            buttonBox[0] = redirectButton;
        } else if (title.equals("Booking")) {
            ImageIcon icon = resizeIcon(FewFile.getImage("content/dashboard/booking.png"), iconWidth, iconHeight);
            this.add(new BoxIcon(icon), BorderLayout.CENTER);
            buttonBox[1] = redirectButton;
        } else {
            JLabel titleLabel = new JLabel(title, JLabel.CENTER);
            this.add(titleLabel, BorderLayout.NORTH);
        }
        this.add(redirectButton, BorderLayout.SOUTH);
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image resizedImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
