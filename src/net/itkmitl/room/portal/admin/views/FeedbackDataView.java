package net.itkmitl.room.portal.admin.views;

import net.itkmitl.room.libs.peeranat.util.FewFile;
import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Hashtable;

public class FeedbackDataView {
    private final JInternalFrame frame;
    private final JPanel dataPanelPad;
    private final JPanel titlePanel;
    private final JPanel buttonPanel;
    private final JPanel ratingPanel;
    private final JScrollPane commentScrollPane;
    private final JLabel idLabel;
    private final JLabel userIDLabel;
    private final JLabel roomIDLabel;
    private final JLabel commentLabel;
    private final JLabel createdOnLabel;
    private final JLabel ratingLabel;
    public JSpinner ratingSpinner;
    public JPanel dataPanel;
    public JButton saveButton, cancelButton;
    public JLabel pageTitle, pageSubtitle;
    public JTextField idField, createdOnField;
    public JSlider ratingSlider;
    public JTextArea commentField;
    public JComboBox<Object> userIDSelect, roomIDSelect;

    public FeedbackDataView() {
        frame = new JInternalFrame("Feedback Data", false, true, false, true);
        frame.setFrameIcon(FewFile.getImage("icons/tableedit-16px.png"));
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
        commentField.setFont(new Font("TH SarabunPSK", Font.BOLD, 18));
        commentScrollPane = new JScrollPane(commentField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        dataPanel.add(commentLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 4, new Insets(0, 10, 5, 5)).getGBC());
        dataPanel.add(commentScrollPane, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 4, new Insets(0, 0, 5, 10)).setColumnSpan(1, 3));
        dataPanel.add(new JPanel(), new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 5, new Insets(0, 0, 5, 5)).getGBC());
        dataPanel.add(new JPanel(), new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 6, new Insets(0, 0, 5, 5)).getGBC());

        ratingLabel = new JLabel("Rating");
        ratingPanel = new JPanel();
        ratingPanel.setLayout(new GridBagLayout());
        ratingSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
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
