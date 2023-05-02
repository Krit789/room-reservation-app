package net.itkmitl.room.portal.selectBuilding;

import net.itkmitl.room.portal.Controller;
import net.itkmitl.room.portal.account.EntryView;
import net.itkmitl.room.portal.components.LeftSelectorBox;
import net.itkmitl.room.portal.dashboard.DashboardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectBuildingController extends Controller implements ActionListener {
    private final SelectBuildingView view;

    public SelectBuildingController(SelectBuildingView view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.initialize();
    }

    @Override
    public void initialize() {
        this.initializeListener();
    }

    public SelectBuildingView getView(){
        return view;
    }
    @Override
    public void initializeListener() {
        for(int i = this.getView().getSelectorPanel().boxHolder.size() - 1; i >= 0; i--) {
            this.getView().getSelectorPanel().boxHolder.get(i).addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof LeftSelectorBox){
            for(int i = this.getView().getSelectorPanel().boxHolder.size() - 1; i >= 0; i--) {
                if(e.getSource().equals(this.getView().getSelectorPanel().boxHolder.get(i))){
                    this.getView().testLabel.setText("Box ID = " + this.getView().getSelectorPanel().boxHolder.get(i).boxID);
                    //put roomBox query thingy here
                }
            }
        }
    }
}
