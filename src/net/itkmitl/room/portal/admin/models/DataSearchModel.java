package net.itkmitl.room.portal.admin.models;

import java.util.ArrayList;

public class DataSearchModel {
    private String title;
    private ArrayList<String> radioButtonLabel;

    public DataSearchModel() {
        this(new ArrayList<>(), "Untitled Search");
    }

    public DataSearchModel(ArrayList<String> radioButtonLabel, String title) {
        this.radioButtonLabel = radioButtonLabel;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getRadioButtonLabel() {
        return radioButtonLabel;
    }

    public void setRadioButtonLabel(ArrayList<String> radioButtonLabel) {
        this.radioButtonLabel = radioButtonLabel;
    }
}
