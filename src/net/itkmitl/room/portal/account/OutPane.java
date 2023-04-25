package net.itkmitl.room.portal.account;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import net.itkmitl.room.libs.peeranat.util.FewFile;

public class OutPane extends JPanel {

    private JPanel paneN, paneW, paneS, paneE;

    public OutPane() {
        setLayout(new BorderLayout());

//        paneN = new JPanel();
//        paneW = new JPanel();
//        paneS = new JPanel();
//        paneE = new JPanel();
//
//        add(paneN, BorderLayout.NORTH);
//        add(paneW, BorderLayout.WEST);
//        add(paneS, BorderLayout.SOUTH);
//        add(paneE, BorderLayout.EAST);


        //        JLabel label = new JLabel("Hello, World!");
//        label.setBounds(50, 50, 200, 30);
//        add(label);
//        JButton button = new JButton("Click me!");
//        button.setBounds(50, 100, 100, 30);
//        add(button);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = new ImageIcon(FewFile.getImage("account/img/Gradient.png")).getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

    public static void main(String[] args) {
        new OutPane();
    }
}
