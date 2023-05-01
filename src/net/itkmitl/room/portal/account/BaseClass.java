package net.itkmitl.room.portal.account;

import net.itkmitl.room.libs.peeranat.util.*;
import net.itkmitl.room.portal.admin.BaseWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public abstract class BaseClass implements ActionListener {
    protected JFrame baseFrame;
    protected OutPane outerPane;
    protected JButton loginButton, registerButton;
    protected JPanel innerPane, floatingBox, buttonBox;
    protected ImageIcon img_itbuilding;
    protected JMenuBar menuBar;
    protected JMenu fileMenu, windowMenu, optionMenu, helpMenu;
    protected JMenuItem fileMenuItem1, fileMenuItem2, fileMenuItem3;
    protected JMenuItem optionMenuItem1, helpMenuItem1;

    protected JMenuItem windowMenuItem1, windowMenuItem2, windowMenuItem3;
    protected GridBagConstraints c;
    protected ArrayList<Image> multiIcon;

    protected Insets insets = new Insets(400, 600, 400, 600), newInsets;

    protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    private void menuBarInitialize() {
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
        fileMenuItem3.addActionListener(e -> System.exit(0));

        // 'Options' Menu Components declaration
        optionMenuItem1 = new JMenuItem("Switch to Admin Mode");
        optionMenuItem1.addActionListener(e -> {
            baseFrame.dispose();
            String[] arguments = new String[]{""};
            BaseWindow.main(arguments);
        });

        // 'Window' Menu Components declaration
        windowMenuItem1 = new JMenuItem("1");
        windowMenuItem2 = new JMenuItem("2");
        windowMenuItem3 = new JMenuItem("3");


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

        menuBar.add(windowMenu);
        windowMenu.add(windowMenuItem1);
        windowMenu.add(windowMenuItem2);
        windowMenu.add(windowMenuItem3);

        menuBar.add(helpMenu);
        helpMenu.add(helpMenuItem1);
    }

    private void outerPaneInitialize() {

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
                newInsets = new Insets(paddingSize, paddingSize, paddingSize, paddingSize);


                // Repaint the panel to update the layout
                outerPane.revalidate();
                outerPane.repaint();
            }
        });


    }

    private void innerPaneInitialize() {
        innerPane = new JPanel(new BorderLayout());
//        insets = ;
        innerPane.setBorder(BorderFactory.createEmptyBorder(insets.top, insets.left, insets.bottom, insets.right));
        JLabel label = new JLabel("Content", SwingConstants.CENTER);
        innerPane.add(label, BorderLayout.CENTER);
        innerPane.setMaximumSize(new Dimension(400, 600));
//        innerPane.setPreferredSize(new Dimension(400, 600));
//        innerPane.setBackground(Color.WHITE);
//        Insets insets = new Insets(10, 10, 10, 10);
//        innerPane.setBorder(BorderFactory.createEmptyBorder());
//        JLabel labelNorth = new JLabel("North", SwingConstants.CENTER);
//        innerPane.add(labelNorth, BorderLayout.NORTH);
//        JLabel labelSouth = new JLabel("South", SwingConstants.CENTER);
//        innerPane.add(labelSouth, BorderLayout.SOUTH);
//        JLabel labelEast = new JLabel("East", SwingConstants.CENTER);
//        innerPane.add(labelEast, BorderLayout.EAST);
//        JLabel labelWest = new JLabel("West", SwingConstants.CENTER);
//        innerPane.add(labelWest, BorderLayout.WEST);
////        JLabel labelCenter = new JLabel("Center", SwingConstants.CENTER);
////        innerPane.add(labelCenter, BorderLayout.CENTER);
//        innerPane.setMaximumSize(new Dimension(100, 100));
        innerPane.setOpaque(false);
    }

    public BaseClass() {
        //JFrame and MenuBar Initialization
        menuBarInitialize();
        baseFrame = new JFrame();
        baseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        baseFrame.setMinimumSize(new Dimension(640, 480));
        baseFrame.setSize(screenSize);
        baseFrame.setExtendedState(baseFrame.getExtendedState() | Frame.MAXIMIZED_BOTH);
        baseFrame.setJMenuBar(menuBar);
        baseFrame.setMinimumSize(new Dimension(910, 480));

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

        floatingBox = new JPanel();
        floatingBox.setSize(new Dimension(500, 500));
        floatingBox.setLayout(new GridBagLayout());

        outerPaneInitialize();
        innerPaneInitialize();

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        img_itbuilding = new ImageIcon(FewFile.getImage("account/img/it_building.png"));

        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        buttonBox = new JPanel();
        buttonBox.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonBox.add(registerButton);
        buttonBox.add(loginButton);

        innerPane.add(floatingBox);

        outerPane.add(innerPane);

        baseFrame.setContentPane(outerPane);
        baseFrame.setVisible(true);

        //Add Building Image
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.4;
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 5;
        floatingBox.add(new JLabel("building goes here"), c);//Image wont go in
        c.gridheight = 1;

        // Update the padding of the nested panel

    }

    public static void main(String[] args) {
//        MacConfig.menuBar();
//        try {
//            MacConfig.menuBar("Laew Tae Hong Management");
//            try {
//                UIManager.setLookAndFeel(new FlatIntelliJLaf());
//            } catch (Exception ignored) {
//                UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//            }
//        } catch (Exception ignored) {
//        }
//        new BaseClass();
//    }
    }
}