package net.itkmitl.room.portal.dashboard.components;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ContentPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6701138923212123618L;

	public ContentPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);
    }
}
