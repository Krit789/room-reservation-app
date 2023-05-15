package net.itkmitl.room.portal.content.components;

import net.itkmitl.room.portal.CardView;
import net.itkmitl.room.portal.components.ButtonGradient;

import javax.swing.*;
import java.awt.*;

public class History extends CardView {
    public ButtonGradient backButton;
    public JPanel statusPanel, reservationListPanel;

    public History(){
        reservationListPanel = new JPanel(new CardLayout());
        backButton = new ButtonGradient();
        backButton.setText("Back");
        add(backButton);
        backButton.setActionCommand("Back to Dashboard");
    }
}
