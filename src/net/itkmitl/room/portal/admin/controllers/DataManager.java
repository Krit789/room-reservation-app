package net.itkmitl.room.portal.admin.controllers;

import net.itkmitl.room.libs.phatsanphon.entity.Entity;

public interface DataManager {
    void databaseLoader(int id);
    void databaseCommiter(Entity entity, int mode);
    void dataLoader();
}
