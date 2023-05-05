package net.itkmitl.room.portal.content;

import net.itkmitl.room.portal.View;
import net.itkmitl.room.portal.components.TransparentPanel;
import net.itkmitl.room.portal.content.components.Dashboard;

import javax.swing.*;
import java.awt.*;

public class MainContentView extends View{
    private TransparentPanel contentPanel;
    private Dashboard dashboard;

    public MainContentView(){
        super();
    }
    @Override
    protected void initialize() {
        contentPanel = new TransparentPanel(new CardLayout());
        dashboard = new Dashboard();
        contentPanel.add(dashboard, "Dashboard");
        this.add(contentPanel);
    }
}
