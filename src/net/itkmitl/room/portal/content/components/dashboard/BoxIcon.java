package net.itkmitl.room.portal.content.components.dashboard;

import net.itkmitl.room.portal.components.TransparentPanel;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class BoxIcon extends TransparentPanel {
    @Serial
    private static final long serialVersionUID = -3043535217335143086L;
    private final ImageIcon image;

    public BoxIcon(ImageIcon image) {
        this.image = image;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(160, 160);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int original_width = image.getImage().getWidth(this);
        int original_height = image.getImage().getHeight(this);
        int bound_width = getWidth();
        int bound_height = getHeight();
        int new_width = original_width;
        int new_height = original_height;

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image.getImage(), (bound_width - new_width) / 2, 0, new_width, new_height, this);
    }

}
