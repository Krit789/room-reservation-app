package net.itkmitl.room.portal.components;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private int arcWidth;
    private int arcHeight;
    private Color backgroundColor, color1, color2;
    {
        setOpaque(false);
    }


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
    }

    public RoundedPanel(int arcWidth, int arcHeight, Color color1, Color color2) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.backgroundColor = null;
        this.color1 = color1;
        this.color2 = color2;
    }




    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        Dimension arcs = new Dimension(arcWidth, arcHeight);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        if (backgroundColor != null) {
            graphics2D.setColor(backgroundColor);
        } else if (color1 != null && color2 != null) {
            GradientPaint gra = new GradientPaint(0, 0, color1, width, 0, color2);
            graphics2D.setPaint(gra);
        } else {
            graphics2D.setBackground(getBackground());
        }
        graphics2D.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
        super.paintComponent(graphics2D);
    }
}
