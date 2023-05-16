package net.itkmitl.room.portal.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.itkmitl.room.libs.peeranat.util.FewFile;

public class LeftPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 561892552360870497L;
    public JLabel lthIcon, title;
    public JPanel titleBox;

    public LeftPanel() {
        super(new BorderLayout());

        this.setBackground(new Color(16, 52, 105));
        this.setPreferredSize(
                new Dimension(408, (int) this.getBounds().getSize().getHeight())
        );

        titleBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lthIcon = new JLabel(FewFile.getImage("icons/icon-64px.png"));
        title = new JLabel("LAEW TAE HONG");
        title.setFont(new Font("Cousine", Font.BOLD, 29));

        title.setForeground(Color.WHITE);
        titleBox.setOpaque(false);
        titleBox.setBorder(new EmptyBorder(30, 30, 30, 0));
        titleBox.add(lthIcon);
        titleBox.add(Box.createHorizontalStrut(10));
        titleBox.add(title);

        this.add(titleBox, BorderLayout.NORTH);
    }
}
