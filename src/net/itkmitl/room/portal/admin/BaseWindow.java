package net.itkmitl.room.portal.admin;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.formdev.flatlaf.FlatIntelliJLaf;
import net.itkmitl.room.GUIStarter;
import net.itkmitl.room.MacConfig;
import net.itkmitl.room.portal.admin.components.*;
import net.itkmitl.room.portal.admin.controllers.OperationWindowController;
import net.itkmitl.room.portal.admin.controllers.PreferenceWindowController;
import net.itkmitl.room.portal.admin.views.OperationWindowView;
import net.itkmitl.room.portal.admin.views.PreferenceWindowView;

public class BaseWindow extends ComponentAdapter implements ActionListener, InternalFrameListener {
    private final OperationWindowView mainMenu;
    private final JFrame baseFrame;
    private final JPanel statusBar;
    private final JMenuBar menuBar;
    public static JMenu windowMenu;
    private final JMenu fileMenu, optionMenu, helpMenu;
    private final JMenuItem fileMenuItem1, fileMenuItem2, fileMenuItem3;
    private final JMenuItem optionMenuItem1, optionMenuItem2, optionMenuItem3, optionMenuItem4;
    private final JMenuItem windowMenuItem2, windowMenuItem3;
    private final JCheckBoxMenuItem windowCheckBoxMenuItem1;
    private final JMenuItem aboutMenuItem1;
    private static JDesktopPane desktop;
    private final JProgressBar progressBar;
    private final JLabel statusLabel;
    private ArrayList<Image> multiIcon;
    public static HashMap<JInternalFrame, JMenuItem> windowList = new HashMap<>();
//    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private boolean autoCenterMainMenu = true;

    public BaseWindow() {
        baseFrame = new JFrame("Laew Tae Hong Management");
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
        baseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        baseFrame.setMinimumSize(new Dimension(640, 480));
        baseFrame.setSize(1280, 720);
        baseFrame.addComponentListener(this);
        baseFrame.setExtendedState(baseFrame.getExtendedState() | baseFrame.MAXIMIZED_BOTH);

        // Menu Components declaration
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        optionMenu = new JMenu("Options");
        BaseWindow.windowMenu = new JMenu("Window");
        helpMenu = new JMenu("Help");

        // 'File' Menu Components declaration
        fileMenuItem1 = new JMenuItem("Status");
        fileMenuItem2 = new JMenuItem("View Logs");
        fileMenuItem3 = new JMenuItem("Exit");
        fileMenuItem3.addActionListener(this);
        // 'Options' Menu Components declaration
        optionMenuItem1 = new JMenuItem("Connect");
        optionMenuItem2 = new JMenuItem("Disconnect");
        optionMenuItem3 = new JMenuItem("Settings");
        optionMenuItem3.setIcon(new ImageIcon("resource/icons/settingsicon-16px.png"));
        optionMenuItem3.addActionListener(this);
        optionMenuItem4 = new JMenuItem("Switch to User Mode");
        optionMenuItem4.addActionListener(this);

        windowCheckBoxMenuItem1 = new JCheckBoxMenuItem("Auto Center Main Menu");
        windowCheckBoxMenuItem1.setState(autoCenterMainMenu);
        windowCheckBoxMenuItem1.setToolTipText("Automatically center Main Menu when Main Window is resized");
        windowCheckBoxMenuItem1.addActionListener(this);
        windowMenuItem2 = new JMenuItem("Cascade Window");
        windowMenuItem2.setIcon(new ImageIcon("resource/icons/Cascade-16px.png"));
        windowMenuItem3 = new JMenuItem("Close all Window");
        windowMenuItem3.setIcon(new ImageIcon("resource/icons/CloseAll-16px.png"));
        windowMenuItem3.addActionListener(this);

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
        windowMenu.add(windowCheckBoxMenuItem1);
        windowMenu.add(windowMenuItem2);
        windowMenuItem2.addActionListener(this);
        windowMenu.add(windowMenuItem3);
        windowMenu.add(new JSeparator());

        menuBar.add(helpMenu);
        aboutMenuItem1.addActionListener(this);
        helpMenu.add(aboutMenuItem1);

        baseFrame.setJMenuBar(menuBar);


        desktop = new JDesktopPane();
        desktop.setBackground(new Color(51, 126, 185));
        baseFrame.add(desktop, BorderLayout.CENTER);

        // create the status bar panel
        statusBar = new JPanel();
        statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        // set itself to the bottom of the page
        baseFrame.add(statusBar, BorderLayout.SOUTH);
        statusBar.setPreferredSize(new Dimension(baseFrame.getWidth(), 24));

        // Status bar
        statusBar.setLayout(new GridBagLayout());
        statusLabel = new JLabel("Ready.");
        statusLabel.setMinimumSize(new Dimension(128, statusBar.getHeight()));

        // Adding JLabel to the left of statusBar
        statusBar.add(statusLabel, new GBCBuilder(GridBagConstraints.BOTH, 0.3, 0, 0, new Insets(0, 10, 0, 0)).setAnchor(GridBagConstraints.WEST));

        // Adding JPanel next to the JLabel as a filler
        statusBar.add(new JPanel(), new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.5, 1, 0).getGBC());

        // Adding JProgressBar to the right of statusBar
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(false);
        statusBar.add(progressBar, new GBCBuilder(GridBagConstraints.NONE, 0.2, 2, 0, new Insets(0, 10, 0, 10)).setAnchor(GridBagConstraints.EAST));


        // Setting Window size and boilerplate code
        baseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainMenu = spawnMainMenu();
        baseFrame.setVisible(true);
    }

    private OperationWindowView spawnMainMenu() {
        OperationWindowController oper = new OperationWindowController();
        OperationWindowView view = oper.getView();
        Dimension desktopSize = desktop.getSize();
        Dimension jInternalFrameSize = view.getFrame().getSize();
        view.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        view.getFrame().addInternalFrameListener(this);
        view.getFrame().show();
        view.getFrame().setVisible(true);
        desktop.add(view.getFrame());
        view.getFrame().moveToFront();
        return view;
    }

    private PreferenceWindowView spawnPreference() {
        PreferenceWindowController pref = new PreferenceWindowController();
        PreferenceWindowView view = pref.getView();

        Dimension desktopSize = desktop.getSize();
        Dimension jInternalFrameSize = view.getFrame().getSize();
        view.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        view.getFrame().addInternalFrameListener(this);
        view.getFrame().show();
        view.getFrame().setVisible(true);
        desktop.add(view.getFrame());
        view.getFrame().moveToFront();

        return view;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(aboutMenuItem1)) {
            new AboutDialog(baseFrame);
        } else if (e.getSource().equals(fileMenuItem3)) {
            baseFrame.dispose();
            System.exit(0);
        } else if (e.getSource().equals(optionMenuItem3)) {
            spawnPreference();
        } else if (e.getSource().equals(optionMenuItem4)) {
            baseFrame.dispose();
            String[] arguments = new String[]{""};
            GUIStarter.main(arguments);
        } else if (e.getSource().equals(windowCheckBoxMenuItem1)) {
            windowCheckBoxMenuItem1.setState(!autoCenterMainMenu);
                autoCenterMainMenu = !autoCenterMainMenu;
                if (autoCenterMainMenu) {
                    Dimension desktopSize = desktop.getSize();
                    Dimension jInternalFrameSize = mainMenu.getFrame().getSize();
                mainMenu.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                        (desktopSize.height - jInternalFrameSize.height) / 2);
            }
        } else if (e.getSource().equals(windowMenuItem3)){
            HashMap<JInternalFrame, JMenuItem> removalList = new HashMap<>();
            for (JInternalFrame i : windowList.keySet()) {
                if (!i.getTitle().equals("Main Menu")){
                    removalList.put(i, windowList.get(i));
                }
            }

            for(Map.Entry<JInternalFrame, JMenuItem> entry : removalList.entrySet()) {
                JInternalFrame key = entry.getKey();
                JMenuItem value = entry.getValue();
                windowList.remove(key);
                windowMenu.remove(value);
                try {
                    key.setClosed(true);
                } catch (Exception ignored){}
            }
//            for (JInternalFrame i: desktop.getAllFrames()){
//                windowList.remove(i);
//                windowMenu.remove(value);
//                desktop.remove();
//            }
        }
        else if (e.getSource().equals(windowMenuItem2)) {
            for (JInternalFrame i : desktop.getAllFrames()
            ) {
                System.out.println(i.getTitle());
            }
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        try {
            if ((mainMenu != null) && (autoCenterMainMenu) && (!mainMenu.getFrame().isIcon())) {
                Dimension desktopSize = desktop.getSize();
                Dimension jInternalFrameSize = mainMenu.getFrame().getSize();
                mainMenu.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                        (desktopSize.height - jInternalFrameSize.height) / 2);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void internalFrameOpened(InternalFrameEvent e) {
        JMenuItem newItem = new JMenuItem(e.getInternalFrame().getTitle());
        windowList.put(e.getInternalFrame(), newItem);
        newItem.setIcon(e.getInternalFrame().getFrameIcon());
        windowMenu.add(newItem);
    }

    public void internalFrameClosing(InternalFrameEvent e) {
    }

    public void internalFrameClosed(InternalFrameEvent e) {
        windowMenu.remove(windowList.get(e.getInternalFrame()));
        windowList.remove(e.getInternalFrame());
    }

    public void internalFrameIconified(InternalFrameEvent e) {
    }

    public void internalFrameDeiconified(InternalFrameEvent e) {
    }

    public void internalFrameActivated(InternalFrameEvent e) {
    }

    public void internalFrameDeactivated(InternalFrameEvent e) {
    }

    public static JDesktopPane getDesktop(){
        return desktop;
    }

    public static void main(String[] args) {
        try {
            MacConfig.menuBar("Laew Tae Hong Management");
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (Exception ignored) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            }
        } catch (Exception ignored) {}
        new BaseWindow(); //start the application
    }



}



