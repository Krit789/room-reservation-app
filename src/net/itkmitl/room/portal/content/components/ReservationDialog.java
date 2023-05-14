package net.itkmitl.room.portal.content.components;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.content.MainContentView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;

public class ReservationDialog extends JDialog implements ActionListener, WindowListener {
    private JPanel titlePanel, selectionPanel, buttonPanel, lengthPanel;
    private JLabel pageTitle, pageSubtitle, dateLabel, segmentLabel, lengthLabel, hourLabel;
    private JButton okButton, cancelButton;
    private DatePicker reservationDatePicker;
    public JComboBox segmentBox;
    public JSpinner lengthSpinner;

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

        dateLabel.setFont(regular);
        segmentLabel.setFont(regular);
        lengthLabel.setFont(regular);
        hourLabel.setFont(regular);

        DatePickerSettings datePickerSettings1 = new DatePickerSettings();
        Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(44, 84, 144));
        datePickerSettings1.setBorderCalendarPopup(new LineBorder(new Color(44, 84, 144), 1));
        reservationDatePicker = new DatePicker(datePickerSettings1);
        datePickerSettings1.setDateRangeLimits(LocalDate.now(), LocalDate.now().plusDays(7));
        reservationDatePicker.getComponentDateTextField().setBorder(border);
        reservationDatePicker.setDateToToday();

        segmentBox = new JComboBox();
        lengthSpinner = new JSpinner();
        selectionPanel = new JPanel();
        selectionPanel.setBackground(Color.white);
        selectionPanel.setLayout(new GridBagLayout());
        selectionPanel.add(dateLabel, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 0, new Insets(5, 10, 0, 10)).setAnchor(GridBagConstraints.WEST));
        selectionPanel.add(reservationDatePicker, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 1, new Insets(0, 10, 0, 10)).getGBC());
        selectionPanel.add(segmentLabel, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 2, new Insets(5, 10, 0, 10)).setAnchor(GridBagConstraints.WEST));
        selectionPanel.add(segmentBox, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 3, new Insets(10, 10, 5, 10)).getGBC());
        selectionPanel.add(lengthLabel, new GBCBuilder(GridBagConstraints.NONE, 1, 0, 4, new Insets(5, 10, 0, 10)).setAnchor(GridBagConstraints.WEST));

        lengthPanel = new JPanel();
        lengthPanel.setBackground(Color.white);
        lengthPanel.setLayout(new GridBagLayout());
        lengthPanel.add(lengthSpinner, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.9, 0, 0, new Insets(0, 0, 0, 0)).getGBC());
        lengthPanel.add(hourLabel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 0.1, 1, 0, new Insets(0, 5, 0, 0)).getGBC());

        selectionPanel.add(lengthPanel, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 0, 5, new Insets(0, 10, 5, 10)).getGBC());

        add(selectionPanel, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
        addWindowListener(this);
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(parent);
    }
    private void disableGlassPane(){
        MainContentView.glassPane.setVisible(false);
        MainContentView.glassPane.setEnabled(false);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        disableGlassPane();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(okButton)) {
            dispose();
            disableGlassPane();

        } else if (e.getSource().equals(cancelButton)) {
            dispose();
            disableGlassPane();

        }
    }
}
