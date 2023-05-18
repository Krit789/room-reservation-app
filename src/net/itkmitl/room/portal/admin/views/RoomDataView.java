package net.itkmitl.room.portal.admin.views;

import net.itkmitl.room.libs.peeranat.util.FewFile;
import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RoomDataView {
    private final String[] stateList = new String[]{"Available", "Unavailable", "Maintenance"};
    private final JInternalFrame frame;
    private final JPanel dataPanelPad;
    private final JPanel titlePanel;
    private final JPanel buttonPanel;
    private final JPanel timePanel;
    private final JLabel idLabel;
    private final JLabel nameLabel;
    private final JLabel capacityLabel;
    private final JLabel floorLabel;
    private final JLabel buildingLabel;
    private final JLabel openTimeLabel;
    private final JLabel closeTimeLabel;
    private final JLabel stateLabel;
    public JPanel dataPanel;
    public JButton saveButton, cancelButton;
    public JLabel pageTitle, pageSubtitle;
    public JTextField idField, nameField, floorField, buildingField;
    public JSpinner capacitySpinner, openTimeHourField, openTimeMinuteField, closeTimeHourField, closeTimeMinuteField;
    public JComboBox<?> stateSelect;

    public RoomDataView() {
        frame = new JInternalFrame("Room Data", false, true, false, true);
        frame.setFrameIcon(FewFile.getImage("icons/tableedit-16px.png"));
        frame.setLayout(new BorderLayout());

        titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(new EmptyBorder(10, 10, 5, 10));

        pageTitle = new JLabel("Room Data");
        pageSubtitle = new JLabel("Viewing room data");
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
        dataPanel.setBorder(BorderFactory.createTitledBorder("Room Data"));
        dataPanel.setLayout(new GridBagLayout());

        idLabel = new JLabel("ID");
        idField = new JTextField("");
        dataPanel.add(idLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 0, new Insets(5, 10, 5, 5)).getGBC());
        dataPanel.add(idField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 0, new Insets(0, 0, 5, 10)).getGBC());


        nameLabel = new JLabel("Name");
        nameField = new JTextField("");
        dataPanel.add(nameLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 1, new Insets(0, 10, 5, 5)).getGBC());
        dataPanel.add(nameField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.7, 1, 1, new Insets(0, 0, 5, 10)).getGBC());

        capacityLabel = new JLabel("Capacity");
        capacitySpinner = new JSpinner(new SpinnerNumberModel(8, 0, 65535, 1));
        dataPanel.add(capacityLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 2, 1, new Insets(0, 10, 5, 5)).getGBC());
        dataPanel.add(capacitySpinner, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 3, 1, new Insets(0, 0, 5, 10)).getGBC());

        buildingLabel = new JLabel("Building");
        buildingField = new JTextField("");
        dataPanel.add(buildingLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 2, new Insets(0, 10, 5, 5)).getGBC());
        dataPanel.add(buildingField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.7, 1, 2, new Insets(0, 0, 5, 10)).getGBC());

        floorLabel = new JLabel("Floor");
        floorField = new JTextField("");
        dataPanel.add(floorLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 2, 2, new Insets(0, 10, 5, 5)).getGBC());
        dataPanel.add(floorField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 3, 2, new Insets(0, 0, 5, 10)).getGBC());

        timePanel = new JPanel();
        timePanel.setLayout(new GridBagLayout());


        openTimeLabel = new JLabel("Open Time");
        openTimeHourField = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        openTimeHourField.setEditor(new JSpinner.NumberEditor(openTimeHourField, "00"));
        openTimeMinuteField = new JSpinner(new SpinnerNumberModel(0, 0, 59, 30));
        openTimeMinuteField.setEditor(new JSpinner.NumberEditor(openTimeMinuteField, "00"));
        timePanel.add(openTimeLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.2, 0, 0, new Insets(0, 10, 5, 5)).getGBC());
        timePanel.add(openTimeHourField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.2, 1, 0, new Insets(0, 0, 5, 5)).getGBC());
        timePanel.add(openTimeMinuteField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.2, 2, 0, new Insets(0, 0, 5, 5)).getGBC());

        closeTimeLabel = new JLabel("Close Time");
        closeTimeHourField = new JSpinner(new SpinnerNumberModel(23, 0, 23, 1));
        closeTimeHourField.setEditor(new JSpinner.NumberEditor(closeTimeHourField, "00"));
        closeTimeMinuteField = new JSpinner(new SpinnerNumberModel(0, 0, 59, 30));
        closeTimeMinuteField.setEditor(new JSpinner.NumberEditor(closeTimeMinuteField, "00"));
        timePanel.add(closeTimeLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.2, 3, 0, new Insets(0, 30, 5, 5)).getGBC());
        timePanel.add(closeTimeHourField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.2, 4, 0, new Insets(0, 0, 5, 5)).getGBC());
        timePanel.add(closeTimeMinuteField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.2, 5, 0, new Insets(0, 0, 5, 5)).getGBC());

        dataPanel.add(timePanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 3).setColumnSpan(4, 1));


        stateLabel = new JLabel("State");
        stateSelect = new JComboBox<>(stateList);
        dataPanel.add(stateLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 0, 4, new Insets(0, 10, 5, 5)).getGBC());
        dataPanel.add(stateSelect, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 1, 4, new Insets(0, 0, 5, 10)).getGBC());

        idField.setEditable(false);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");


        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dataPanelPad.add(dataPanel, BorderLayout.CENTER);

        frame.add(dataPanelPad, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
    }

    public JInternalFrame getFrame() {
        return frame;
    }
}
