package net.itkmitl.room.portal.content.components.history;

import net.itkmitl.room.portal.components.RoundedPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class HistoryLeftButtonText extends RoundedPanel {
    private JLabel icon, text;
    private String imagePath;

    public HistoryLeftButtonText() {
        this("", "");
    }

    public HistoryLeftButtonText(String imagePath, String textIn) {
        super(10, 10);

        setLayout(new BorderLayout());
        this.imagePath = imagePath;
        icon = new JLabel(new ImageIcon(this.imagePath));
        text = new JLabel(textIn);
        text.setFont(new Font("Cousine", Font.PLAIN, 16));

        EmptyBorder border = new EmptyBorder(10, 10, 10, 10);
        icon.setBorder(border);
        text.setHorizontalTextPosition(JLabel.CENTER);
        text.setBorder(border);

        setIconSize(60);
//        setPreferredSize(new Dimension(200, 100));
        add(icon, BorderLayout.WEST);
        add(text);
    }

    public void setIconSize(int size) {
        setIconSize(size, size);
    }

    public void setIconSize(int width, int height) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();

        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        icon.setIcon(new ImageIcon(scaledImage));
    }


}
