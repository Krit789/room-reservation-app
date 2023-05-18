package net.itkmitl.room.portal.account;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.enums.EnumUserRole;
import net.itkmitl.room.libs.jarukrit.ProgramError;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.util.FewFile;
import net.itkmitl.room.libs.peeranat.util.FewPassword;
import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.phatsanphon.repository.UserRepository;
import net.itkmitl.room.libs.store.AppStore;
import net.itkmitl.room.portal.Controller;
import net.itkmitl.room.portal.account.components.LoginPanel;
import net.itkmitl.room.portal.account.components.RegisterPanel;
import net.itkmitl.room.portal.admin.BaseWindow;
import net.itkmitl.room.portal.components.AboutDialog;
import net.itkmitl.room.portal.content.MainContentPortal;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class EntryController extends Controller implements ActionListener, ComponentListener, DocumentListener, MouseListener {
    private final EntryView view;
    private final LoginPanel loginPanel;
    private final RegisterPanel registerPanel;
    private User myUser;
    private final AppStore store = AppStore.getAppStore();
    private String currentCard = "Login";

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
        loginPanel.getPasswordField().getDocument().addDocumentListener(this);
        registerPanel.getEmailField().getDocument().addDocumentListener(this);
        registerPanel.getPasswordField().getDocument().addDocumentListener(this);
        registerPanel.getConfirmPasswordField().getDocument().addDocumentListener(this);
        registerPanel.getRegisterButton().addActionListener(this);
        registerPanel.getLoginLabel2().addMouseListener(this);
        this.getView().contentPanel.addComponentListener(this);
    }

    protected void changeCard(String name) {
        CardLayout cl = (CardLayout) (this.getView().contentPanel.getLayout());
        cl.show(this.getView().contentPanel, name);
        currentCard = name;
    }

    private void RegisterDetail(RegisterPanel reg) {
        //temporary placeholder
        SwingWorker<?, ?> worker = new SwingWorker<>() {

            @Override
            protected Object doInBackground() {
                try {
                    FewQuery db = LaewTaeDB.getDB();
                    UserRepository userRepository = new UserRepository(db);
                    getView().registerPanel.getWarningLabel().setIcon(FewFile.getImage("icons/loading-32px.gif"));
                    int w = getView().registerPanel.getWarningLabel().getIcon().getIconWidth();
                    int h = getView().registerPanel.getWarningLabel().getIcon().getIconHeight();
                    Dimension size = new Dimension(w, h);
                    getView().registerPanel.getWarningLabel().setMinimumSize(size);
                    getView().registerPanel.getWarningLabel().setPreferredSize(size);
                    if (userRepository.getExactUserByEmail(reg.getEmail()) == null) {
                        myUser = new User();
                        myUser.setEmail(reg.getEmail());
                        myUser.setPasswordHash(FewPassword.getSalt(reg.getPassword()));
                        myUser.setFirstname(reg.getFirstName());
                        myUser.setLastname(reg.getLastName());
                        myUser.setTelephoneNumber(reg.getTelephone());
                        myUser.setRole(EnumUserRole.STUDENT);
                        userRepository.createUser(myUser);
                        store.dispatch("user", myUser);
                        changeFrame(getView(), new MainContentPortal());
                    } else {
                        getView().registerPanel.getWarningLabel().setIcon(null);
                        getView().registerPanel.getWarningLabel().setText("E-mail already in use!");
                    }
                } catch (Exception ex) {
                    getView().registerPanel.getWarningLabel().setIcon(null);
                    getView().registerPanel.getWarningLabel().setText("Invalid Information!");
                    JOptionPane.showMessageDialog(BaseWindow.baseFrame, ProgramError.getStackTrace(ex), "Database Query Error", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }

            @Override
            protected void done() {
                getView().registerPanel.getWarningLabel().setIcon(null);
            }
        };
        worker.execute();
    }

    private void userLogin(String email, String password) {
        SwingWorker<?, ?> worker = new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                try {
                    getView().loginPanel.getWarningLabel().setIcon(FewFile.getImage("icons/loading-32px.gif"));
                    int w = getView().loginPanel.getWarningLabel().getIcon().getIconWidth();
                    int h = getView().loginPanel.getWarningLabel().getIcon().getIconHeight();
                    Dimension size = new Dimension(w, h);
                    getView().loginPanel.getWarningLabel().setMinimumSize(size);
                    getView().loginPanel.getWarningLabel().setPreferredSize(size);
                    FewQuery db = LaewTaeDB.getDB();
                    UserRepository userRepository = new UserRepository(db);
                    myUser = userRepository.getExactUserByEmail(email);
                    if (myUser != null) {
                        if (FewPassword.checkPassword(password, myUser.getPasswordHash())) {
                            store.dispatch("user", myUser);
                            changeFrame(getView(), new MainContentPortal());
                        } else {
                            getView().loginPanel.getWarningLabel().setText("<html>Invalid Password!</html>");
                        }
                    } else {
                        getView().loginPanel.getWarningLabel().setText("<html>User not found!</html>");
                    }
                } catch (Exception ex) {
                    getView().loginPanel.getWarningLabel().setIcon(null);
                    getView().loginPanel.getWarningLabel().setText("<html>Invalid Email and/or Password!</html>");
                    JOptionPane.showMessageDialog(BaseWindow.baseFrame, ProgramError.getStackTrace(ex), "Database Query Error", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }

            @Override
            protected void done() {
                getView().loginPanel.getWarningLabel().setIcon(null);
            }
        };
        worker.execute();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object objectPerformed = e.getSource();
        if (objectPerformed.equals(this.getView().optionMenuItem1) && store.select("user") != null) {
            this.getView().dispose();
            BaseWindow.main(new String[]{""});
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

    private int emailValidator() {
        JTextField email = null;
        int type = 0;
        if (currentCard.equals("Login")) {
            email = loginPanel.getEmailField();
            type = 0;
        } else if (currentCard.equals("Register")) {
            email = registerPanel.getEmailField();
            type = 1;
        }
        if (email.getText().length() < 5) {
            return -2;
        } else if (!email.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            return -1;
        } else {
            return 0;
        }
    }

    private int passwordValidator() {
        JPasswordField password = null;
        JPasswordField confirmPassword = null;
        int type = 0;
        if (currentCard.equals("Login")) {
            password = loginPanel.getPasswordField();
            type = 0;
        } else if (currentCard.equals("Register")) {
            password = registerPanel.getPasswordField();
            confirmPassword = registerPanel.getConfirmPasswordField();
            type = 1;
        }
        if (String.valueOf(password.getPassword()).length() < 4) {
            return -1;
        } else if (type == 1 && !String.valueOf(password.getPassword()).equals(String.valueOf(confirmPassword.getPassword()))) {
            return -2;
        } else {
            return 1;
        }

    }

    private void allValidator() {
        int result1 = emailValidator();
        int result2 = passwordValidator();
        String outText = null;
        boolean enable = false;
        if (currentCard.equals("Login")) {
            if (result1 == 0 && result2 == 1) {
                enable = true;
                outText = " ";
            } else if (result1 == -1 && result2 == 1) {
                enable = false;
                outText = "Invalid E-Mail!";
            } else if ((result1 == -1 || result1 == -2) && result2 == -1) {
                enable = false;
                outText = "Invalid E-Mail and Password must be at least 4 character long";
            } else if (result1 == 0 && result2 == -1) {
                enable = false;
                outText = "Password must be at least 4 character long";
            }
            loginPanel.getWarningLabel().setText(outText);
            loginPanel.getLoginButton().setEnabled(enable);
            FontMetrics fm = getView().loginPanel.getWarningLabel().getFontMetrics(getView().loginPanel.getWarningLabel().getFont());
            int w = fm.stringWidth("0000");
            int h = fm.getHeight();
            Dimension size = new Dimension(w, h);
            getView().loginPanel.getWarningLabel().setMinimumSize(size);
            getView().loginPanel.getWarningLabel().setPreferredSize(size);
        } else if (currentCard.equals("Register")) {
            if (result1 == 0 && result2 == 1) {
                enable = true;
                outText = " ";
            } else if (result1 == -1 && result2 == 1) {
                enable = false;
                outText = "Invalid E-Mail!";
            } else if ((result1 == -1 || result1 == -2) && result2 == -1) {
                enable = false;
                outText = "Invalid E-Mail and Password must be at least 4 character long";
            } else if ((result1 == -1 || result1 == -2) && result2 == -2) {
                enable = false;
                outText = "Invalid E-Mail and Password and Confirm Password must be the same";
            } else if (result1 == 0 && result2 == -1) {
                enable = false;
                outText = "Password must be at least 4 character long";
            } else if (result1 == 0 && result2 == -2) {
                enable = false;
                outText = "Password and Confirm Password must be the same";
            }
            registerPanel.getWarningLabel().setText(outText);
            registerPanel.getRegisterButton().setEnabled(enable);
            FontMetrics fm = getView().registerPanel.getWarningLabel().getFontMetrics(getView().registerPanel.getWarningLabel().getFont());
            int w = fm.stringWidth("0000");
            int h = fm.getHeight();
            Dimension size = new Dimension(w, h);
            getView().registerPanel.getWarningLabel().setMinimumSize(size);
            getView().registerPanel.getWarningLabel().setPreferredSize(size);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        allValidator();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        allValidator();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        allValidator();
    }
}
