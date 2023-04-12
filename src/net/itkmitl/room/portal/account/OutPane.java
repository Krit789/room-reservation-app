package net.itkmitl.room.portal.account;

import net.itkmitl.room.libs.peeranat.util.FewFile;

import javax.swing.*;
import java.awt.*;

public class OutPane extends JPanel {

    public OutPane() {
        setLayout(null);
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
        Image image = new ImageIcon(FewFile.getImage("account/img/account_background.png")).getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

    public static void main(String[] args) {
        new OutPane();
    }
}
