package net.itkmitl.room.portal.admin.models;

public class DataListTableModel {
    private String title, pageTitle;
    private String[] columnHeader;
    private Object[][] columnData;

    public DataListTableModel(){
        this("Table", "Untitled Page", new String[]{"A", "B", "C", "D"}, new Object[][]{{1, 2 ,3 ,true},{1, 2 ,3 ,true},{1, 2 ,3 ,true}});
    }
    public DataListTableModel(String title, String pageTitle, String[] columnHeader, Object[][] columnData){
        this.title = title;
        this.pageTitle = pageTitle;
        this.columnHeader = columnHeader;
        this.columnData = columnData;
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

    public String[] getColumnHeader() {
        return columnHeader;
    }

    public void setColumnHeader(String[] columnHeader) {
        this.columnHeader = columnHeader;
    }

    public Object[][] getColumnData() {
        return columnData;
    }

    public void setColumnData(Object[][] columnData) {
        this.columnData = columnData;
    }
}
