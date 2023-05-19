package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.libs.phatsanphon.entity.Entity;

public interface DataManager {
    public abstract void databaseLoader(int id);
    public abstract void databaseCommiter(Entity entity, int mode);
    public abstract void dataLoader();
}
