package net.itkmitl.room.portal.content.components.dashboard;

import net.itkmitl.room.portal.components.TransparentPanel;

import javax.swing.*;
import java.io.Serial;

public class ContentPanel extends TransparentPanel {
    @Serial
    private static final long serialVersionUID = 6701138923212123618L;

    public ContentPanel() {
//        super(30, 30, Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);
    }
}
