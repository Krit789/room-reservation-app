package net.itkmitl.room.portal.admin.views;

import javax.swing.JButton;
import javax.swing.JInternalFrame;

import net.itkmitl.room.libs.peeranat.util.FewFile;

public class DataListEditableView extends DataListTableView {
    public final JButton createButton, editSelectedButton, deletedSelectedButton;

    public DataListEditableView() {
        super();
        frame.setFrameIcon(FewFile.getImage("icons/tableedit-16px.png"));
        frame.setTitle("Editable Table");

        createButton = new JButton("Create");
        createButton.setIcon(FewFile.getImage("icons/create-16px.png"));


        editSelectedButton = new JButton("Edit");
        editSelectedButton.setIcon(FewFile.getImage("icons/update-16px.png"));


        deletedSelectedButton = new JButton("Delete");
        deletedSelectedButton.setIcon(FewFile.getImage("icons/delete-16px.png"));

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
