package net.itkmitl.room.portal.admin.views;

import net.itkmitl.room.portal.components.GBCBuilder;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class DataListTableView {
    protected final JInternalFrame frame;
    protected final JPanel titlePanel, northPanel, northButtonPanel;
    public final JLabel pageTitle, pageSubtitle;
    public JTable table;
    protected final JScrollPane scrollPane;
    protected final JButton viewEntryButton;

    public DataListTableView() {
        frame = new JInternalFrame("Table", true, true, true, true);
        frame.setFrameIcon(new ImageIcon("resource/icons/tableview-16px.png"));
        frame.setLayout(new BorderLayout());
        titlePanel = new JPanel();
        pageTitle = new JLabel();
        pageSubtitle = new JLabel();

        northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1,2));
        titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        northPanel.add(titlePanel);
        northButtonPanel = new JPanel();
        northButtonPanel.setLayout(new GridBagLayout());

        viewEntryButton = new JButton("View Selected");
        northButtonPanel.add(viewEntryButton, new GBCBuilder(GridBagConstraints.NONE,0.5,1,1,0, new Insets(0,5,0,5)).setAnchor(GridBagConstraints.EAST));
        northPanel.add(northButtonPanel);
        frame.add(northPanel, BorderLayout.NORTH);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(pageTitle);
        titlePanel.add(pageSubtitle);
        pageTitle.setFont(new Font("Arial", Font.BOLD, 25));
        titlePanel.setBorder(new EmptyBorder(10, 10, 5, 10));
        pageTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        pageSubtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            };
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
                return component;
            }
        };
//        table.setColumnSelectionAllowed(false);
//        table.setCellSelectionEnabled(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.createHorizontalScrollBar();
        frame.add(scrollPane, BorderLayout.CENTER);


        if (this instanceof DataListTableView){
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.pack();
        }

    }

    public JInternalFrame getFrame() {
        return frame;
    }
}
