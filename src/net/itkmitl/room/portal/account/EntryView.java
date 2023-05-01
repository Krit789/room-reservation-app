package net.itkmitl.room.portal.account;

import net.itkmitl.room.portal.View;
import net.itkmitl.room.portal.account.components.LoginPanel;
import net.itkmitl.room.portal.account.components.RegisterPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntryView extends View{
    protected JPanel contentPannel;
    protected LoginPanel loginPanel;
    protected RegisterPanel registerPanel;

    @Override
    protected void initialize() {
        contentPannel = new JPanel(new CardLayout());
        contentPannel.setBorder(BorderFactory.createEmptyBorder(300, 200, 300, 200));
        contentPannel.setOpaque(false);
        loginPanel = new LoginPanel();
        registerPanel = new RegisterPanel();
        contentPannel.add("Login", loginPanel);
        contentPannel.add("Register", registerPanel);
        this.add(contentPannel);
    }
}
