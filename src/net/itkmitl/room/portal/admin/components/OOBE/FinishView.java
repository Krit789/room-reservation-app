package net.itkmitl.room.portal.admin.components.OOBE;

import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.UUID;

public class FinishView extends JPanel {
    private JLabel title, description, firstPasswordDisplay;
    private JPanel titlePanel, firstAccountPanel;
    public JTextField firstPassWordField;
    public FinishView(){
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(15, 10 ,0 ,15));
        titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        title = new JLabel("Laew Tae Hong Administration Setup");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        description = new JLabel("<html><p style=\"width:225px\">" +
               "Please keep this key safe because you're not going to see it again. To enter Laew Tae Hong Administration click on Laew Tae Hong logo on the login page 3 times." + "</p></html>");
        description.setFont(new Font("SansSerif", Font.PLAIN, 12));

        titlePanel.add(title);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 25)));
        titlePanel.add(description);
        this.add(titlePanel, BorderLayout.NORTH);

        firstAccountPanel = new JPanel();
        firstAccountPanel.setLayout(new GridBagLayout());
        firstPasswordDisplay = new JLabel("<html><p><b>Important</b></p><p style=\"width:225px\">This is your default administration password,<br> E-Mail is blank.</p></html>");
        firstPasswordDisplay.setFont(new Font("SansSerif", Font.PLAIN, 12));

        firstPassWordField = new JTextField("");
        firstPassWordField.setEditable(false);
        firstPassWordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        firstAccountPanel.add(firstPasswordDisplay, new GBCBuilder(GridBagConstraints.NORTH, 1, 0, 0).setAnchor(GridBagConstraints.NORTHWEST));
        firstAccountPanel.add(firstPassWordField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 1, new Insets(10, 0,0 ,0)).getGBC());

        this.add(firstAccountPanel, BorderLayout.CENTER);

    }
}
