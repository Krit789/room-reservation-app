package net.itkmitl.room.portal.content.components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.itkmitl.room.libs.peeranat.util.FewFile;
import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.TransparentPanel;

public class DarkPane extends JPanel implements MouseListener {
    private static final Color BACKGROUND_COLOR = new Color(0, 0, 0, 192);
    private final JPanel textPanel;
    private final JLabel spinner;
    private final JLabel label;

    public DarkPane() {
        setOpaque(false);
        setLayout(new GridBagLayout());
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        textPanel = new TransparentPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new FlowLayout());

        spinner = new JLabel(FewFile.getImage("icons/loading-32px.gif"));
        label = new JLabel();
        label.setFont(new Font("SansSerif", Font.PLAIN, 32));
        label.setForeground(Color.WHITE);
        textPanel.add(spinner);
        textPanel.add(Box.createHorizontalStrut(25));
        textPanel.add(label);
        add(textPanel, new GBCBuilder(GridBagConstraints.CENTER, 0, 0, 0).getGBC());
        addMouseListener(this);
    }

    public void setText(String text) {
        label.setText(text);
    }

    public void setSpinnerVisibility(boolean visible) {
        spinner.setVisible(visible);
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
