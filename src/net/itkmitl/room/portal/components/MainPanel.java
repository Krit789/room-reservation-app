package net.itkmitl.room.portal.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = -7478512272957985676L;
    public ArrayList<MainRoomSelectionBox> mainBoxHolder = new ArrayList<MainRoomSelectionBox>();


    public MainPanel() {
        super(new BorderLayout());
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(30, 70, 30, 70));

    }
}
