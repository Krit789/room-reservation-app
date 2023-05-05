package net.itkmitl.room.portal.selectBuilding;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.itkmitl.room.portal.Controller;
import net.itkmitl.room.portal.components.LeftSelectorBox;
import net.itkmitl.room.portal.components.LeftSelectorPanel;
import net.itkmitl.room.portal.dashboard.Dashboard;

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
        for(int i = this.getView().getSelectorPanel().leftBoxHolder.size() - 1; i >= 0; i--) {
            this.getView().getSelectorPanel().leftBoxHolder.get(i).addActionListener(this);
        }
        ((LeftSelectorPanel)this.getView().leftSelectorPanel).backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof LeftSelectorBox){
            for(int i = this.getView().getSelectorPanel().leftBoxHolder.size() - 1; i >= 0; i--) {
                if(e.getSource().equals(this.getView().getSelectorPanel().leftBoxHolder.get(i))){
                    this.getView().testLabel.setText("Box ID = " + this.getView().getSelectorPanel().leftBoxHolder.get(i).boxID);

                    //put roomBox query thingy here
                }
            }
        }else if(e.getSource().equals(((LeftSelectorPanel)this.getView().leftSelectorPanel).backButton)){
            changeFrame(getView(), new Dashboard());
        }
    }
}
