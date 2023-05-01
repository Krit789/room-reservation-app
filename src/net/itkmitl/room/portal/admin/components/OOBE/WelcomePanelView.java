package net.itkmitl.room.portal.admin.components.OOBE;

import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class WelcomePanelView extends JPanel {
    private JLabel title, description;

    public WelcomePanelView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(15, 10 ,0 ,15));
        title = new JLabel("Welcome to Laew Tae Hong");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        description = new JLabel("<html><p style=\"width:225px\">" +
                "It looks like this is the first time you use Laew Tae Hong, let's begin our setup process and get going!" + "</p><br><br><p><u>Laew Tae Hong requires</u></p><ul><li>MySQL Database with read and write access</li><li>Local disk with read and write access</li></ul></html>");
        description.setFont(new Font("SansSerif", Font.PLAIN, 12));

        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 25)));
        this.add(description);
    }
}
