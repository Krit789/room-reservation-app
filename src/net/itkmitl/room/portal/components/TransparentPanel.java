package net.itkmitl.room.portal.components;

import javax.swing.*;
import java.awt.*;

public class TransparentPanel extends JPanel {
    private static final long serialVersionUID = 729961928996065079L;

    public TransparentPanel() {
        this(new BorderLayout());
    }

    public TransparentPanel(LayoutManager lm) {
        setLayout(lm);
        setOpaque(false);
    }
}
