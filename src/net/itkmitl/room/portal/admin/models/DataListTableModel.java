package net.itkmitl.room.portal.admin.models;

import javax.swing.table.DefaultTableModel;

public class DataListTableModel {
    private String title, pageTitle, pageSubtitle;
    private DefaultTableModel dtm;

    public DataListTableModel() {
        this("Table", "Untitled Page", "3 Data Rows", new DefaultTableModel(new Object[][]{{1, 2, 3, true}, {1, 2, 3, true}, {1, 2, 3, true}}, new String[]{"A", "B", "C", "D"}));
    }

    public DataListTableModel(String title, String pageTitle, String pageSubtitle, DefaultTableModel dtm) {
        this.title = title;
        this.pageTitle = pageTitle;
        this.pageSubtitle = pageSubtitle;
        this.dtm = dtm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageSubtitle() {
        return pageSubtitle;
    }

    public void setPageSubtitle(String pageSubtitle) {
        this.pageSubtitle = pageSubtitle;
    }

    public DefaultTableModel getDtm() {
        return dtm;
    }

    public void setDtm(DefaultTableModel dtm) {
        this.dtm = dtm;
    }
}
