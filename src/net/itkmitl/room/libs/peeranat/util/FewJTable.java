package net.itkmitl.room.libs.peeranat.util;

import javafx.embed.swing.SwingNode;

import javax.swing.*;

public class FewJTable {

    public static SwingNode swingNode(JTable table) {
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(table);
        return swingNode;
    }
}
