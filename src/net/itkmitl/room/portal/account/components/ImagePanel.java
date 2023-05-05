package net.itkmitl.room.portal.account.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8862608090477009970L;
	private ImageIcon image;

    public ImagePanel(ImageIcon image) {
        this.image = image;
        this.setBackground(Color.white);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(474, 1000);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int original_width = image.getImage().getWidth(this);
        int original_height = image.getImage().getHeight(this);
//        int bound_width = getWidth();
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
        graphics2D.drawImage(image.getImage(),0,0,new_width, new_height,this);
    }

}
