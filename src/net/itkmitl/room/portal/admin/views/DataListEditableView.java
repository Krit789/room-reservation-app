package net.itkmitl.room.portal.admin.views;

import net.itkmitl.room.portal.components.GBCBuilder;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

public class DataListEditableView extends DataListTableView{
    public final JButton editSelectedButton;
    public DataListEditableView(){
        super();
        frame.setFrameIcon(new ImageIcon("resource/icons/tableedit-16px.png"));
        frame.setTitle("Editable Table");
        editSelectedButton = new JButton("Edit Selected");
        northButtonPanel.add(editSelectedButton,new GBCBuilder(GridBagConstraints.NONE,0.5,1,0,0, new Insets(0,5,0,5)).setAnchor(GridBagConstraints.EAST));

    }
    @Override
    public JInternalFrame getFrame() {
        return frame;
    }

}
