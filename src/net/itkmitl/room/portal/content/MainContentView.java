package net.itkmitl.room.portal.content;

import net.itkmitl.room.portal.View;
import net.itkmitl.room.portal.components.TransparentPanel;
import net.itkmitl.room.portal.content.components.Dashboard;
import net.itkmitl.room.portal.content.components.Selector;
import net.itkmitl.room.portal.dashboard.components.ContentPanel;

import java.awt.*;

public class MainContentView extends View{
    private TransparentPanel contentPanel;
    private Dashboard dashboard;
    private Selector selector;

    public MainContentView(){
        super();
    }
    @Override
    protected void initialize() {
        contentPanel = new TransparentPanel(new CardLayout());
        dashboard = new Dashboard();
        selector = new Selector();
        contentPanel.add(dashboard, "Dashboard");
        contentPanel.add(selector, "Selector");
        this.add(contentPanel);
    }

    public Dashboard getDashboard() {
        return this.dashboard;
    }
    public ContentPanel getContentPanel(){
        return (ContentPanel) this.contentPanel;
    }
}
