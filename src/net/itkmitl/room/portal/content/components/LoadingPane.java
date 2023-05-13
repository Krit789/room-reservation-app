package net.itkmitl.room.portal.content.components;

import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.TransparentPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoadingPane extends JPanel implements MouseListener {
    private static final Color BACKGROUND_COLOR = new Color(0, 0, 0, 192);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Font TEXT_FONT = new Font("SansSerif", Font.PLAIN, 32);
    private JPanel textPanel;

    public LoadingPane() {
        setOpaque(false);
        setLayout(new GridBagLayout());
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        textPanel = new TransparentPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new FlowLayout());

        JLabel spinner = new JLabel(new ImageIcon("resource/icons/loading-32px.gif"));
        JLabel label = new JLabel("Loading data...");
        label.setFont(TEXT_FONT);
        label.setForeground(TEXT_COLOR);
        textPanel.add(spinner);
        textPanel.add(Box.createHorizontalStrut(15));
        textPanel.add(label);
        add(textPanel, new GBCBuilder(GridBagConstraints.CENTER, 0, 0, 0).getGBC());
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.SrcOver.derive(0.5f));
        g2.setColor(BACKGROUND_COLOR);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
    }
    public void mousePressed(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        e.consume();
    }



}
