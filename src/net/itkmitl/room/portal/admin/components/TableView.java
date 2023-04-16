package net.itkmitl.room.portal.admin.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TableView extends InternalFrame{
    private final JInternalFrame frame;
    private final JPanel titlePanel;
    private final JLabel pageTitle;
    private final JTable table;
    private final JScrollPane scrollPane;

    public TableView() {
        frame = new JInternalFrame("Table View", true, true, true, true  );
        frame.setLayout(new BorderLayout());
        titlePanel = new JPanel();
        pageTitle = new JLabel("User Table");
        titlePanel.add(pageTitle);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        pageTitle.setFont(new Font("Arial", Font.BOLD, 25));
        titlePanel.setBorder(new EmptyBorder(10, 10, 5, 10));
        frame.add(titlePanel, BorderLayout.NORTH);

        String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};

        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", Integer.valueOf(5), Boolean.valueOf(false)},
                {"John", "Doe",
                        "Rowing", Integer.valueOf(3), Boolean.valueOf(true)},
                {"Sue", "Black",
                        "Knitting", Integer.valueOf(2), Boolean.valueOf(false)},
                {"Jane", "White",
                        "Speed reading", Integer.valueOf(20), Boolean.valueOf(true)},
                {"Joe", "Brown",
                        "Pool", Integer.valueOf(10), Boolean.valueOf(false)}
        };

        table = new JTable(data, columnNames);
        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
//        frame.setMinimumSize(frame.getSize());
    }
    public JInternalFrame getFrame(){
        return frame;
    }
}
