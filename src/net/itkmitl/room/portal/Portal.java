package net.itkmitl.room.portal;

import com.formdev.flatlaf.FlatIntelliJLaf;
import net.itkmitl.room.MacConfig;
import net.itkmitl.room.libs.phatsanphon.UIConfig;

import javax.swing.*;

public abstract class Portal {
    protected Portal() {
        UIConfig.setLookAndFeel();
    }

    public abstract void run();
}
