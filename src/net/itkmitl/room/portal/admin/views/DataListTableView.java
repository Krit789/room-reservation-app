package net.itkmitl.room.portal.admin.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class DataListTableView {
    private final JInternalFrame frame;
    private final JPanel titlePanel;
    public final JLabel pageTitle;
    public final JTable table;
    private final JScrollPane scrollPane;

    public DataListTableView() {
        frame = new JInternalFrame("Table", true, true, true, true  );
        frame.setFrameIcon(new ImageIcon("resource/icons/tableview-16px.png"));
        frame.setLayout(new BorderLayout());
        titlePanel = new JPanel();
        pageTitle = new JLabel();
        titlePanel.add(pageTitle);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        pageTitle.setFont(new Font("Arial", Font.BOLD, 25));
        titlePanel.setBorder(new EmptyBorder(10, 10, 5, 10));
        frame.add(titlePanel, BorderLayout.NORTH);
        table = new JTable();
        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
    }
    public JInternalFrame getFrame(){
        return frame;
    }
}
