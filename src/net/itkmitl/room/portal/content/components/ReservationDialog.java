package net.itkmitl.room.portal.content.components;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.LocalDate;

public class ReservationDialog extends JDialog {
    private JPanel titlePanel, selectionPanel, buttonPanel, lengthPanel, segmentPanel;
    private JLabel pageTitle, pageSubtitle, dateLabel, segmentLabel, lengthLabel, hourLabel, reasonLabel;
    public JLabel segmentLoadingLabel;
    public JButton okButton, cancelButton;
    public DatePicker reservationDatePicker;
    public JComboBox<ReservableEntity> segmentBox;
    public JSpinner lengthSpinner;
    public SpinnerNumberModel snm;
    private JScrollPane reasonPane;
    public JTextArea reasonTextArea;

    private final Font regular = new Font("Sansserif", Font.PLAIN, 16);

    public ReservationDialog(JFrame parent, Room room) {
        super(parent, "Select Time", true);

        setBackground(Color.white);
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.white);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(new EmptyBorder(10, 10, 5, 10));

        pageTitle = new JLabel("Reservation");
        pageSubtitle = new JLabel(String.format("Make a reservation for %s Floor %s, %s", room.getName(), room.getFloor(), room.getBuilding()));
        titlePanel.add(pageTitle);
        titlePanel.add(pageSubtitle);
        pageTitle.setFont(new Font("Cousine", Font.BOLD, 25));
        pageTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        pageSubtitle.setFont(new Font("Cousine", Font.PLAIN, 12));
        pageSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(titlePanel, BorderLayout.NORTH);

        dateLabel = new JLabel("Select Date");
        segmentLabel = new JLabel("Available Segment");
        lengthLabel = new JLabel("Length");
        hourLabel = new JLabel("Hour(s)");
        reasonLabel = new JLabel("Reason");

        dateLabel.setFont(regular);
        segmentLabel.setFont(regular);
        lengthLabel.setFont(regular);
        hourLabel.setFont(regular);
        reasonLabel.setFont(regular);

        DatePickerSettings datePickerSettings1 = new DatePickerSettings();
        Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(44, 84, 144));
        datePickerSettings1.setBorderCalendarPopup(new LineBorder(new Color(44, 84, 144), 1));
        reservationDatePicker = new DatePicker(datePickerSettings1);
        datePickerSettings1.setDateRangeLimits(LocalDate.now(), LocalDate.now().plusDays(7));
        datePickerSettings1.setEnableYearMenu(false);
        datePickerSettings1.setAllowEmptyDates(false);
        datePickerSettings1.setEnableMonthMenu(false);
        reservationDatePicker.getComponentToggleCalendarButton().setIcon(new ImageIcon("resource/icons/calendar-16px.png"));
        reservationDatePicker.getComponentToggleCalendarButton().setText("");
        reservationDatePicker.getComponentDateTextField().setBorder(border);
        reservationDatePicker.setDateToToday();

        segmentBox = new JComboBox<>();
        segmentBox.setBorder(border);
        lengthSpinner = new JSpinner();
        snm = new SpinnerNumberModel(1, 1, 3, 0.5);
        lengthSpinner.setModel(snm);
        lengthSpinner.setBorder(border);
        lengthSpinner.setEditor(new JSpinner.DefaultEditor(lengthSpinner));
        selectionPanel = new JPanel();
        selectionPanel.setBackground(Color.white);
        selectionPanel.setLayout(new GridBagLayout());
        selectionPanel.add(dateLabel, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 0, new Insets(5, 15, 0, 15)).setAnchor(GridBagConstraints.WEST));
        selectionPanel.add(reservationDatePicker, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 1, new Insets(0, 15, 0, 15)).getGBC());
        selectionPanel.add(segmentLabel, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 2, new Insets(5, 15, 0, 15)).setAnchor(GridBagConstraints.WEST));

        segmentPanel = new JPanel();
        segmentPanel.setBackground(Color.white);
        segmentPanel.setLayout(new GridBagLayout());
        segmentLoadingLabel = new JLabel();
        segmentLoadingLabel.setIcon(new ImageIcon("resource/icons/loading-16px.gif"));
        segmentPanel.add(segmentBox, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.99, 0, 0, new Insets(0, 0, 0, 0)).getGBC());
        segmentPanel.add(segmentLoadingLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.01, 1, 0, new Insets(0, 5, 0, 0)).getGBC());
        selectionPanel.add(segmentPanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 3, new Insets(10, 15, 5, 15)).getGBC());

        selectionPanel.add(lengthLabel, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 4, new Insets(5, 15, 0, 15)).setAnchor(GridBagConstraints.WEST));

        lengthPanel = new JPanel();
        lengthPanel.setBackground(Color.white);
        lengthPanel.setLayout(new GridBagLayout());
        lengthPanel.add(lengthSpinner, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 0, 0, new Insets(0, 0, 0, 0)).getGBC());
        lengthPanel.add(hourLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 1, 0, new Insets(0, 5, 0, 0)).getGBC());

        selectionPanel.add(lengthPanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 5, new Insets(0, 15, 5, 15)).getGBC());

        reasonTextArea = new JTextArea();
        reasonTextArea.setRows(4);
        reasonPane = new JScrollPane(reasonTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        selectionPanel.add(reasonLabel, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 6, new Insets(5, 15, 0, 15)).setAnchor(GridBagConstraints.WEST));
        selectionPanel.add(reasonPane, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 7, new Insets(0, 15, 5, 15)).getGBC());

        add(selectionPanel, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);
        okButton = new JButton("OK");
        okButton.setEnabled(false);
        cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

}
