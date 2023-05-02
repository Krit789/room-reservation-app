package net.itkmitl.room.portal.dashboard.components;

import net.itkmitl.room.portal.components.RoundedPanel;

import javax.swing.BoxLayout;
import java.awt.*;

public class ContentPanel extends RoundedPanel {
    /**
     *
     */
    private static final long serialVersionUID = 6701138923212123618L;

    public ContentPanel() {
        super(30, 30, Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);
    }
}
