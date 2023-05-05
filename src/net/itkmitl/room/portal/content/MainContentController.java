package net.itkmitl.room.portal.content;

import net.itkmitl.room.libs.phatsanphon.entity.User;
import net.itkmitl.room.libs.store.AppStore;
import net.itkmitl.room.portal.Controller;
import net.itkmitl.room.portal.dashboard.DashboardView;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainContentController extends Controller implements ActionListener {
    private final MainContentView view;
    private AppStore store = AppStore.getAppStore();
    public MainContentController(MainContentView view){
        this.view = view;
    }
    public MainContentView getView() {
        return view;
    }
    @Override
    protected void start() {
        this.initialize();
    }

    @Override
    protected void initialize() {
        this.initializeListener();
    }

    @Override
    protected void initializeListener() {
        if (((User)store.select("user")).getRole().getLevel() >= 10){
            this.getView().optionMenuItem1.addActionListener(this);
        }
        this.getView().helpMenuItem1.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
