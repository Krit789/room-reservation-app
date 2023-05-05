package net.itkmitl.room.portal.admin.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import net.itkmitl.room.portal.components.GBCBuilder;

public class FeedbackDataView {
    private JInternalFrame frame;
    private JPanel dataPanelPad, titlePanel, buttonPanel, ratingPanel;
    public JSpinner ratingSpinner;
    public JPanel dataPanel;
    private JScrollPane commentScrollPane;
    public JButton saveButton, cancelButton;
    public JLabel pageTitle, pageSubtitle;
    private JLabel idLabel, userIDLabel, roomIDLabel, commentLabel, createdOnLabel, ratingLabel;
    public JTextField idField, createdOnField;
    public JSlider ratingSlider;
    public JTextArea commentField;
    public JComboBox<Object> userIDSelect, roomIDSelect;

    public FeedbackDataView() {
        frame = new JInternalFrame("Feedback Data", false, true, false, true);
        frame.setFrameIcon(new ImageIcon("resource/icons/tableedit-16px.png"));
        frame.setLayout(new BorderLayout());

        titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(new EmptyBorder(10, 10, 5, 10));

        pageTitle = new JLabel("Feedback Data");
        pageSubtitle = new JLabel("Viewing Feedback data");
        titlePanel.add(pageTitle);
        titlePanel.add(pageSubtitle);
        pageTitle.setFont(new Font("Arial", Font.BOLD, 25));
        pageTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        pageSubtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        frame.add(titlePanel, BorderLayout.NORTH);
        dataPanel = new JPanel();
        dataPanelPad = new JPanel();
        dataPanelPad.setBorder(new EmptyBorder(5, 10, 10, 10));
        dataPanelPad.setLayout(new BorderLayout());
        dataPanel.setBorder(BorderFactory.createTitledBorder("Feedback Data"));
        dataPanel.setLayout(new GridBagLayout());

        idLabel = new JLabel("ID");
        idField = new JTextField("");
        dataPanel.add(idLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 0, new Insets(5, 10, 5, 5)).getGBC());
        dataPanel.add(idField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 0, new Insets(0, 0, 5, 10)).getGBC());

        userIDLabel = new JLabel("User");
        userIDSelect = new JComboBox<>();
        dataPanel.add(userIDLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 1, new Insets(0, 10, 5, 5)).getGBC());
        dataPanel.add(userIDSelect, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 1, new Insets(0, 0, 5, 10)).getGBC());

        roomIDLabel = new JLabel("Room");
        roomIDSelect = new JComboBox<>();
        dataPanel.add(roomIDLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 2, new Insets(0, 10, 5, 5)).getGBC());
        dataPanel.add(roomIDSelect, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 2, new Insets(0, 0, 5, 10)).getGBC());

        createdOnLabel = new JLabel("Created On");
        createdOnField = new JTextField("");
        dataPanel.add(createdOnLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 3, new Insets(0, 10, 5, 5)).getGBC());
        dataPanel.add(createdOnField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 3, new Insets(0, 0, 5, 10)).getGBC());


        commentLabel = new JLabel("Comment");
        commentField = new JTextArea("");
        commentField.setRows(3);
        commentField.setLineWrap(true);
        commentScrollPane = new JScrollPane(commentField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        dataPanel.add(commentLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 4, new Insets(0, 10, 5, 5)).getGBC());
        dataPanel.add(commentScrollPane, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 4, new Insets(0, 0, 5, 10)).setColumnSpan(1, 3));
        dataPanel.add(new JPanel(), new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 5, new Insets(0, 0, 5, 5)).getGBC());
        dataPanel.add(new JPanel(), new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 6, new Insets(0, 0, 5, 5)).getGBC());

        ratingLabel = new JLabel("Rating");
        ratingPanel = new JPanel();
        ratingPanel.setLayout(new GridBagLayout());
        ratingSlider = new JSlider(JSlider.HORIZONTAL, 0, 10 ,0);
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("0.0"));
        labelTable.put(1, new JLabel("0.5"));
        labelTable.put(2, new JLabel("1.0"));
        labelTable.put(3, new JLabel("1.5"));
        labelTable.put(4, new JLabel("2.0"));
        labelTable.put(5, new JLabel("2.5"));
        labelTable.put(6, new JLabel("3.0"));
        labelTable.put(7, new JLabel("3.5"));
        labelTable.put(8, new JLabel("4.0"));
        labelTable.put(9, new JLabel("4.5"));
        labelTable.put(10, new JLabel("5.0"));
        ratingSlider.setLabelTable(labelTable);
        ratingSlider.setMajorTickSpacing(2);
        ratingSlider.setMinorTickSpacing(1);
        ratingSlider.setPaintTicks(true);
        ratingSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 5.0, 0.5));
        ratingSpinner.setEditor(new JSpinner.NumberEditor(ratingSpinner, "0.0"));

        ratingPanel.add(ratingSlider, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.03, 0, 0, new Insets(0, 0, 5, 5)).getGBC());
        ratingPanel.add(ratingSpinner, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.97, 1, 0, new Insets(0, 0, 5, 0)).getGBC());
        dataPanel.add(ratingLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 7, new Insets(0, 10, 5, 5)).getGBC());
        dataPanel.add(ratingPanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 7, new Insets(0, 0, 5, 10)).getGBC());

        idField.setEditable(false);
        createdOnField.setEnabled(false);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dataPanelPad.add(dataPanel, BorderLayout.CENTER);

        frame.add(dataPanelPad, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    public JInternalFrame getFrame() {
        return frame;
    }
}
