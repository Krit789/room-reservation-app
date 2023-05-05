package net.itkmitl.room.portal.components;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class TransparentPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 729961928996065079L;

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
