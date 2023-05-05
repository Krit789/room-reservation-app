package net.itkmitl.room.portal.content.components;

import net.itkmitl.room.portal.CardView;
import net.itkmitl.room.portal.components.Header;
import net.itkmitl.room.portal.components.LeftPanel;
import net.itkmitl.room.portal.components.LeftSelectorPanel;
import net.itkmitl.room.portal.components.MainPanel;

import javax.swing.*;
import java.awt.*;

public class Selector extends CardView {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8564430568200760268L;
	public JPanel leftPanel, innerPanel, mainPanel, header, leftSelectorPanel;
    public JLabel testLabel;//delete later

    public Selector() {
        super();
    }

    @Override
    public void initialize() {
        leftPanel = new LeftPanel();
        innerPanel = new MainPanel();
        mainPanel = new JPanel(new BorderLayout());
        leftSelectorPanel = new LeftSelectorPanel();
        mainPanel.setOpaque(false);

        header = new Header("Select a Building", "But I need to tell you something");

        testLabel = new JLabel("Hi");
        innerPanel.add(testLabel);

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(innerPanel, BorderLayout.CENTER);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
        leftPanel.add(leftSelectorPanel, BorderLayout.CENTER);
    }
    public LeftSelectorPanel getSelectorPanel(){
        return (LeftSelectorPanel) this.leftSelectorPanel;
    }

    public MainPanel getMainPanel() {
        return (MainPanel) mainPanel;
    }
}

