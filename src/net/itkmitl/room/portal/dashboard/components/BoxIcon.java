package net.itkmitl.room.portal.dashboard.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;

import net.itkmitl.room.portal.components.TransparentPanel;

public class BoxIcon extends TransparentPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3043535217335143086L;
	private ImageIcon image;

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

        // first check if we need to scale width
//        if (original_width > bound_width) {
//            //scale width to fit
//            new_width = bound_width;
//            //scale height to maintain aspect ratio
//            new_height = (new_width * original_height) / original_width;
//        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image.getImage(),(bound_width - new_width) / 2,0,new_width, new_height,this);
    }

}
