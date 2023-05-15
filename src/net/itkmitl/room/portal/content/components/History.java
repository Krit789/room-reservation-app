package net.itkmitl.room.portal.content.components;

import net.itkmitl.room.portal.CardView;
import net.itkmitl.room.portal.components.ButtonGradient;
import net.itkmitl.room.portal.content.components.history.HistoryLeftPanel;

import javax.swing.*;
import java.awt.*;

public class History extends CardView {
    public ButtonGradient backButton;
    public JPanel btnPanel, mainPanel;

    public History(){
        backButton = new ButtonGradient();
        btnPanel = new HistoryLeftPanel();
    }
}
