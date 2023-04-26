package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.portal.admin.models.DataListEditableModel;
import net.itkmitl.room.portal.admin.models.DataListTableModel;
import net.itkmitl.room.portal.admin.views.DataListEditableView;
import net.itkmitl.room.portal.admin.views.DataListTableView;

public class DataListEditableController extends DataListTableController{
    public DataListEditableView view;
    public DataListEditableModel model;
    public DataListEditableController(){
        this(new DataListEditableModel());
    }
    public DataListEditableController(DataListEditableModel model){
        this.view = new DataListEditableView();
        this.model = model;
        view.getFrame().setTitle(model.getTitle());
        view.pageTitle.setText(model.getPageTitle());
        view.pageSubtitle.setText(model.getPageSubtitle());
        view.table.setModel(model.getDtm());
    }
}
