package net.itkmitl.room;

import net.itkmitl.room.libs.peeranat.config.FewConfig;
import net.itkmitl.room.portal.account.EntryPortal;
import net.itkmitl.room.portal.admin.components.OOBE.OOBEController;

import java.io.File;

public class ApplicationMain {
    public static void main(String[] args) throws Exception {
        File cfg = new File("config.yml");
        if (!cfg.exists()) {
            OOBEController.main(new String[]{});
        } else if (cfg.exists() && new FewConfig(cfg).getValue("first_run") == null) {
            OOBEController.main(new String[]{});
        } else {
            EntryPortal.main(new String[]{});
        }
    }
}
