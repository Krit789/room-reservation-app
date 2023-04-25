package net.itkmitl.room.portal;

import net.itkmitl.room.libs.phatsanphon.UIConfig;

public abstract class Portal {
    protected Portal() {
        UIConfig.setLookAndFeel();
    }

    public abstract void run();
}
