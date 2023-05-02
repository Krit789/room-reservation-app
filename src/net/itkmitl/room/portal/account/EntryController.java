package net.itkmitl.room.portal.account;

import net.itkmitl.room.portal.Controller;
import net.itkmitl.room.portal.account.components.LoginPanel;
import net.itkmitl.room.portal.account.components.RegisterPanel;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.components.AboutDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class EntryController extends Controller implements ActionListener, ComponentListener {
    private final EntryView view;
    private User myUser;

    public EntryController(EntryView view){
        this.view = view;
    }
    public EntryView getView(){
        return view;
    }
    @Override
    protected void start() {
        this.initialize();
    }
    @Override
    protected void initialize() {
        this.initializeListener();
    }

    @Override
    protected void initializeListener() {
        this.getView().optionMenuItem1.addActionListener(this);
        this.getView().helpMenuItem1.addActionListener(this);
        this.getView().loginPanel.registerButton.addActionListener(this);
        this.getView().loginPanel.loginButton.addActionListener(this);
        this.getView().registerPanel.registerButton.addActionListener(this);
        this.getView().registerPanel.loginButton.addActionListener(this);
        this.getView().contentPannel.addComponentListener(this);
    }

    protected void changeCard(String name){
        CardLayout cl = (CardLayout)(this.getView().contentPannel.getLayout());
        cl.show(this.getView().contentPannel, name);
    }

        //temporary placeholder
    }

        //temporary placeholder
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.getView().optionMenuItem1)) {
            this.getView().dispose();
            String[] arguments = new String[]{""};
            BaseWindow.main(arguments);
        } else if (e.getSource().equals(this.getView().helpMenuItem1)){
            new AboutDialog(this.getView());
        } else if (e.getSource().equals(this.getView().loginPanel.registerButton)){
            this.changeCard("Register");
        } else if (e.getSource().equals(this.getView().loginPanel.loginButton)){
        } else if (e.getSource().equals(this.getView().registerPanel.registerButton)){
        } else if (e.getSource().equals(this.getView().registerPanel.loginButton)){
            this.changeCard("Login");
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        if(e.getSource().equals(this.getView().contentPannel)){
            Dimension size = this.getView().getSize();
            int width = size.width;
            int height = size.height;
            int paddingSize = Math.min(width, height) / 5;
            this.getView().contentPannel.setBorder(BorderFactory.createEmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize));
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
