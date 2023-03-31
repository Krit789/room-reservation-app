package net.itkmitl.room.portal.admin;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.itkmitl.room.libs.peeranat.util.FewFile;
import net.itkmitl.room.portal.admin.components.OperationWindow;

public class BaseWindow {
    private final JFrame baseFrame;
    private final JPanel statusBar;
    private final JMenuBar menuBar;
    private final JMenu fileMenu, windowMenu, optionMenu, aboutMenu, helpMenu;
    private final JMenuItem fileMenuItem1, fileMenuItem2, fileMenuItem3;
    private final JMenuItem optionMenuItem1, optionMenuItem2, optionMenuItem3, optionMenuItem4;
    private final JMenu newWindowMenu;
    private final JMenuItem windowMenuItem1, windowMenuItem2, windowMenuItem3;
    private final JMenuItem aboutMenuItem1;
    private final JDesktopPane desktop;
    private final JProgressBar progressBar;
    private final JLabel statusLabel;
    private GridBagConstraints gbc;

    public BaseWindow() {
        baseFrame = new JFrame("Laew Tae Hong Management");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        baseFrame.setIconImage(FewFile.getImage("icon.png"));
        baseFrame.setSize(screenSize);
        baseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        baseFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

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
        optionMenuItem4 = new JMenuItem("Switch to User Mode");

        windowMenuItem2 = new JMenuItem("Cascade Window");
        windowMenuItem3 = new JMenuItem("Close all Window");
        newWindowMenu = new JMenu("New");
        windowMenuItem1 = new JMenuItem("Operation Window");
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
        windowMenu.add(windowMenuItem2);
        windowMenu.add(windowMenuItem3);

//        menuBar.add(aboutMenu);
//        aboutMenu.add(aboutMenuItem1);

        menuBar.add(helpMenu);
        helpMenu.add(aboutMenuItem1);

        baseFrame.setJMenuBar(menuBar);


        desktop = new JDesktopPane();
        baseFrame.add(desktop, BorderLayout.CENTER);

        // create the status bar panel
        statusBar = new JPanel();
        statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        // set itself to the bottom of the page
        baseFrame.add(statusBar, BorderLayout.SOUTH);
        statusBar.setPreferredSize(new Dimension(baseFrame.getWidth(), 24));

        statusBar.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.3;
        gbc.insets = new Insets(0,10,0,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        statusLabel = new JLabel("You want to use GridBagConsraints#anchor to define the position within the cell that you want to align the component to.");
        statusBar.add(statusLabel, gbc);
//        Dimension minSize = new Dimension(100, 24);
//        Dimension prefSize = new Dimension(1600, 24);
//        Dimension maxSize = new Dimension(Short.MAX_VALUE, 24);
//        statusBar.add(new Box.Filler(minSize, prefSize, maxSize));

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
//        gbc.insets = new Insets(0,10,0,0);
        gbc.gridx = 1;
        gbc.gridy = 0;
        statusBar.add(new JPanel(), gbc);


        gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.2;
        gbc.insets = new Insets(0,10,0,10);
        gbc.gridx = 2;
        gbc.gridy = 0;
        progressBar = new JProgressBar();
        progressBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        progressBar.setIndeterminate(true);
        statusBar.add(progressBar, gbc);


        // Setting Window size and boilerplate code
        baseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        baseFrame.setVisible(true);
        spawnMainMenu();


    }

    private void spawnMainMenu() {
        OperationWindow oper = new OperationWindow();
        Dimension desktopSize = desktop.getSize();
        Dimension jInternalFrameSize = oper.getFrame().getSize();
        oper.getFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        oper.getFrame().setVisible(true);
        desktop.add(oper.getFrame());
        oper.getFrame().moveToFront();
    }

    public static void main(String[] args) {
        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.application.name", "Laew Tae Hong Management");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Laew Tae Hong Management");
        } catch (Exception ignored) {
        }

        new BaseWindow();//start your application
    }

}
