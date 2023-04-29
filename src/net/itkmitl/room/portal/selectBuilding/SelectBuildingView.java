package net.itkmitl.room.portal.selectBuilding;

import net.itkmitl.room.portal.View;
import net.itkmitl.room.portal.components.Header;
import net.itkmitl.room.portal.components.LeftPanel;
import net.itkmitl.room.portal.components.MainPanel;

import javax.swing.*;
import java.awt.*;

public class SelectBuildingView extends View {
    public JPanel leftPanel, innerPanel, mainPanel, header;

    public SelectBuildingView() {
        super();
    }

    @Override
    protected void initialize() {
        leftPanel = new LeftPanel();
        innerPanel = new MainPanel();
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);

        header = new Header("Select a Building", "But I need to tell you something");

        innerPanel.add(new JLabel("Hi"));

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(innerPanel, BorderLayout.CENTER);

        this.outerPane.setBackground(new Color(255, 255, 255));

        this.add(leftPanel, BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
    }
}