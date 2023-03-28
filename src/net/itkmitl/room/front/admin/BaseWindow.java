package net.itkmitl.room.front.admin;

import javax.swing.*;
import java.awt.*;

public class BaseWindow {
    private JFrame baseFrame;
    private JPanel menuPanel;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu optionMenu;

    private JMenu aboutMenu;
    private JMenuItem fileMenuItem1;
    private JMenuItem fileMenuItem2;
    private JMenuItem fileMenuItem3;
    private JMenuItem optionMenuItem1;
    private JMenuItem optionMenuItem2;
    private JMenuItem optionMenuItem3;
    private JMenuItem aboutMenuItem1;

    public BaseWindow(){
        baseFrame = new JFrame("Laew Tae Hong");
        baseFrame.setIconImage(new ImageIcon("resource/icon.png").getImage());

        menuPanel = new JPanel();
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        optionMenu = new JMenu("Options");
        aboutMenu = new JMenu("About");
        fileMenuItem1 = new JMenuItem("Status");
        fileMenuItem2 = new JMenuItem("View Logs");
        fileMenuItem3 = new JMenuItem("Exit");

        optionMenuItem1 = new JMenuItem("Connect");
        optionMenuItem2 = new JMenuItem("Disconnect");
        optionMenuItem3 = new JMenuItem("Settings");

        aboutMenuItem1 = new JMenuItem("About Us");

        baseFrame.setLayout(new BorderLayout());


        menuBar.add(fileMenu);
        fileMenu.add(fileMenuItem1);
        fileMenu.add(fileMenuItem2);
        fileMenu.add(fileMenuItem3);

        menuBar.add(optionMenu);
        optionMenu.add(optionMenuItem1);
        optionMenu.add(optionMenuItem2);
        optionMenu.add(optionMenuItem3);

        menuBar.add(aboutMenu);
        aboutMenu.add(aboutMenuItem1);

        baseFrame.setJMenuBar(menuBar);

        baseFrame.setSize(1280, 720);
        baseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        baseFrame.setVisible(true);

    }

    public static void main(String[] args) {
        new BaseWindow();
    }
}
