package net.itkmitl.room.portal.admin.components.OOBE;

import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel2View extends JPanel {
    private JLabel title, description;

    public WelcomePanel2View() {
        this.setLayout(new GridBagLayout());
        title = new JLabel("Welcome to Laew Tae Hong V2");
        description = new JLabel("<html><p style=\"width:100px\">" +
                "Lorem ipsum dolor sit amet,  commodo leo. Aenean sed tellus nec risus ultricies viverra eget eu massa. Pellentesque fringilla lectus vitae sollicitudin suscipit. Maecenas porttitor fringilla ante eu mattis." + "</p></html>");
        this.add(title, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0.1,0,0, new Insets(10, 10, 10, 10)).getGBC());
        this.add(description, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0.9,0,1, new Insets(10, 10, 10, 10)).getGBC());
    }
}
