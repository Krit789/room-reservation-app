package net.itkmitl.room.portal.admin.views;

import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DataSearchView {
    private final JPanel searchPanel, buttonPanel, titlePanel, radioPanel;
    public final ButtonGroup searchType;
    public final JRadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5;
    public final JTextField searchField;
    public final JLabel searchLabel, pageTitle;
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
        radioButton1 = new JRadioButton("");
        radioButton2 = new JRadioButton("");
        radioButton3 = new JRadioButton("");
        radioButton4 = new JRadioButton("");
        radioButton5 = new JRadioButton("");
        searchType.add(radioButton1);
        radioButton1.setSelected(true);
        searchType.add(radioButton2);
        searchType.add(radioButton3);
        searchType.add(radioButton4);
        searchType.add(radioButton5);

        searchPanel = new JPanel();
        radioPanel = new JPanel();
        radioPanel.setLayout(new FlowLayout());
        searchPanel.setLayout(new GridBagLayout());
        searchPanel.add(searchLabel, new GBCBuilder(GridBagConstraints.BOTH, 1, 0, 0, 0, new Insets(5,10,5,5)).getGBC());
        searchPanel.add(searchField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 0, 1, new Insets(0,10,0,10)).getGBC());

        radioPanel.add(radioButton1);
        radioPanel.add(radioButton2);
        radioPanel.add(radioButton3);
        radioPanel.add(radioButton4);
        radioPanel.add(radioButton5);
        searchPanel.add(radioPanel, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 0 ,2, new Insets(5,10,0,10)).setAnchor(GridBagConstraints.WEST));


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
