package net.itkmitl.room.portal.admin;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import com.formdev.flatlaf.FlatIntelliJLaf;
import net.itkmitl.room.GUIStarter;
import net.itkmitl.room.libs.peeranat.util.FewFile;
import net.itkmitl.room.portal.Login;
import net.itkmitl.room.portal.admin.components.AboutDialog;
import net.itkmitl.room.portal.admin.components.GBCBuilder;
import net.itkmitl.room.portal.admin.components.OperationWindow;
import net.itkmitl.room.portal.admin.components.PreferenceWindow;

public class BaseWindow {
    private OperationWindow mainMenu;
    private final JFrame baseFrame;
    private final JPanel statusBar;
    private final JMenuBar menuBar;
    private final JMenu fileMenu, windowMenu, optionMenu, aboutMenu, helpMenu;
    private final JMenuItem fileMenuItem1, fileMenuItem2, fileMenuItem3;
    private final JMenuItem optionMenuItem1, optionMenuItem2, optionMenuItem3, optionMenuItem4;
    private final JMenu newWindowMenu;
    private final JMenuItem windowMenuItem1, windowMenuItem2, windowMenuItem3;
    private final JCheckBoxMenuItem windowCheckBoxMenuItem1;
    private final JMenuItem aboutMenuItem1;
    private final JDesktopPane desktop;
    private final JProgressBar progressBar;
    private final JLabel statusLabel;
    private ArrayList<Image> multiIcon;

    private boolean autoCenterMainMenu = true;

    public BaseWindow() {
        baseFrame = new JFrame("Laew Tae Hong Management");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        multiIcon = new ArrayList<>();
        multiIcon.add(new ImageIcon("resource/icons/icon-208px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-128px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-64px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-56px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-48px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-40px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-20px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-16px.png").getImage());
        baseFrame.setIconImages(multiIcon);
        baseFrame.setSize(screenSize);
        baseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        baseFrame.setMinimumSize(new Dimension(640, 480));
        baseFrame.setSize(new Dimension(1280, 720));
        baseFrame.addComponentListener(new CustomComponentListener());
        baseFrame.setExtendedState(baseFrame.getExtendedState() | baseFrame.MAXIMIZED_BOTH);

        // Menu Components declaration
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        optionMenu = new JMenu("Options");
        windowMenu = new JMenu("Window");
        aboutMenu = new JMenu("About");
        helpMenu = new JMenu("Help");

        // 'File' Menu Components declaration
        fileMenuItem1 = new JMenuItem("Status");
        fileMenuItem2 = new JMenuItem("View Logs");
        fileMenuItem3 = new JMenuItem("Exit");
        fileMenuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        // 'Options' Menu Components declaration
        optionMenuItem1 = new JMenuItem("Connect");
        optionMenuItem2 = new JMenuItem("Disconnect");
        optionMenuItem3 = new JMenuItem("Settings");
        optionMenuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spawnPreference();
            }
        });
        optionMenuItem4 = new JMenuItem("Switch to User Mode");
        optionMenuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseFrame.dispose();
                String[] arguments = new String[]{""};
                GUIStarter.main(arguments);
            }
        });

        windowCheckBoxMenuItem1 = new JCheckBoxMenuItem("Auto Center Main Menu");
        windowCheckBoxMenuItem1.setState(autoCenterMainMenu);
        windowCheckBoxMenuItem1.setToolTipText("Automatically center Main Menu when Main Window is resized");
        windowCheckBoxMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowCheckBoxMenuItem1.setState(!autoCenterMainMenu);
                autoCenterMainMenu = !autoCenterMainMenu;
                if (autoCenterMainMenu) {
                    Dimension desktopSize = desktop.getSize();
                    Dimension jInternalFrameSize = mainMenu.getFrame().getSize();
                    mainMenu.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                            (desktopSize.height - jInternalFrameSize.height) / 2);
                }
            }
        });
        windowMenuItem2 = new JMenuItem("Cascade Window");
        windowMenuItem3 = new JMenuItem("Close all Window");
        newWindowMenu = new JMenu("New");
        windowMenuItem1 = new JMenuItem("Main Menu");
        windowMenuItem1.setEnabled(false);
        windowMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spawnMainMenu();
            }
        });

        // 'About' Menu Components declaration
        aboutMenuItem1 = new JMenuItem("About Us");

        // Set JFrame layout to BorderLayout
        baseFrame.setLayout(new BorderLayout());

        // Adding Sub menu to menu items
        menuBar.add(fileMenu);
        fileMenu.add(fileMenuItem1);
        fileMenu.add(fileMenuItem2);
        fileMenu.add(new JSeparator());
        fileMenu.add(fileMenuItem3);

        menuBar.add(optionMenu);
        optionMenu.add(optionMenuItem4);
        optionMenu.add(new JSeparator());
        optionMenu.add(optionMenuItem1);
        optionMenu.add(optionMenuItem2);
        optionMenu.add(optionMenuItem3);

        menuBar.add(windowMenu);
        windowMenu.add(newWindowMenu);
        newWindowMenu.add(windowMenuItem1);
        windowMenu.add(new JSeparator());
        windowMenu.add(windowCheckBoxMenuItem1);
        windowMenu.add(windowMenuItem2);
        windowMenu.add(windowMenuItem3);

//        menuBar.add(aboutMenu);
//        aboutMenu.add(aboutMenuItem1);

        menuBar.add(helpMenu);
        aboutMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboutDialog(baseFrame);
            }
        });
        helpMenu.add(aboutMenuItem1);

        baseFrame.setJMenuBar(menuBar);


        desktop = new JDesktopPane();
        desktop.setBackground(new Color(44, 102, 188));
        baseFrame.add(desktop, BorderLayout.CENTER);

        // create the status bar panel
        statusBar = new JPanel();
        statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        // set itself to the bottom of the page
        baseFrame.add(statusBar, BorderLayout.SOUTH);
        statusBar.setPreferredSize(new Dimension(baseFrame.getWidth(), 24));

        // Status bar
        statusBar.setLayout(new GridBagLayout());
        statusLabel = new JLabel("You want to use GridBagConsraints#anchor to define the position within the cell that you want to align the component to.");
        statusLabel.setMinimumSize(new Dimension(128, statusBar.getHeight()));

        // Adding JLabel to the left of statusBar
        statusBar.add(statusLabel, new GBCBuilder(GridBagConstraints.BOTH, 0.3, 0, 0, new Insets(0, 10, 0, 0)).setAnchor(GridBagConstraints.WEST));

        // Adding JPanel next to the JLabel as a filler
        statusBar.add(new JPanel(), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 1, 0).getGBC());

        // Adding JProgressBar to the right of statusBar
        progressBar = new JProgressBar();
        progressBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        progressBar.setIndeterminate(true);
        statusBar.add(progressBar, new GBCBuilder(GridBagConstraints.NONE, 0.2, 2, 0, new Insets(0, 10, 0, 10)).setAnchor(GridBagConstraints.EAST));


        // Setting Window size and boilerplate code
        baseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainMenu = spawnMainMenu();
        baseFrame.setVisible(true);
    }

    private OperationWindow spawnMainMenu() {
        OperationWindow oper = new OperationWindow();
        Dimension desktopSize = desktop.getSize();
        Dimension jInternalFrameSize = oper.getFrame().getSize();
        oper.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        oper.getFrame().setVisible(true);
        desktop.add(oper.getFrame());
        oper.getFrame().moveToFront();
        return oper;
    }

    private PreferenceWindow spawnPreference() {
        PreferenceWindow pref = new PreferenceWindow();
        Dimension desktopSize = desktop.getSize();
        Dimension jInternalFrameSize = pref.getFrame().getSize();
        pref.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        pref.getFrame().setVisible(true);
        desktop.add(pref.getFrame());
        pref.getFrame().moveToFront();
        return pref;
    }

//    public void macResetMenuBar() {
//        try {
//            String os = System.getProperty("os.name").toLowerCase();
//            if (os.contains("mac")) {
////                System.setProperty("apple.laf.useScreenMenuBar", "false");
////                baseFrame.setJMenuBar(null);
//                Application application = Application.getApplication();
//                application.setDefaultMenuBar(null);
//            }
//
//        } catch (Exception ignored) {
//
//        }
//    }

    public static void main(String[] args) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("mac")) {
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                System.setProperty("apple.awt.application.name", "Laew Tae Hong Management");
                System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Laew Tae Hong Management");
            }
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (Exception ignored) {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            }

        } catch (Exception ignored) {

        }
        new BaseWindow(); //start your application
    }

    class CustomComponentListener implements ComponentListener {
        public void componentResized(ComponentEvent e) {
            try {
                if ((mainMenu != null) && (autoCenterMainMenu) && (!mainMenu.getFrame().isIcon())) {
                    Dimension desktopSize = desktop.getSize();
                    Dimension jInternalFrameSize = mainMenu.getFrame().getSize();
                    mainMenu.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                            (desktopSize.height - jInternalFrameSize.height) / 2);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        public void componentMoved(ComponentEvent e) {
        }

        public void componentShown(ComponentEvent e) {
        }

        public void componentHidden(ComponentEvent e) {
        }
    }
}

