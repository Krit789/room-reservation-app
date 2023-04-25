package net.itkmitl.room.portal.admin.views;

import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DataSearchView {
    private final JPanel searchPanel, buttonPanel, titlePanel;
    public final ButtonGroup searchType;
    public final JRadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5;
    public final JTextField searchField;
    private final JLabel searchLabel, pageTitle;
    public final JButton searchButton;
    private final JInternalFrame frame;

    public DataSearchView(){
        frame = new JInternalFrame("Table Search", false, true, false, false);
        frame.setMinimumSize(new Dimension(480, 200));
        frame.setSize(480, 200);
        frame.setFrameIcon(new ImageIcon("resource/icons/tablesearch-16px.png"));
        frame.setLayout(new BorderLayout());

        titlePanel = new JPanel();
        pageTitle = new JLabel("Search");
        titlePanel.add(pageTitle);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        pageTitle.setFont(new Font("Arial", Font.BOLD, 25));
        titlePanel.setBorder(new EmptyBorder(10, 10, 5, 10));

        searchLabel = new JLabel("Enter Search Query");
        searchField = new JTextField();
        searchType = new ButtonGroup();
        radioButton1 = new JRadioButton("ID");
        radioButton1.setSelected(true);
        radioButton2 = new JRadioButton("First Name");
        radioButton3 = new JRadioButton("Last Name");
        radioButton4 = new JRadioButton("E-Mail");
        radioButton5 = new JRadioButton("Phone Number");
        searchType.add(radioButton1);
        radioButton1.setSelected(true);
        searchType.add(radioButton2);
        searchType.add(radioButton3);
        searchType.add(radioButton4);
        searchType.add(radioButton5);

        searchPanel = new JPanel();
        searchPanel.setLayout(new GridBagLayout());
        searchPanel.add(searchLabel, new GBCBuilder(GridBagConstraints.EAST, 1, 1, 0, 0, new Insets(0,5,5,5)).getGBC());
        searchPanel.add(searchField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 1, 0, 1, new Insets(5,5,5,5)).setColumnSpan(5, 1));
        searchPanel.add(radioButton1, new GBCBuilder(GridBagConstraints.WEST, 0.1, 1, 0, 2, new Insets(5,5,5,0)).getGBC());
        searchPanel.add(radioButton2, new GBCBuilder(GridBagConstraints.WEST, 0.2, 1, 1, 2, new Insets(5,5,5,0)).getGBC());
        searchPanel.add(radioButton3, new GBCBuilder(GridBagConstraints.WEST, 0.2, 1, 2, 2, new Insets(5,5,5,0)).getGBC());
        searchPanel.add(radioButton4, new GBCBuilder(GridBagConstraints.WEST, 0.2, 1, 3, 2, new Insets(5,5,5,0)).getGBC());
        searchPanel.add(radioButton5, new GBCBuilder(GridBagConstraints.WEST, 0.3, 1, 4, 2, new Insets(5,5,5,5)).getGBC());

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        searchButton = new JButton("Search");
        buttonPanel.add(searchButton);

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(searchPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

    }

    public JInternalFrame getFrame() {
        return frame;
    }
}
