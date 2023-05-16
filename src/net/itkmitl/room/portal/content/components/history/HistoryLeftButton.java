package net.itkmitl.room.portal.content.components.history;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class HistoryLeftButton extends JButton {
    @Serial
    private static final long serialVersionUID = -4015833019658837000L;
    private HistoryLeftButtonText textPanel;

    public HistoryLeftButton() {
        this("", "");
    }

    public HistoryLeftButton(String imagePath, String text) {

        textPanel = new HistoryLeftButtonText(imagePath, text) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setOpaque(false);
                g.setColor(new Color(0, 0, 0, 0));
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
