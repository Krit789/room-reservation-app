package net.itkmitl.room.portal.admin.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import net.itkmitl.room.portal.components.GBCBuilder;

public class DataListTableView {
    protected final JInternalFrame frame;
    protected final JPanel titlePanel, northPanel, northButtonPanel;
    public final JLabel pageTitle, pageSubtitle;
    public JTable table;
    protected final JScrollPane scrollPane;
    public final JButton viewEntryButton, reloadButton;

    public DataListTableView() {
        frame = new JInternalFrame("Table", true, true, true, true);
        frame.setFrameIcon(new ImageIcon("resource/icons/tableview-16px.png"));
        frame.setLayout(new BorderLayout());
        titlePanel = new JPanel();
        pageTitle = new JLabel();
        pageSubtitle = new JLabel();

        northPanel = new JPanel();
        northPanel.setLayout(new GridBagLayout());
        titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        northPanel.add(titlePanel, new GBCBuilder(GridBagConstraints.NONE,1,0.9,0,0, new Insets(0,5,0,0)).setAnchor(GridBagConstraints.WEST));
        northButtonPanel = new JPanel();
        northButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        reloadButton = new JButton("Reload");
        reloadButton.setIcon(new ImageIcon("resource/icons/reload-16px.png"));
        viewEntryButton = new JButton("View");
        viewEntryButton.setIcon(new ImageIcon("resource/icons/view-16px.png"));
        northButtonPanel.add(new JLabel("Operations"));
        northButtonPanel.add(reloadButton);
        northButtonPanel.add(viewEntryButton);
        northPanel.add(new JSeparator(), new GBCBuilder(GridBagConstraints.HORIZONTAL,1,0.01,0,1).getGBC());
        northPanel.add(northButtonPanel, new GBCBuilder(GridBagConstraints.NONE,1,0.09,0,2, new Insets(0,5,0,5)).setAnchor(GridBagConstraints.WEST));
        frame.add(northPanel, BorderLayout.NORTH);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(pageTitle);
        titlePanel.add(pageSubtitle);
        pageTitle.setFont(new Font("Arial", Font.BOLD, 25));
        titlePanel.setBorder(new EmptyBorder(10, 10, 5, 10));
        pageTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        pageSubtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        table = new JTable() {
            /**
			 *
			 */
			private static final long serialVersionUID = 7614361499285925400L;
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
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.createHorizontalScrollBar();
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.pack();
    }

    public JInternalFrame getFrame() {
        return frame;
    }
}
