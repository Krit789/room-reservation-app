package net.itkmitl.room.portal.content.components.history;

import net.itkmitl.room.portal.components.TransparentPanel;

import javax.swing.*;

public class HistoryRightPanel extends TransparentPanel {
    private JLabel text;
    public HistoryRightPanel(){
        text = new JLabel("Contents go here!");
        add(text);
    }

}
