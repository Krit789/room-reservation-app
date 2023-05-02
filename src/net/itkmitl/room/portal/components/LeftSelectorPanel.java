package net.itkmitl.room.portal.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LeftSelectorPanel extends JPanel {
    public JLabel parentCategory;
    private JButton backButton;
    public ArrayList<LeftSelectorBox> boxHolder = new ArrayList<LeftSelectorBox>();

    public LeftSelectorPanel(){
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 50, 20, 50));
        this.setPreferredSize(
                new Dimension(534, (int) this.getBounds().getSize().getHeight())
        );
        this.setOpaque(false);
        parentCategory = new JLabel("Test Category");
        parentCategory.setFont(new Font("Calibri", Font.BOLD, 18));
        parentCategory.setForeground(Color.WHITE);
        backButton = new JButton("Back");
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setForeground(Color.WHITE);
        this.add(parentCategory);

        //test importing floor data(idk how this database work so have this for now)
        for(int i = 0; i < 5; i++) {
            LeftSelectorBox box = new LeftSelectorBox("Test Faculty " + i, i);
            this.add(box);
            boxHolder.add(box);
        }
        //would be in a loop for all component to add

        this.add(backButton);
    }
}
