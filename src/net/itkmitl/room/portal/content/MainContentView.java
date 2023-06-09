package net.itkmitl.room.portal.content;

import net.itkmitl.room.portal.View;
import net.itkmitl.room.portal.components.TransparentPanel;
import net.itkmitl.room.portal.content.components.DarkPane;
import net.itkmitl.room.portal.content.components.Dashboard;
import net.itkmitl.room.portal.content.components.History;
import net.itkmitl.room.portal.content.components.Selector;

import java.awt.*;

public class MainContentView extends View {
    private static final long serialVersionUID = 3256816243621773023L;
    public static DarkPane glassPane;
    private TransparentPanel contentPanel;
    private Dashboard dashboard;
    private Selector selector;
    private History history;


    public MainContentView() {
        super();
    }

    @Override
    protected void initialize() {
        contentPanel = new TransparentPanel(new CardLayout());
        dashboard = new Dashboard();
        selector = new Selector();
        history = new History();
        glassPane = new DarkPane();
        glassPane.setText("Loading data...");
        glassPane.setSpinnerVisibility(true);
        contentPanel.add(dashboard, "Dashboard");
        contentPanel.add(selector, "Selector");
        contentPanel.add(history, "History");
        this.add(contentPanel);
        setGlassPane(glassPane);
        glassPane.setVisible(true);
        glassPane.setEnabled(true);

    }

    public Dashboard getDashboard() {
        return this.dashboard;
    }

    public TransparentPanel getContentPanel() {
        return this.contentPanel;
    }

    public Selector getSelector() {
        return this.selector;
    }

    public History getHistory() {
        return this.history;
    }
}
