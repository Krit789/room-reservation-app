package net.itkmitl.room.portal.components;

import net.itkmitl.room.portal.admin.BaseWindow;

import javax.swing.*;
import java.awt.*;

public class LoadingDialog {
    public final JDialog dialog;
    private final JLabel loadingImage, messageLabel;

    public LoadingDialog() {
        dialog = new JDialog(BaseWindow.baseFrame, "Please Wait");
        dialog.setModalityType(Dialog.ModalityType.MODELESS);
        dialog.setLayout(new GridBagLayout());
        loadingImage = new JLabel(new ImageIcon("resource/icons/hourglass-32px.gif"));
        messageLabel = new JLabel("Hang Tight! We're loading data just for you");

        dialog.add(loadingImage, new GBCBuilder(GridBagConstraints.CENTER, 0.2, 0, 0, 0).setInset(new Insets(5, 15, 5, 5)));
        dialog.add(messageLabel, new GBCBuilder(GridBagConstraints.CENTER, 0.8, 0, 1, 0).setInset(new Insets(5, 5, 5, 15)));
        dialog.setSize(320, 85);
        dialog.setLocationRelativeTo(BaseWindow.baseFrame);
    }
}
