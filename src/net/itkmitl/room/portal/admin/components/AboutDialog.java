package net.itkmitl.room.portal.admin.components;

import javafx.scene.shape.Box;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutDialog{
    private final JDialog aboutDialog;
    private final JPanel aboutPanel, titlePanel, okPanel;
    private final JLabel title, menuDesc;
    private final JButton okButton;
    public AboutDialog(JFrame baseFrame){
        aboutDialog = new JDialog(baseFrame, "About", true);
        aboutPanel = new JPanel();
        aboutPanel.setLayout(new FlowLayout());
        aboutDialog.add(aboutPanel);

        titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        title = new JLabel("Laew Tae Hong");
        menuDesc = new JLabel("Developed by IT20 @ KMITL");
        title.setMinimumSize(new Dimension(300, 70));
        title.setFont(new Font("Arial", Font.BOLD, 25));

        titlePanel.add(title);
        titlePanel.add(menuDesc);
        titlePanel.setBorder(new EmptyBorder(20,20,20,20));
        aboutPanel.add(titlePanel);

        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aboutDialog.dispose();
            }
        });
        okPanel = new JPanel();
        okPanel.add(okButton);
        aboutPanel.add(okPanel);

        aboutDialog.setSize(320, 240);
        aboutDialog.setResizable(false);
        aboutDialog.setVisible(true);

    }
}
