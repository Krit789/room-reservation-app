package net.itkmitl.room.portal.components;

import javafx.scene.shape.Box;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AboutDialog implements ActionListener, ListSelectionListener {
    private final JDialog aboutDialog;
    private final JList nameList;
    private final JScrollPane scrollPane;
    private final JPanel aboutPanel, titlePanel, okPanel, listPanel;
    private final JLabel title, menuDesc, role, logo;
    private final JButton okButton;

    public AboutDialog(JFrame baseFrame) {
        JLabel logo1;
        aboutDialog = new JDialog(baseFrame, "About", false);
        aboutDialog.setLayout(new GridBagLayout());
        aboutPanel = new JPanel();

        // JDialog Layout
        aboutPanel.setLayout(new GridBagLayout());

        // Laew Tae Hong Title and Description
        titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        title = new JLabel("Laew Tae Hong");
        menuDesc = new JLabel("Made possible by these peoples!");
        title.setMinimumSize(new Dimension(300, 70));
        title.setFont(new Font("Arial", Font.BOLD, 25));
        titlePanel.add(title);
        titlePanel.add(menuDesc);
        try {
            logo1 = new JLabel(new ImageIcon("resource/icons/icon-48px.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
            logo1 = new JLabel("Logo");
        }
        logo = logo1;
        aboutPanel.add(logo, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 1, 0, 0, new Insets(5, 5, 0, 0)).getGBC());
        aboutPanel.add(titlePanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 1, 0, new Insets(5, 15, 0, 5)).getGBC());
        aboutDialog.add(aboutPanel, new GBCBuilder(GridBagConstraints.CENTER, 1, 0.65, 0, 0).getGBC());

        // Programmer List
        listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());
        nameList = new JList(new String[]{"Jarukrit Sripaploen", "Napat Wetchapun", "Tanakrit Supprasit", "Peeranat Matsor", "Phatsanphon Nakaranurak", "Saruta Torat"});
        nameList.setVisibleRowCount(3);
        nameList.setToolTipText("Click on the list to reveal each person's roles");
        nameList.addListSelectionListener(this);
        scrollPane = new JScrollPane(nameList);
        listPanel.add(scrollPane, BorderLayout.CENTER);
        role = new JLabel("");
        role.setBorder(new EmptyBorder(5, 0, 0, 0));
        listPanel.add(role, BorderLayout.SOUTH);
        aboutDialog.add(listPanel, new GBCBuilder(GridBagConstraints.BOTH, 1, 0.3, 0, 1, new Insets(0, 15, 5, 15)).getGBC());

        // OK Button
        okButton = new JButton("OK");
        okButton.addActionListener(this);
        okPanel = new JPanel();
        okPanel.add(okButton);
        aboutDialog.add(okPanel, new GBCBuilder(GridBagConstraints.CENTER, 1, 0.05, 0, 2).getGBC());

        aboutDialog.setSize(320, 260);
        aboutDialog.setResizable(false);
        aboutDialog.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        aboutDialog.dispose();
    }

    public void valueChanged(ListSelectionEvent e) {
        switch (((JList) e.getSource()).getSelectedIndex()) {
            case 0:
                // Jarukrit
                role.setText("65070030   Admin Frontend Developer");
                break;
            case 1:
                // Napat
                role.setText("65070064   User Frontend Developer");
                break;
            case 2:
                // Tanakrit
                role.setText("65070089   User Frontend Co-Developer");
                break;
            case 3:
                // Peeranat
                role.setText("65070159   Backend libs Developer");
                break;
            case 4:
                // Phatsanphon
                role.setText("65070171   User Frontend and Backend Developer");
                break;
            case 5:
                // Saruta
                role.setText("65070211   UI Designer and Arts");
                break;
        }
    }
}
