package net.itkmitl.room.portal.account;

import net.itkmitl.room.portal.View;
import net.itkmitl.room.portal.account.components.LoginPanel;
import net.itkmitl.room.portal.account.components.RegisterPanel;

import javax.swing.*;
import java.awt.*;

public class EntryView extends View {
    private static final long serialVersionUID = -8997505387144700741L;
    protected JPanel contentPanel;
    protected LoginPanel loginPanel;
    protected RegisterPanel registerPanel;

    @Override
    protected void initialize() {
        contentPanel = new JPanel(new CardLayout());
        contentPanel.setOpaque(false);
        loginPanel = new LoginPanel();
        registerPanel = new RegisterPanel();
        contentPanel.add("Login", loginPanel);
        contentPanel.add("Register", registerPanel);
        this.add(contentPanel);
        this.setBackground(Color.white);
        this.repaint();

    }
}
