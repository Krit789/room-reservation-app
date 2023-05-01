package net.itkmitl.room.portal.account;

import net.itkmitl.room.portal.Controller;
import net.itkmitl.room.portal.account.components.LoginPanel;
import net.itkmitl.room.portal.account.components.RegisterPanel;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.components.AboutDialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntryController extends Controller implements ActionListener{
    private final EntryView view;

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
    }

    protected void changeCard(String name){
        CardLayout cl = (CardLayout)(this.getView().contentPannel.getLayout());
        cl.show(this.getView().contentPannel, name);
    }

    private String getRegisterDetail(RegisterPanel reg){
        //temporary placeholder
        return reg.getData();
    }

    private String getLoginDetail(LoginPanel login){
        //temporary placeholder
        return login.getData();
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
            System.out.println(this.getLoginDetail(this.getView().loginPanel));
        } else if (e.getSource().equals(this.getView().registerPanel.registerButton)){
            System.out.println(this.getRegisterDetail(this.getView().registerPanel));
        } else if (e.getSource().equals(this.getView().registerPanel.loginButton)){
            this.changeCard("Login");
        }
    }
}
