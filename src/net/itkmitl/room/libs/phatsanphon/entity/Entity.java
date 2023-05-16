package net.itkmitl.room.libs.phatsanphon.entity;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;

/**
 * Entity
 */
public abstract class Entity {
    private final FewQuery db;

    public Entity(FewQuery query) throws Exception {
        db = LaewTaeDB.getDB();
        this.processQuery(query);
    }

    public Entity() throws Exception {
        db = LaewTaeDB.getDB();
    }

    protected FewQuery getDB() {
        return this.db;
    }

    public abstract void processQuery(FewQuery query) throws Exception;
}
