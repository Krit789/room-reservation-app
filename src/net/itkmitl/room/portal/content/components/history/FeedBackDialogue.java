package net.itkmitl.room.portal.content.components.history;

import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FeedBackDialogue extends JDialog {
    public JRadioButton rating00, rating05, rating10, rating15, rating20, rating25, rating30, rating35, rating40, rating45, rating50;
    public ButtonGroup ratingGroup;
    public JButton submitBtn, cancelBtn;
    private JLabel pageTitle, pageSubtitle, feedbackLabel, ratingLabel;
    private JPanel ratingPanel, titlePanel, centerPanel, buttonPanel;
    private JScrollPane feedbackPane;
    public JTextArea feedbackTextArea;
    private final Font regular = new Font("Sansserif", Font.PLAIN, 16);
    public FeedBackDialogue(JFrame parent/*, Room room*/){
        super(parent, "Select Time", true);
        setBackground(Color.white);
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.white);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(new EmptyBorder(10, 10, 5, 10));

        pageTitle = new JLabel("FeedBack");
        pageSubtitle = new JLabel("How is the room");
        titlePanel.add(pageTitle);
        titlePanel.add(pageSubtitle);
        pageTitle.setFont(new Font("Cousine", Font.BOLD, 25));
        pageTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        pageSubtitle.setFont(new Font("Cousine", Font.PLAIN, 12));
        pageSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(titlePanel, BorderLayout.NORTH);

        centerPanel = new JPanel();
        centerPanel.setBackground(Color.white);
        centerPanel.setLayout(new GridBagLayout());

        ratingPanel = new JPanel(new FlowLayout());
        ratingGroup = new ButtonGroup();
        ratingLabel = new JLabel("Rate");
        ratingLabel.setFont(regular);

        rating00 = new JRadioButton("0");
        ratingPanel.add(rating00);
        ratingGroup.add(rating00);
        rating05 = new JRadioButton("0.5");
        ratingPanel.add(rating05);
        ratingGroup.add(rating05);
        rating10 = new JRadioButton("1");
        ratingPanel.add(rating10);
        ratingGroup.add(rating10);
        rating15 = new JRadioButton("1.5");
        ratingPanel.add(rating15);
        ratingGroup.add(rating15);
        rating20 = new JRadioButton("2");
        ratingPanel.add(rating20);
        ratingGroup.add(rating20);
        rating25 = new JRadioButton("2.5");
        ratingPanel.add(rating25);
        ratingGroup.add(rating25);
        rating30 = new JRadioButton("3");
        ratingPanel.add(rating30);
        ratingGroup.add(rating30);
        rating35 = new JRadioButton("3.5");
        ratingPanel.add(rating35);
        ratingGroup.add(rating35);
        rating40 = new JRadioButton("4");
        ratingPanel.add(rating40);
        ratingGroup.add(rating40);
        rating45 = new JRadioButton("4.5");
        ratingPanel.add(rating45);
        ratingGroup.add(rating45);
        rating50 = new JRadioButton("5");
        ratingPanel.add(rating50);
        ratingGroup.add(rating50);

        feedbackLabel = new JLabel("Comments");
        feedbackLabel.setFont(regular);

        feedbackTextArea = new JTextArea();
        feedbackTextArea.setRows(4);
        feedbackPane = new JScrollPane(feedbackTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        centerPanel.add(ratingLabel, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 0, new Insets(5, 15, 0, 15)).setAnchor(GridBagConstraints.WEST));
        centerPanel.add(ratingPanel, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 1, new Insets(5, 15, 0, 15)).setAnchor(GridBagConstraints.WEST));
        centerPanel.add(feedbackLabel, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 2, new Insets(5, 15, 0, 15)).setAnchor(GridBagConstraints.WEST));
        centerPanel.add(feedbackPane, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 3, new Insets(5, 15, 0, 15)).getGBC());
        add(centerPanel, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);
        submitBtn = new JButton("Submit");
        submitBtn.setEnabled(false);
        cancelBtn = new JButton("Cancel");
        buttonPanel.add(submitBtn);
        buttonPanel.add(cancelBtn);

        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
}
