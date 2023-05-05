package net.itkmitl.room.portal.admin.views;

import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReservationDataView {
    private JInternalFrame frame;
    private JPanel dataPanelPad, titlePanel, buttonPanel, timePanel;
    public JPanel dataPanel;
    private JScrollPane reasonScrollPane;
    public JButton saveButton, cancelButton;
    public JLabel pageTitle, pageSubtitle;
    private JLabel idLabel, userIDLabel, roomIDLabel, reasonLabel, startTimeLabel, endTimeLabel, reservationTimeLabel;
    public JTextField idField, reservationTimeField;
    public JCheckBox cancelledCheckbox;
    public JTextArea reasonField;
    public JComboBox userIDSelect, roomIDSelect;
    public JSpinner startTimeHourField, startTimeMinuteField, endTimeHourField, endTimeMinuteField;

    public ReservationDataView() {
        frame = new JInternalFrame("Reservation Data", false, true, false, true);
        frame.setFrameIcon(new ImageIcon("resource/icons/tableedit-16px.png"));
        frame.setLayout(new BorderLayout());

        titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(new EmptyBorder(10, 10, 5, 10));

        pageTitle = new JLabel("Reservation Data");
        pageSubtitle = new JLabel("Viewing reservation data");
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
        dataPanel.setBorder(BorderFactory.createTitledBorder("Reservation Data"));
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

        reasonLabel = new JLabel("Reason");
        reasonField = new JTextArea("");
        reasonField.setLineWrap(true);
        reasonField.setRows(3);
        reasonScrollPane = new JScrollPane(reasonField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        dataPanel.add(reasonLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 3, new Insets(0, 10, 5, 5)).getGBC());
        dataPanel.add(reasonScrollPane, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 3, new Insets(0, 0, 5, 10)).setColumnSpan(1, 3));
        dataPanel.add(new JPanel(), new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 4, new Insets(0, 10, 5, 5)).getGBC());
        dataPanel.add(new JPanel(), new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 5, new Insets(0, 10, 5, 5)).getGBC());

        timePanel = new JPanel();
        timePanel.setLayout(new GridBagLayout());
        startTimeLabel = new JLabel("Start Time");
        startTimeHourField = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        startTimeHourField.setEditor(new JSpinner.NumberEditor(startTimeHourField, "00"));
        startTimeMinuteField = new JSpinner(new SpinnerNumberModel(0, 0, 59, 30));
        startTimeMinuteField.setEditor(new JSpinner.NumberEditor(startTimeMinuteField, "00"));
        timePanel.add(startTimeLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.2, 0, 0, new Insets(0, 10, 5, 5)).getGBC());
        timePanel.add(startTimeHourField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.4, 1, 0, new Insets(0, 30, 5, 5)).getGBC());
        timePanel.add(startTimeMinuteField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.4, 2, 0, new Insets(0, 0, 5, 5)).getGBC());

        endTimeLabel = new JLabel("End Time");
        endTimeHourField = new JSpinner(new SpinnerNumberModel(23, 0, 23, 1));
        endTimeHourField.setEditor(new JSpinner.NumberEditor(endTimeHourField, "00"));
        endTimeMinuteField = new JSpinner(new SpinnerNumberModel(0, 0, 59, 30));
        endTimeMinuteField.setEditor(new JSpinner.NumberEditor(endTimeMinuteField, "00"));
        timePanel.add(endTimeLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.2, 0, 1, new Insets(0, 10, 5, 5)).getGBC());
        timePanel.add(endTimeHourField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.4, 1, 1, new Insets(0, 30, 5, 5)).getGBC());
        timePanel.add(endTimeMinuteField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.4, 2, 1, new Insets(0, 0, 5, 5)).getGBC());
        dataPanel.add(timePanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 6).setColumnSpan(2, 1));

        reservationTimeLabel = new JLabel("Reservation Time");
        reservationTimeField = new JTextField("");
        dataPanel.add(reservationTimeLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 7, new Insets(0, 10, 5, 5)).getGBC());
        dataPanel.add(reservationTimeField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 7, new Insets(0, 0, 5, 10)).getGBC());

        cancelledCheckbox = new JCheckBox("Is Cancelled");
        dataPanel.add(cancelledCheckbox, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 8, new Insets(10, 10, 10, 0)).setColumnSpan(2, 1));

        idField.setEditable(false);
        reservationTimeField.setEnabled(false);

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

    public JInternalFrame getFrame(){
        return frame;
    }
}
