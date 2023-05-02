package net.itkmitl.room.portal.components;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private int arcWidth;
    private int arcHeight;
    private Color backgroundColor;


    public RoundedPanel() {
        this(20, 20, Color.WHITE);
    }

    public RoundedPanel(Color color) {
        this(20, 20, color);
    }

    public RoundedPanel(LayoutManager layout) {
        super(layout);
    }

    public RoundedPanel(int arcWidth, int arcHeight) {
        this(arcWidth, arcHeight, Color.WHITE);
    }

    public RoundedPanel(int arcWidth, int arcHeight, Color backgroundColor) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.backgroundColor = backgroundColor;
        setOpaque(false);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(arcWidth, arcHeight);
        int width = getWidth();
        int height = getHeight();
        g.setColor(backgroundColor);
        g.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
    }
}
