package net.itkmitl.room.portal.content.components.dashboard;

import net.itkmitl.room.enums.EnumRoomState;
import net.itkmitl.room.libs.peeranat.util.FewRoom;
import net.itkmitl.room.libs.phatsanphon.date.DateTime;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.portal.components.GBCBuilder;
import net.itkmitl.room.portal.components.RoundedPanel;
import net.itkmitl.room.portal.components.TransparentPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serial;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

public class BookingStatusPanel extends RoundedPanel {
    @Serial
    private static final long serialVersionUID = 84550694890228683L;
    public JLabel titleLabel;
    private JPanel piechart;

    public BookingStatusPanel() {
        super(30, 40, new Color(156, 199, 223), new Color(181, 191, 224));

        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setBackground(new Color(234, 234, 234));
        setPreferredSize(new Dimension(200, 400));

        titleLabel = new JLabel("Room Booking Status");
        titleLabel.setFont(new Font("Cousine", Font.BOLD, 20));

        add(titleLabel, new GBCBuilder(GridBagConstraints.NONE, 0, 1, 0, 0).setAnchor(GridBagConstraints.NORTH));

        pieChart();
        clock();
        this.add(new TransparentPanel(), new GBCBuilder(GridBagConstraints.BOTH, 1, 1, 0, 2).getGBC());
        calendar();
    }

    private void pieChart() {
        //      .........Pie chart.........
        Collection<Room> roomAvailable = FewRoom.getRoomByState(EnumRoomState.AVAILABLE);
        Collection<Room> roomUnavailable = FewRoom.getRoomByState(EnumRoomState.UNAVAILABLE);
        Collection<Room> roomMaintenance = FewRoom.getRoomByState(EnumRoomState.MAINTENANCE);

        int roomAvailableNum = (roomAvailable.size());
        int roomUnavailableNum = (roomUnavailable.size() + roomMaintenance.size());

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Available Room", roomAvailableNum);
        dataset.setValue("Maintenance", roomUnavailableNum);

        JFreeChart chart;
        chart = ChartFactory.createPieChart(
                "",
                dataset,
                true,
                true,
                false
        );

        StandardChartTheme theme = (StandardChartTheme) org.jfree.chart.StandardChartTheme.createJFreeTheme();
        theme.setChartBackgroundPaint(new Color(0, 0, 0, 0));
        theme.setRegularFont(new Font("Sansserif", Font.BOLD, 12));
        theme.setLegendBackgroundPaint(new Color(255, 255, 255, 125));
        theme.setPlotBackgroundPaint(new Color(0, 0, 0, 0));
//        theme.setAxisLabelPaint(new Color(255,255,255));
        theme.setLegendItemPaint(new Color(0, 0, 0));
        theme.setPlotOutlinePaint(new Color(0, 0, 0, 0));
        theme.apply(chart);
        chart.setAntiAlias(true);

        chart.setTextAntiAlias(true);
        chart.setBorderPaint(new Color(0, 0, 0, 0));

        LegendTitle legend = chart.getLegend();
        legend.setFrame(new BlockBorder(Color.white));

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(370, 250));
        chartPanel.setPopupMenu(null);
        chartPanel.setBackground(new Color(0x0000005, true));

        PiePlot plot = (PiePlot) chart.getPlot();

        plot.setSectionPaint("Available Room", new Color(90, 140, 206));
        plot.setSectionPaint("Maintenance", new Color(0x101442));

        piechart = new JPanel();
        piechart.add(chartPanel);

//        this.add(piechart, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.8, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
        this.add(piechart, new GBCBuilder(GridBagConstraints.NONE, 1, 1, 0, 1).setAnchor(GridBagConstraints.NORTH));
        piechart.setSize(new Dimension(10, 50));
        piechart.setBackground(new Color(0x0343477, true));
        chartPanel.repaint();
    }

    private void clock() {
        JLabel clock = new JLabel("00:00:00");
        clock.setFont(new Font("Sansserif", Font.BOLD, 48));
        SwingWorker<?, ?> worker = new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() throws Exception {
                while (true) {
                    synchronized (this) {
                        DateTime dt = new DateTime(System.currentTimeMillis());
                        clock.setText(String.format("%d:%02d:%02d", dt.getHours(), dt.getMinutes(), dt.getSeconds()));
                        this.wait(1000);
                    }
                }
            }
        };
        this.add(clock, new GBCBuilder(GridBagConstraints.CENTER, 1, 1, 0, 3).getGBC());
        worker.execute();
    }

    private void calendar() {
        //      .........Calendar.........
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

//        panel.setPreferredSize(new Dimension(1, 1));
//        this.add(panel, new GridBagConstraints(0, 8, 0, 3, 0, 10, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
        this.add(panel, new GBCBuilder(GridBagConstraints.BOTH, 1, 1, 0, 4).getGBC());

    }

}



