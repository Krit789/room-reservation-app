package net.itkmitl.room;

import net.itkmitl.room.enums.EnumRoomState;
import net.itkmitl.room.libs.peeranat.config.FewConfig;
import net.itkmitl.room.libs.peeranat.util.FewRoom;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.portal.account.EntryPortal;
import net.itkmitl.room.portal.admin.components.OOBE.OOBEController;
import net.itkmitl.room.portal.content.MainContentPortal;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ApplicationMain {
    public static void main(String[] args) throws Exception {
        FewConfig config = new FewConfig(new File("config.yml"));
        if (config.getValue("first_run") == null){
            OOBEController.main(new String[]{});
        } else {
            EntryPortal.main(new String[]{});
        }
    }
}
