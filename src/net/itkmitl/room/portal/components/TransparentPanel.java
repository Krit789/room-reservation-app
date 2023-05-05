package net.itkmitl.room.portal.components;

import javax.swing.*;
import java.awt.*;

public class TransparentPanel extends JPanel {
    {
        setOpaque(false);
    }
    public TransparentPanel(){
        this.setLayout(new BorderLayout());
    }
    public TransparentPanel(LayoutManager lm){
        this.setLayout(lm);
    }
}
