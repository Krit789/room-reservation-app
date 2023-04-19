package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.portal.admin.models.DataListTableModel;
import net.itkmitl.room.portal.admin.views.DataListTableView;

import javax.swing.table.DefaultTableModel;

public class DataListTableController {
    DataListTableView view;
    DataListTableModel model;

    public DataListTableController(){
        this(new DataListTableModel());
    }
    public DataListTableController(DataListTableModel model){
        this.view = new DataListTableView();
        this.model = model;
        view.getFrame().setTitle(model.getTitle());
        view.pageTitle.setText(model.getPageTitle());
        view.table.setModel(new DefaultTableModel(model.getColumnData(), model.getColumnHeader()));
    }
}
