package net.itkmitl.room.portal.account;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.enums.EnumUserRole;
import net.itkmitl.room.libs.jarukrit.ProgramError;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.util.FewPassword;
import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.phatsanphon.repository.UserRepository;
import net.itkmitl.room.portal.Controller;
import net.itkmitl.room.portal.account.components.LoginPanel;
import net.itkmitl.room.portal.account.components.RegisterPanel;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.components.AboutDialog;
import net.itkmitl.room.portal.dashboard.Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class EntryController extends Controller implements ActionListener, ComponentListener {
    private final EntryView view;
    private User myUser;
    public static User currentUser;

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

    private void RegisterDetail(RegisterPanel reg){
        //temporary placeholder
        try {
            FewQuery db = RVDB.getDB();
            UserRepository userRepository = new UserRepository(db);
            if(userRepository.getUserByEmail(reg.getEmail()) == null) {
                myUser = new User();
                myUser.setEmail(reg.getEmail());
                myUser.setPasswordHash(FewPassword.getSalt(reg.getPassword()));
                myUser.setFirstname(reg.getFirstName());
                myUser.setLastname(reg.getLastName());
                myUser.setTelephoneNumber(reg.getTelephone());
                myUser.setRole(EnumUserRole.STUDENT);
                userRepository.createUser(myUser);
                currentUser = myUser;
                this.changeFrame(this.getView(), new Dashboard());
            }else{
                this.getView().registerPanel.warningLabel.setText("E-mail already in use!");
            }
        } catch (Exception ex){
            this.getView().registerPanel.warningLabel.setText("Invalid Information!");
            JOptionPane.showMessageDialog(BaseWindow.baseFrame, ProgramError.getStackTrace(ex), "Database Query Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void userLogin(String email, String password){
        try {
            FewQuery db = RVDB.getDB();
            UserRepository userRepository = new UserRepository(db);
            myUser = userRepository.getUserByEmail(email);
            if (myUser != null) {
                if (FewPassword.checkPassword(password, myUser.getPasswordHash())) {
                    currentUser = myUser;
                    this.changeFrame(this.getView(), new Dashboard());
                }else{
                    this.getView().loginPanel.warningLabel.setText("Invalid Password!");
                }
            }else{
                this.getView().loginPanel.warningLabel.setText("User not found!");
            }
        } catch (Exception ex){
            this.getView().loginPanel.warningLabel.setText("Invalid Email and/or Password!");
            JOptionPane.showMessageDialog(BaseWindow.baseFrame, ProgramError.getStackTrace(ex), "Database Query Error", JOptionPane.ERROR_MESSAGE);
        }
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
            this.userLogin(this.getView().loginPanel.getEmail(), this.getView().loginPanel.getPassword());
        } else if (e.getSource().equals(this.getView().registerPanel.registerButton)){
            this.RegisterDetail(this.getView().registerPanel);
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
