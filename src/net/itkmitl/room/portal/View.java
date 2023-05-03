package net.itkmitl.room.portal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.*;

import net.itkmitl.room.portal.account.components.OutPane;

public abstract class View extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8075338715312313006L;
	public OutPane outerPane;
    public JMenuBar menuBar;
    public JMenu fileMenu, windowMenu, optionMenu, helpMenu;
    public JMenuItem fileMenuItem1, fileMenuItem2, fileMenuItem3;
    public JMenuItem optionMenuItem1, helpMenuItem1;
//    public JMenuItem windowMenuItem1, windowMenuItem2, windowMenuItem3;
    public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private ArrayList<Image> multiIcon;

    public View() {
        this.initializeOuterPane();
        this.initializeMenuBar();
        this.initializeFrame();
        this.initialize();
    }

    protected void initializeMenuBar() {
        //----------------------- Menubar -----------------------
        // Menu Components declaration
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        optionMenu = new JMenu("Options");
        windowMenu = new JMenu("Window");
        helpMenu = new JMenu("Help");

        // 'File' Menu Components declaration
        fileMenuItem1 = new JMenuItem("New");
        fileMenuItem2 = new JMenuItem("View Rooms");
        fileMenuItem3 = new JMenuItem("Exit");
        fileMenuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // 'Options' Menu Components declaration
        optionMenuItem1 = new JMenuItem("Switch to Admin Mode");

        // 'Window' Menu Components declaration
//        windowMenuItem1 = new JMenuItem("1");
//        windowMenuItem2 = new JMenuItem("2");
//        windowMenuItem3 = new JMenuItem("3");

        // 'Help' Menu Components declaration
        helpMenuItem1 = new JMenuItem("About Us");

        // Adding Sub menu to menu items
        menuBar.add(fileMenu);
        fileMenu.add(fileMenuItem1);
        fileMenu.add(fileMenuItem2);
        fileMenu.add(new JSeparator());
        fileMenu.add(fileMenuItem3);

        menuBar.add(optionMenu);
        optionMenu.add(optionMenuItem1);

//        menuBar.add(windowMenu);
//        windowMenu.add(windowMenuItem1);
//        windowMenu.add(windowMenuItem2);
//        windowMenu.add(windowMenuItem3);

        menuBar.add(helpMenu);
        helpMenu.add(helpMenuItem1);
    }

    protected void initializeOuterPane() {
        outerPane = new OutPane();
        outerPane.setPreferredSize(screenSize);
        outerPane.setBackground(new Color(15, 27, 47));

        outerPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Get the current size of the panel
                Dimension size = outerPane.getSize();
                int width = size.width;
                int height = size.height;

                // Calculate the new padding based on the current size
                int paddingSize = Math.min(width, height) / 5;
                Insets newInsets = new Insets(paddingSize, paddingSize, paddingSize, paddingSize);

                // Repaint the panel to update the layout
                outerPane.revalidate();
                outerPane.repaint();
            }
        });
    }

    protected void initializeFrame() {
        this.setTitle("Laew Tae Hong");
        multiIcon = new ArrayList<>();
        multiIcon.add(new ImageIcon("resource/icons/icon-208px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-128px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-64px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-56px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-48px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-40px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-20px.png").getImage());
        multiIcon.add(new ImageIcon("resource/icons/icon-16px.png").getImage());
        this.setIconImages(multiIcon);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1280, 720));
        this.setSize(screenSize);
        this.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
        this.setJMenuBar(menuBar);
        this.setBackground(Color.white);

        this.setContentPane(outerPane);
        this.setVisible(true);
    }

    protected abstract void initialize();
}