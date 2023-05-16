package net.itkmitl.room.portal.content.components.history;

import net.itkmitl.room.portal.components.TransparentPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.Serial;

class HistoryLeftButtonText extends TransparentPanel {
    @Serial
    private static final long serialVersionUID = -4015833019658837000L;
    private final JLabel icon;
    private final JLabel text;
    private final String imagePath;

    public HistoryLeftButtonText() {
        this("", "");
    }

    public HistoryLeftButtonText(String imagePath, String textIn) {
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
