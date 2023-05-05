package net.itkmitl.room.portal.admin.views;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;

public class DataListEditableView extends DataListTableView{
    public final JButton createButton, editSelectedButton, deletedSelectedButton;
    public DataListEditableView(){
        super();
        frame.setFrameIcon(new ImageIcon("resource/icons/tableedit-16px.png"));
        frame.setTitle("Editable Table");

        createButton = new JButton("Create");
        createButton.setIcon(new ImageIcon("resource/icons/create-16px.png"));


        editSelectedButton = new JButton("Edit");
        editSelectedButton.setIcon(new ImageIcon("resource/icons/update-16px.png"));


        deletedSelectedButton = new JButton("Delete");
        deletedSelectedButton.setIcon(new ImageIcon("resource/icons/delete-16px.png"));

        northButtonPanel.add(createButton);
        northButtonPanel.add(editSelectedButton);
        northButtonPanel.add(deletedSelectedButton);
        frame.pack();
    }
    @Override
    public JInternalFrame getFrame() {
        return frame;
    }

}
