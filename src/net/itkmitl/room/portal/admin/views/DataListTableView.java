package net.itkmitl.room.portal.admin.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

public class DataListTableView {
    private final JInternalFrame frame;
    private final JPanel titlePanel;
    public final JLabel pageTitle, pageSubtitle;
    public final JTable table;
    private final JScrollPane scrollPane;

    public DataListTableView() {
        frame = new JInternalFrame("Table", true, true, true, true);
        frame.setFrameIcon(new ImageIcon("resource/icons/tableview-16px.png"));
        frame.setLayout(new BorderLayout());
        titlePanel = new JPanel();
        pageTitle = new JLabel();
        pageSubtitle = new JLabel();

        titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.add(titlePanel, BorderLayout.NORTH);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(pageTitle);
        titlePanel.add(pageSubtitle);
        pageTitle.setFont(new Font("Arial", Font.BOLD, 25));
        titlePanel.setBorder(new EmptyBorder(10, 10, 5, 10));
        pageTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        pageSubtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        table = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
                return component;
            }
        };
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.createHorizontalScrollBar();
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
    }

    public JInternalFrame getFrame() {
        return frame;
    }
}
