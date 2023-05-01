package net.itkmitl.room.portal.admin.components.OOBE;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OOBEController implements ActionListener {
    OOBEView view;

    public OOBEController() {
        view = new OOBEView();
        view.nextButton.addActionListener(this);
        view.backButton.addActionListener(this);
        view.cancelButton.addActionListener(this);
    }

    public static void main(String[] args) {
        try {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.application.name", "Laew Tae Hong");
            System.setProperty("apple.awt.application.appearance", "system");
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (Exception ignored) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            }
        } catch (Exception ignored) {
        }
        new OOBEController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.nextButton)) {
            CardLayout c = (CardLayout) view.contentPanel.getLayout();
            c.show(view.contentPanel, OOBEView.PANEL2);
        } else if (e.getSource().equals(view.backButton)) {
            CardLayout c = (CardLayout) view.contentPanel.getLayout();
            c.show(view.contentPanel, OOBEView.PANEL1);
        } else if (e.getSource().equals(view.cancelButton)) {
            view.getFrame().dispose();
        }
    }
}
