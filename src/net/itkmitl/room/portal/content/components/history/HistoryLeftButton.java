package net.itkmitl.room.portal.content.components.history;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;

public class HistoryLeftButton extends JButton {
    private HistoryLeftButtonText textPanel;

    public HistoryLeftButton() {
        this("", "");
    }

    public HistoryLeftButton(String imagePath, String text) {
        textPanel = new HistoryLeftButtonText(imagePath, text) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        textPanel.setBounds(10, 10, 100, 50);
        add(textPanel);
    }

    public JPanel getTextPanel() {
        return textPanel;
    }

}
