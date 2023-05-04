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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class EntryController extends Controller implements ActionListener, ComponentListener, DocumentListener, MouseListener {
    private final EntryView view;
    private User myUser;
    public static User currentUser;
    private final LoginPanel loginPanel;
    private final RegisterPanel registerPanel;

    public EntryController(EntryView view) {
        this.view = view;
        loginPanel = getView().loginPanel;
        registerPanel = getView().registerPanel;
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
        loginPanel.getRegisterLabel2().addMouseListener(this);
        loginPanel.getLoginButton().addActionListener(this);
        loginPanel.getEmailField().addActionListener(this);
        loginPanel.getEmailField().getDocument().addDocumentListener(this);
        loginPanel.getPasswordField().addActionListener(this);
        loginPanel.getPasswordField().getDocument().addDocumentListener(this);
        registerPanel.getRegisterButton().addActionListener(this);
        registerPanel.getLoginLabel2().addMouseListener(this);
        this.getView().contentPanel.addComponentListener(this);
    }

    protected void changeCard(String name) {
        CardLayout cl = (CardLayout) (this.getView().contentPanel.getLayout());
        cl.show(this.getView().contentPanel, name);
    }

    private void RegisterDetail(RegisterPanel reg) {
        //temporary placeholder
        try {
            FewQuery db = RVDB.getDB();
            UserRepository userRepository = new UserRepository(db);
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
                this.changeFrame(this.getView(), new Dashboard());
            } else {
                registerPanel.getWarningLabel().setText("E-mail already in use!");
            }
        } catch (Exception ex) {
            registerPanel.getWarningLabel().setText("Invalid Information!");
            JOptionPane.showMessageDialog(BaseWindow.baseFrame, ProgramError.getStackTrace(ex), "Database Query Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void userLogin(String email, String password) {
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                try {
                    FewQuery db = RVDB.getDB();
                    UserRepository userRepository = new UserRepository(db);
                    myUser = userRepository.getUserByEmail(email);
                    if (myUser != null) {
                        if (FewPassword.checkPassword(password, myUser.getPasswordHash())) {
                            currentUser = myUser;
                            changeFrame(getView(), new Dashboard());
                        } else {
                            getView().loginPanel.getWarningLabel().setText("Invalid Password!");
                        }
                    } else {
                        getView().loginPanel.getWarningLabel().setText("User not found!");
                    }
                } catch (Exception ex) {
                    getView().loginPanel.getWarningLabel().setText("Invalid Email and/or Password!");
                    JOptionPane.showMessageDialog(BaseWindow.baseFrame, ProgramError.getStackTrace(ex), "Database Query Error", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }
        };
        worker.execute();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object objectPerformed = e.getSource();
        if (objectPerformed.equals(this.getView().optionMenuItem1) && currentUser != null) {
            this.getView().dispose();
            String[] arguments = new String[]{""};
            BaseWindow.main(arguments);
        } else if (objectPerformed.equals(this.getView().helpMenuItem1)) {
            new AboutDialog(this.getView());
        } else if (objectPerformed.equals(loginPanel.getLoginButton())) {
            if (getView().loginPanel.getEmail().length() > 3 && getView().loginPanel.getPassword().length() > 1) {
                this.userLogin(loginPanel.getEmail(), loginPanel.getPassword());
            } else {
                getView().loginPanel.getWarningLabel().setText("Email and/or Password must not be blank!");
            }
        } else if (objectPerformed.equals(registerPanel.getRegisterButton())) {
            this.RegisterDetail(registerPanel);
        }
        // Login TextField and PasswordField
        else if (objectPerformed.equals(loginPanel.getEmailField()) || objectPerformed.equals(loginPanel.getPasswordField())) {
            loginPanel.getLoginButton().doClick();
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Object objectPerformed = e.getSource();
        if (objectPerformed.equals(this.getView().contentPanel)) {
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
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object objectPerformed = e.getSource();
        if (objectPerformed.equals(getView().loginPanel.getRegisterLabel2())) {
            getView().loginPanel.getRegisterLabel2().setForeground(new Color(25, 93, 196));
        } else if (objectPerformed.equals(getView().registerPanel.getLoginLabel2())) {
            getView().registerPanel.getLoginLabel2().setForeground(new Color(25, 93, 196));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object objectPerformed = e.getSource();
        if (objectPerformed.equals(getView().loginPanel.getRegisterLabel2())) {
            this.changeCard("Register");
        } else if (objectPerformed.equals(getView().registerPanel.getLoginLabel2())) {
            this.changeCard("Login");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object objectPerformed = e.getSource();
        if (objectPerformed.equals(getView().loginPanel.getRegisterLabel2())) {
            getView().loginPanel.getRegisterLabel2().setForeground(new Color(94, 135, 197));
        } else if (objectPerformed.equals(getView().registerPanel.getLoginLabel2())) {
            getView().registerPanel.getLoginLabel2().setForeground(new Color(94, 135, 197));
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        loginPanel.getWarningLabel().setText(" ");
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        loginPanel.getWarningLabel().setText(" ");
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
