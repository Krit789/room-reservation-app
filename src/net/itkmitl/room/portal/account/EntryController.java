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
import java.awt.event.*;

public class EntryController extends Controller implements ActionListener, ComponentListener, MouseListener {
    private final EntryView view;
    private User myUser;
    public static User currentUser;

    public EntryController(EntryView view) {
        this.view = view;
    }

    public EntryView getView() {
        return view;
    }

    @Override
    protected void start() {
        this.initialize();
    }

    @Override
    protected void initialize() {
        this.initializeListener();
        resizeContentPanel();
    }

    @Override
    protected void initializeListener() {
        this.getView().optionMenuItem1.addActionListener(this);
        this.getView().helpMenuItem1.addActionListener(this);
        this.getView().loginPanel.registerLabel2.addMouseListener(this);
        this.getView().loginPanel.loginButton.addActionListener(this);
        this.getView().registerPanel.registerButton.addActionListener(this);
        this.getView().registerPanel.loginLabel2.addMouseListener(this);
        this.getView().contentPanel.addComponentListener(this);
    }

    protected void changeCard(String name) {
        CardLayout cl = (CardLayout) (this.getView().contentPanel.getLayout());
        cl.show(this.getView().contentPanel, name);
    }

    private void RegisterDetail(RegisterPanel reg) {
        //temporary placeholder
        SwingWorker worker = new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                try {
                    FewQuery db = RVDB.getDB();
                    UserRepository userRepository = new UserRepository(db);
                    getView().registerPanel.warningLabel.setIcon(new ImageIcon("resource/icons/loading-32px.gif"));
                    if (userRepository.getUserByEmail(reg.getEmail()) == null) {
                        myUser = new User();
                        myUser.setEmail(reg.getEmail());
                        myUser.setPasswordHash(FewPassword.getSalt(reg.getPassword()));
                        myUser.setFirstname(reg.getFirstName());
                        myUser.setLastname(reg.getLastName());
                        myUser.setTelephoneNumber(reg.getTelephone());
                        myUser.setRole(EnumUserRole.STUDENT);
                        userRepository.createUser(myUser);
                        currentUser = myUser;
                        changeFrame(getView(), new Dashboard());
                    } else {
                        getView().registerPanel.warningLabel.setIcon(null);
                        getView().registerPanel.warningLabel.setText("E-mail already in use!");
                    }
                } catch (Exception ex) {
                    getView().registerPanel.warningLabel.setIcon(null);
                    getView().registerPanel.warningLabel.setText("Invalid Information!");
                    JOptionPane.showMessageDialog(BaseWindow.baseFrame, ProgramError.getStackTrace(ex), "Database Query Error", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }

            @Override
            protected void done() {
                getView().registerPanel.warningLabel.setIcon(null);
            }
        };
        worker.execute();
    }

    private void userLogin(String email, String password) {
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                try {
                    getView().loginPanel.warningLabel.setIcon(new ImageIcon("resource/icons/loading-32px.gif"));
                    FewQuery db = RVDB.getDB();
                    UserRepository userRepository = new UserRepository(db);
                    myUser = userRepository.getUserByEmail(email);
                    if (myUser != null) {
                        if (FewPassword.checkPassword(password, myUser.getPasswordHash())) {
                            currentUser = myUser;
                            changeFrame(getView(), new Dashboard());
                        } else {
                            getView().loginPanel.warningLabel.setText("Invalid Password!");
                        }
                    } else {
                        getView().loginPanel.warningLabel.setText("User not found!");
                    }
                } catch (Exception ex) {
                    getView().loginPanel.warningLabel.setIcon(null);
                    getView().loginPanel.warningLabel.setText("Invalid Email and/or Password!");
                    JOptionPane.showMessageDialog(BaseWindow.baseFrame, ProgramError.getStackTrace(ex), "Database Query Error", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }

            @Override
            protected void done() {
                getView().loginPanel.warningLabel.setIcon(null);
            }
        };
        worker.execute();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.getView().optionMenuItem1) && currentUser != null) {
            this.getView().dispose();
            String[] arguments = new String[]{""};
            BaseWindow.main(arguments);
        } else if (e.getSource().equals(this.getView().helpMenuItem1)) {
            new AboutDialog(this.getView());
        } else if (e.getSource().equals(this.getView().loginPanel.loginButton)) {
            if (getView().loginPanel.getEmail().length() > 3 && getView().loginPanel.getPassword().length() > 1) {
                this.userLogin(this.getView().loginPanel.getEmail(), this.getView().loginPanel.getPassword());
            } else {
                getView().loginPanel.warningLabel.setText("Email and/or Password must not be blank!");
            }
        } else if (e.getSource().equals(this.getView().registerPanel.registerButton)) {
            this.RegisterDetail(this.getView().registerPanel);
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        if (e.getSource().equals(this.getView().contentPanel)) {
            resizeContentPanel();
        }
    }

    protected void resizeContentPanel() {
        Dimension size = this.getView().getSize();
        int width = size.width;
        int height = size.height;
        int paddingSize = Math.min(width, height) / 5;
        this.getView().contentPanel.setBorder(BorderFactory.createEmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize));
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(getView().loginPanel.registerLabel2)) {
            this.changeCard("Register");
        } else if (e.getSource().equals(getView().registerPanel.loginLabel2)) {
            this.changeCard("Login");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(getView().loginPanel.registerLabel2)) {
            getView().loginPanel.registerLabel2.setForeground(new Color(25, 93, 196));
        } else if (e.getSource().equals(getView().registerPanel.loginLabel2)) {
            getView().registerPanel.loginLabel2.setForeground(new Color(25, 93, 196));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(getView().loginPanel.registerLabel2)) {
            getView().loginPanel.registerLabel2.setForeground(new Color(94, 135, 197));
        } else if (e.getSource().equals(getView().registerPanel.loginLabel2)) {
            getView().registerPanel.loginLabel2.setForeground(new Color(94, 135, 197));
        }
    }
}
