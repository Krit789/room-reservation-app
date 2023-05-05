package net.itkmitl.room.portal.admin.components.OOBE;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.itkmitl.room.portal.components.GBCBuilder;

public class FinishView extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -629579596973815711L;
	private JLabel title, description, firstPasswordDisplay;
    private JPanel titlePanel, firstAccountPanel;
    public JTextField firstPassWordField;
    public FinishView(){
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(15, 10 ,0 ,15));
        titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        title = new JLabel("Laew Tae Hong Administration Setup");
        title.setFont(new Font("Sanserif", Font.BOLD, 18));
        description = new JLabel("<html><p style=\"width:225px\">" +
               "Please keep this key safe because you're not going to see it again. To enter Laew Tae Hong Administration login with given account and click on option in the menu and then \"Switch to Admin Mode\"." + "</p></html>");
        description.setFont(new Font("SansSerif", Font.PLAIN, 12));

        titlePanel.add(title);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 25)));
        titlePanel.add(description);
        this.add(titlePanel, BorderLayout.NORTH);

        firstAccountPanel = new JPanel();
        firstAccountPanel.setLayout(new GridBagLayout());
        firstPasswordDisplay = new JLabel("<html><p><b>Important</b></p><p style=\"width:225px\">This is your default administration password,<br> E-Mail is <code>admin@lth.org</code>.</p></html>");
        firstPasswordDisplay.setFont(new Font("SansSerif", Font.PLAIN, 12));

        firstPassWordField = new JTextField("");
        firstPassWordField.setEditable(false);
        firstPassWordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        firstAccountPanel.add(firstPasswordDisplay, new GBCBuilder(GridBagConstraints.NORTH, 1, 0, 0).setAnchor(GridBagConstraints.NORTHWEST));
        firstAccountPanel.add(firstPassWordField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 1, new Insets(10, 0,0 ,0)).getGBC());

        this.add(firstAccountPanel, BorderLayout.CENTER);

    }
}
