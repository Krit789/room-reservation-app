package net.itkmitl.room.portal;

import net.itkmitl.room.libs.peeranat.util.FewFile;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class MyFrame extends JFrame {

    public MyFrame() {
        // Load the image
        ImageIcon icon = new ImageIcon(FewFile.getImage("account/img/it_building.png"));
        Image image = icon.getImage();

        // Create a label and add the image icon to it
        JLabel label = new JLabel();
        label.setIcon(icon);

        // Add the label to the frame
        getContentPane().add(label, BorderLayout.CENTER);

        // Set the frame properties
        setTitle("My Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MyFrame().setVisible(true);
        });
    }
}
