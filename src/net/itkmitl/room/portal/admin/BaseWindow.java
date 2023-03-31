package net.itkmitl.room.portal.admin;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.itkmitl.room.libs.peeranat.util.FewFile;
import net.itkmitl.room.portal.admin.components.OperationWindow;

public class BaseWindow {
    private JFrame baseFrame;
    private JPanel menuPanel;
    private JMenuBar menuBar;
    private JMenu fileMenu, windowMenu, optionMenu, aboutMenu, helpMenu;
    private JMenuItem fileMenuItem1, fileMenuItem2, fileMenuItem3;
    private JMenuItem optionMenuItem1, optionMenuItem2, optionMenuItem3, optionMenuItem4;
    private JMenu newWindowMenu;
    private JMenuItem windowMenuItem1, windowMenuItem2, windowMenuItem3;
    private JMenuItem aboutMenuItem1;
    private JDesktopPane desktop;

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

        menuBar.add(aboutMenu);
        aboutMenu.add(aboutMenuItem1);

        menuBar.add(helpMenu);

        baseFrame.setJMenuBar(menuBar);


        desktop = new JDesktopPane();
        baseFrame.add(desktop, BorderLayout.CENTER);

        // create the status bar panel and shove it down the bottom of the frame
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        baseFrame.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(baseFrame.getWidth(), 24));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        JLabel statusLabel = new JLabel("Status status");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(statusLabel);

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
