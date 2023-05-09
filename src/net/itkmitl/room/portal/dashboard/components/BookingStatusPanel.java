package net.itkmitl.room.portal.dashboard.components;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.RoundedPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
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
        dataset.setValue("Available", 70);
        dataset.setValue("Full", 30);

        JFreeChart chart;
        chart = ChartFactory.createPieChart(
                        "",
                dataset,
                true,
                true,
                false
        );;

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(250, 250));
        chartPanel.setBackground(new Color(0x0000005, true));

        PiePlot plot = (PiePlot) chart.getPlot();


        plot.setSectionPaint("Available", new Color(90, 140, 206));
        plot.setSectionPaint("Full", new Color(0x101442));


        piechart = new JPanel();
        piechart.add(chartPanel);

        this.add(piechart, new GBCBuilder(GridBagConstraints.BOTH, 0, 10, 0, 1).setAnchor(GridBagConstraints.CENTER));
        piechart.setSize(new Dimension(50, 50));
        piechart.setBackground(new Color(0x0343477, true));





    }
}
