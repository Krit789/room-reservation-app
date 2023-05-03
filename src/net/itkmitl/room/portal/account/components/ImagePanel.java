package net.itkmitl.room.portal.account.components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    private ImageIcon imageIcon;

    public ImagePanel(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double scaleFactor = Math.min(1d, getScaleFactorToFit(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()), getSize()));
        int scaleWidth = (int) Math.round(imageIcon.getIconWidth() * scaleFactor);
        int scaleHeight = (int) Math.round(imageIcon.getIconHeight() * scaleFactor);
        Image scaled = imageIcon.getImage().getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
        int x = (getWidth() - scaled.getWidth(null)) / 2;
        int y = (getHeight() - scaled.getHeight(null)) / 2;
        g.drawImage(scaled, x, y, null);
    }

    private double getScaleFactorToFit(Dimension original, Dimension toFit) {
        double scaleWidth = toFit.getWidth() / original.getWidth();
        double scaleHeight = toFit.getHeight() / original.getHeight();
        return Math.min(scaleWidth, scaleHeight);
    }
}
