package net.itkmitl.room.portal.content;

import java.awt.*;

import net.itkmitl.room.portal.View;
import net.itkmitl.room.portal.components.TransparentPanel;
import net.itkmitl.room.portal.content.components.Dashboard;
import net.itkmitl.room.portal.content.components.LoadingPane;
import net.itkmitl.room.portal.content.components.Selector;

import javax.swing.*;

public class MainContentView extends View{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3256816243621773023L;
	private TransparentPanel contentPanel;
    private Dashboard dashboard;
    private Selector selector;
    public static LoadingPane glassPane;


    public MainContentView(){
        super();
    }
    @Override
    protected void initialize() {
        contentPanel = new TransparentPanel(new CardLayout());
        dashboard = new Dashboard();
        selector = new Selector();
        glassPane = new LoadingPane();
        contentPanel.add(dashboard, "Dashboard");
        contentPanel.add(selector, "Selector");
        this.add(contentPanel);
        setGlassPane(glassPane);
        glassPane.setVisible(true);
        glassPane.setEnabled(true);

    }

    public Dashboard getDashboard() {
        return this.dashboard;
    }
    public TransparentPanel getContentPanel(){
        return (TransparentPanel) this.contentPanel;
    }

    public Selector getSelector(){
        return (Selector) this.selector;
    }
}
