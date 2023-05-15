package net.itkmitl.room.portal.dashboard.components;


import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.RoundedPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class BookingStatusPanel extends RoundedPanel {

    /**
     *
     */
    private static final long serialVersionUID = 84550694890228683L;
    public JLabel titleLabel;
    private JPanel piechart;

    public BookingStatusPanel() {
        super(30, 40, new Color(156, 199, 223), new Color(181, 191, 224));

        this.setLayout(new GridBagLayout());
        this.setBorder(new EmptyBorder(15, 15, 15, 15));
        this.setBackground(new Color(234, 234, 234));
        this.setPreferredSize(
                new Dimension(200, 400)
        );

        titleLabel = new JLabel("Room Booking Status");
        titleLabel.setFont(new Font("Cousin", Font.BOLD, 20));

        this.add(titleLabel, new GBCBuilder(GridBagConstraints.NONE, 0, 1, 0, 0).setAnchor(GridBagConstraints.NORTH));

//        Pie chart
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Available Room", 70);
        dataset.setValue("Full", 30);

        JFreeChart chart;
        chart = ChartFactory.createPieChart(
                "",
                dataset,
                true,
                true,
                false
        );
        ;

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(250, 250));
        chartPanel.setBackground(new Color(0x0000005, true));

        PiePlot plot = (PiePlot) chart.getPlot();


        plot.setSectionPaint("Available Room", new Color(90, 140, 206));
        plot.setSectionPaint("Full", new Color(0x101442));


        piechart = new JPanel();
        piechart.add(chartPanel);

        this.add(piechart, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.8, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
        piechart.setSize(new Dimension(10, 50));
        piechart.setBackground(new Color(0x0343477, true));

        //calender
        int numDaysInMonth = 0;
        int firstDayOfMonth = 0;
        int numRows = (int) Math.ceil((numDaysInMonth + firstDayOfMonth - 1) / 7.0);

        JPanel panel = new JPanel(new GridLayout(numRows, 7));

        GregorianCalendar cal = new GregorianCalendar();

        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String header : headers) {
            JLabel label = new JLabel(header, JLabel.CENTER);
            panel.add(label);
        }

        firstDayOfMonth = new GregorianCalendar(year, month, 1).get(Calendar.DAY_OF_WEEK);
        numDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i < firstDayOfMonth; i++) {
            panel.add(new JLabel(""));
        }
        for (int i = 1; i <= numDaysInMonth; i++) {
            JLabel label = new JLabel(Integer.toString(i), JLabel.CENTER);
            if (i == day) {
                label.setOpaque(true);
                label.setBackground(new Color(67, 129, 238, 152));
            }
            panel.add(label);
        }

        panel.setPreferredSize(new Dimension(1, 1));
        this.add(panel, new GridBagConstraints(0, 8, 0, 3, 0, 10, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
    }
}



