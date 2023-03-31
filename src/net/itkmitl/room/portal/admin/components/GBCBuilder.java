package net.itkmitl.room.portal.admin.components;

import java.awt.*;

public class GBCBuilder{
    private final GridBagConstraints gbc;
    public GBCBuilder(int fill, double weightx, int gridx, int gridy){
        gbc = new GridBagConstraints();
        gbc.fill = fill;
        gbc.weightx = weightx;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
    }
    public GBCBuilder(int fill, double weightx, int gridx, int gridy, Insets insets){
        gbc = new GridBagConstraints();
        gbc.fill = fill;
        gbc.weightx = weightx;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.insets = insets;
    }
    public GBCBuilder(){
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }
    public GridBagConstraints setInset(Insets insets){
        gbc.insets = insets;
        return gbc;
    }

    public GridBagConstraints getGBC(){
        return gbc;
    }

    public GridBagConstraints setPadding(int ipady, int ipadx){
        gbc.ipady = ipady;
        gbc.ipadx = ipadx;
        return gbc;
    }

    public GridBagConstraints setAnchor(int anchor){
        gbc.anchor = anchor;
        return gbc;
    }

}