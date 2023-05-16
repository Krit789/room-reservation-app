package net.itkmitl.room.portal.admin.views;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ReservationDataView {
    public JPanel dataPanel;
    public JButton saveButton, cancelButton;
    public JLabel pageTitle, pageSubtitle;
    public JTextField idField, reservationTimeField;
    public JCheckBox cancelledCheckbox;
    public JTextArea reasonField;
    public JComboBox<Object> userIDSelect, roomIDSelect;
    public JSpinner startTimeHourField, startTimeMinuteField, endTimeHourField, endTimeMinuteField;
    public DatePicker startDatePicker, endDatePicker;
    private final JInternalFrame frame;
    private final JPanel dataPanelPad;
    private final JPanel titlePanel;
    private final JPanel buttonPanel;
    private final JPanel timePanel;
    private final JScrollPane reasonScrollPane;
    private final JLabel idLabel;
    private final JLabel userIDLabel;
    private final JLabel roomIDLabel;
    private final JLabel reasonLabel;
    private final JLabel startTimeLabel;
    private final JLabel endTimeLabel;
    private final JLabel reservationTimeLabel;

    public ReservationDataView() {
        frame = new JInternalFrame("Reservation Data", false, true, false, true);
        frame.setFrameIcon(FewFile.getImage("icons/tableedit-16px.png"));
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

        DatePickerSettings datePickerSettings1 = new DatePickerSettings();
        datePickerSettings1.setColor(DatePickerSettings.DateArea.BackgroundOverallCalendarPanel, new Color(40, 40, 40));
        datePickerSettings1.setColor(DatePickerSettings.DateArea.BackgroundClearLabel, new Color(86, 86, 86));
        datePickerSettings1.setColor(DatePickerSettings.DateArea.BackgroundCalendarPanelLabelsOnHover, new Color(94, 94, 94));
        datePickerSettings1.setColor(DatePickerSettings.DateArea.BackgroundTodayLabel, new Color(86, 86, 86));
        datePickerSettings1.setColor(DatePickerSettings.DateArea.BackgroundTopLeftLabelAboveWeekNumbers, new Color(86, 86, 86));
        datePickerSettings1.setColor(DatePickerSettings.DateArea.BackgroundMonthAndYearMenuLabels, new Color(86, 86, 86));
        datePickerSettings1.setColor(DatePickerSettings.DateArea.TextFieldBackgroundValidDate, new Color(40, 40, 40));
        datePickerSettings1.setColor(DatePickerSettings.DateArea.TextFieldBackgroundDisabled, new Color(35, 35, 35));
        datePickerSettings1.setColor(DatePickerSettings.DateArea.DatePickerTextValidDate, new Color(221, 221, 221));
        datePickerSettings1.setColor(DatePickerSettings.DateArea.TextFieldBackgroundInvalidDate, new Color(40, 40, 40));
        datePickerSettings1.setColor(DatePickerSettings.DateArea.CalendarBackgroundNormalDates, new Color(40, 40, 40));
        datePickerSettings1.setColor(DatePickerSettings.DateArea.CalendarBackgroundSelectedDate, new Color(86, 86, 86));
        datePickerSettings1.setColor(DatePickerSettings.DateArea.CalendarTextNormalDates, new Color(221, 221, 221));
        datePickerSettings1.setBorderCalendarPopup(new LineBorder(new Color(60, 60, 60), 1));

        DatePickerSettings datePickerSettings2 = new DatePickerSettings();
        datePickerSettings2.setColor(DatePickerSettings.DateArea.BackgroundOverallCalendarPanel, new Color(40, 40, 40));
        datePickerSettings2.setColor(DatePickerSettings.DateArea.BackgroundClearLabel, new Color(86, 86, 86));
        datePickerSettings2.setColor(DatePickerSettings.DateArea.BackgroundCalendarPanelLabelsOnHover, new Color(94, 94, 94));
        datePickerSettings2.setColor(DatePickerSettings.DateArea.BackgroundTodayLabel, new Color(86, 86, 86));
        datePickerSettings2.setColor(DatePickerSettings.DateArea.BackgroundTopLeftLabelAboveWeekNumbers, new Color(86, 86, 86));
        datePickerSettings2.setColor(DatePickerSettings.DateArea.BackgroundMonthAndYearMenuLabels, new Color(86, 86, 86));
        datePickerSettings2.setColor(DatePickerSettings.DateArea.TextFieldBackgroundValidDate, new Color(40, 40, 40));
        datePickerSettings2.setColor(DatePickerSettings.DateArea.TextFieldBackgroundDisabled, new Color(35, 35, 35));
        datePickerSettings2.setColor(DatePickerSettings.DateArea.DatePickerTextValidDate, new Color(221, 221, 221));
        datePickerSettings2.setColor(DatePickerSettings.DateArea.TextFieldBackgroundInvalidDate, new Color(40, 40, 40));
        datePickerSettings2.setColor(DatePickerSettings.DateArea.CalendarBackgroundNormalDates, new Color(40, 40, 40));
        datePickerSettings2.setColor(DatePickerSettings.DateArea.CalendarBackgroundSelectedDate, new Color(86, 86, 86));
        datePickerSettings2.setColor(DatePickerSettings.DateArea.CalendarTextNormalDates, new Color(221, 221, 221));
        datePickerSettings2.setBorderCalendarPopup(new LineBorder(new Color(60, 60, 60), 1));

        startDatePicker = new DatePicker(datePickerSettings1);
        startDatePicker.getComponentDateTextField().setBorder(new LineBorder(new Color(60, 60, 60), 1));
        startDatePicker.setDateToToday();
        startDatePicker.getComponentToggleCalendarButton().setIcon(FewFile.getImage("icons/calendar-16px.png"));
        startDatePicker.getComponentToggleCalendarButton().setText("");
        endDatePicker = new DatePicker(datePickerSettings2);
        endDatePicker.getComponentDateTextField().setBorder(new LineBorder(new Color(60, 60, 60), 1));
        endDatePicker.setDateToToday();
        endDatePicker.getComponentToggleCalendarButton().setIcon(FewFile.getImage("icons/calendar-16px.png"));
        endDatePicker.getComponentToggleCalendarButton().setText("");


        startTimeLabel = new JLabel("Start Time");
        startTimeHourField = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        startTimeHourField.setEditor(new JSpinner.NumberEditor(startTimeHourField, "00"));
        startTimeMinuteField = new JSpinner(new SpinnerNumberModel(0, 0, 59, 30));
        startTimeMinuteField.setEditor(new JSpinner.NumberEditor(startTimeMinuteField, "00"));
        timePanel.add(startTimeLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.2, 0, 0, new Insets(0, 10, 5, 5)).getGBC());
        timePanel.add(startDatePicker, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.8, 1, 0, new Insets(0, 30, 5, 5)).setColumnSpan(2, 1));
        timePanel.add(startTimeHourField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.4, 1, 1, new Insets(0, 30, 5, 5)).getGBC());
        timePanel.add(startTimeMinuteField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.4, 2, 1, new Insets(0, 0, 5, 5)).getGBC());
        endTimeLabel = new JLabel("End Time");
        endTimeHourField = new JSpinner(new SpinnerNumberModel(23, 0, 23, 1));
        endTimeHourField.setEditor(new JSpinner.NumberEditor(endTimeHourField, "00"));
        endTimeMinuteField = new JSpinner(new SpinnerNumberModel(0, 0, 59, 30));
        endTimeMinuteField.setEditor(new JSpinner.NumberEditor(endTimeMinuteField, "00"));

        timePanel.add(endDatePicker, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.8, 1, 2, new Insets(0, 30, 5, 5)).setColumnSpan(2, 1));
        timePanel.add(endTimeLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.2, 0, 2, new Insets(0, 10, 5, 5)).getGBC());
        timePanel.add(endTimeHourField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.4, 1, 3, new Insets(0, 30, 5, 5)).getGBC());
        timePanel.add(endTimeMinuteField, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.4, 2, 3, new Insets(0, 0, 5, 5)).getGBC());
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

    public JInternalFrame getFrame() {
        return frame;
    }
}
