package net.itkmitl.room.portal;

import net.itkmitl.room.libs.peeranat.util.FewFile;
import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.store.AppStore;
import net.itkmitl.room.portal.account.components.OutPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.ArrayList;

public abstract class View extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = -8075338715312313006L;
    public OutPane outerPane;
    public JMenuBar menuBar;
    public JMenu fileMenu, optionMenu, helpMenu;
    public JMenuItem fileMenuItem3;
    public JMenuItem optionMenuItem1, helpMenuItem1;
    public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final AppStore store = AppStore.getAppStore();
    private ArrayList<Image> multiIcon;

    public View() {
        this.initializeOuterPane();
        this.initializeMenuBar();
        initializeFont();
        this.initializeFrame();
        this.initialize();
        this.setVisible(true);
    }

    protected void initializeFont() {
        try {
            Font cousine = Font.createFont(Font.TRUETYPE_FONT, FewFile.getFile("/fonts/Cousine-Regular.ttf"));
            cousine = cousine.deriveFont(16f);

            Font cousineBold = Font.createFont(Font.TRUETYPE_FONT, FewFile.getFile("/fonts/Cousine-Bold.ttf"));
            cousineBold = cousineBold.deriveFont(16f);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(cousine);
            ge.registerFont(cousineBold);
        } catch (Exception e) {
            System.out.println("UIConfig.java: " + e.getMessage());
        }
    }

    protected void initializeMenuBar() {
        //----------------------- Menubar -----------------------
        // Menu Components declaration
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        helpMenu = new JMenu("Help");
        optionMenu = new JMenu("Option");

        // 'File' Menu Components declaration
        fileMenuItem3 = new JMenuItem("Exit");
        fileMenuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        // 'Options' Menu Components declaration
        optionMenuItem1 = new JMenuItem("Switch to Admin Mode");

        // 'Help' Menu Components declaration
        helpMenuItem1 = new JMenuItem("About Us");

        // Adding Sub menu to menu items
        menuBar.add(fileMenu);
        fileMenu.add(fileMenuItem3);

        if ((store.select("user") != null) && (((User) store.select("user")).getRole().getLevel() > 10)) {
            menuBar.add(optionMenu);
            optionMenu.add(optionMenuItem1);
        }

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
                repaintOuterPane();
            }
        });
    }

    protected void repaintOuterPane() {
        Dimension size = outerPane.getSize();
        int width = size.width;
        int height = size.height;

        // Calculate the new padding based on the current size
        int paddingSize = Math.min(width, height) / 5;
        new Insets(paddingSize, paddingSize, paddingSize, paddingSize);

        // Repaint the panel to update the layout
        outerPane.revalidate();
        outerPane.repaint();
    }

    protected void initializeFrame() {
        this.setTitle("Laew Tae Hong");
        multiIcon = new ArrayList<>();
        multiIcon.add(FewFile.getImage("icons/icon-208px.png").getImage());
        multiIcon.add(FewFile.getImage("icons/icon-128px.png").getImage());
        multiIcon.add(FewFile.getImage("icons/icon-64px.png").getImage());
        multiIcon.add(FewFile.getImage("icons/icon-56px.png").getImage());
        multiIcon.add(FewFile.getImage("icons/icon-48px.png").getImage());
        multiIcon.add(FewFile.getImage("icons/icon-40px.png").getImage());
        multiIcon.add(FewFile.getImage("icons/icon-20px.png").getImage());
        multiIcon.add(FewFile.getImage("icons/icon-16px.png").getImage());
        this.setIconImages(multiIcon);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1280, 720));
        this.setSize(screenSize);
        this.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
        this.setJMenuBar(menuBar);
        this.setBackground(Color.white);
        this.setContentPane(outerPane);
    }

    protected abstract void initialize();
}