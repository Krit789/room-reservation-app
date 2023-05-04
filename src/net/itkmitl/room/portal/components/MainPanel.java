package net.itkmitl.room.portal.components;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = -7478512272957985676L;
    public ArrayList<MainSelectionBox> mainBoxHolder = new ArrayList<MainSelectionBox>();


    public MainPanel() {
        super(new BorderLayout());
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(30, 70, 30, 70));
    }
}
