package net.itkmitl.room.portal.admin.components.OOBE;

import net.itkmitl.room.libs.peeranat.util.FewFile;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;

public class OOBEView {
    public static String[] PANEL = {"Panel1", "Panel2", "Panel3", "Panel4"};
    private final JFrame frame;
    private final JPanel buttonPanel;
    public JPanel contentPanel;
    public JButton backButton, nextButton, cancelButton;
    public DatabaseSetupPanelView dbsp;
    public DatabaseTestView dbt;
    public FinishView fn;

    public OOBEView() {
        frame = new JFrame("LTH Setup");
        ArrayList<Image> multiIcon = new ArrayList<>();
        multiIcon.add(FewFile.getImage("icons/icon-208px.png").getImage());
        multiIcon.add(FewFile.getImage("icons/icon-128px.png").getImage());
        multiIcon.add(FewFile.getImage("icons/icon-64px.png").getImage());
        multiIcon.add(FewFile.getImage("icons/icon-56px.png").getImage());
        multiIcon.add(FewFile.getImage("icons/icon-48px.png").getImage());
        multiIcon.add(FewFile.getImage("icons/icon-40px.png").getImage());
        multiIcon.add(FewFile.getImage("icons/icon-20px.png").getImage());
        multiIcon.add(FewFile.getImage("icons/icon-16px.png").getImage());
        frame.add(new JLabel(FewFile.getImage("account/banner/banner1-50.png")), BorderLayout.NORTH);
        frame.setIconImages(multiIcon);

        frame.setLayout(new BorderLayout());
        frame.add(new JLabel(FewFile.getImage("account/banner/banner2-10.png")), BorderLayout.WEST);

        contentPanel = new JPanel();
        contentPanel.setLayout(new CardLayout());
        contentPanel.add(new WelcomePanelView(), PANEL[0]);
        dbsp = new DatabaseSetupPanelView();
        contentPanel.add(dbsp, PANEL[1]);
        dbt = new DatabaseTestView();
        contentPanel.add(dbt, PANEL[2]);
        fn = new FinishView();
        contentPanel.add(fn, PANEL[3]);

        backButton = new JButton("Back");
        nextButton = new JButton("Next");
        cancelButton = new JButton("Cancel");

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(cancelButton);
        buttonPanel.setBorder(new MatteBorder(1, 0, 0, 0, Color.lightGray));
        frame.add(contentPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
