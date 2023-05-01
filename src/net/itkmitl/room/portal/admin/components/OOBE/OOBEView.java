package net.itkmitl.room.portal.admin.components.OOBE;

import javax.swing.*;
import java.awt.*;

public class OOBEView {
    private JFrame frame;
    private JPanel buttonPanel;
    public JPanel contentPanel;
    public JButton backButton, nextButton, cancelButton;
    public static String PANEL1 = "Panel1";
    public static String PANEL2 = "Panel2";

    public OOBEView(){
        frame = new JFrame("LTH Setup");
        frame.setLayout(new BorderLayout());

        contentPanel = new JPanel();
        contentPanel.setLayout(new CardLayout());
        contentPanel.add(new WelcomePanelView(), PANEL1);
        contentPanel.add(new WelcomePanel2View(), PANEL2);

        backButton = new JButton("Back");
        nextButton = new JButton("Next");
        cancelButton = new JButton("Cancel");

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(cancelButton);
        frame.add(contentPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    public JFrame getFrame(){
        return frame;
    }
}
