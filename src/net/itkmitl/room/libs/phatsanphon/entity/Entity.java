package net.itkmitl.room.libs.phatsanphon.entity;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;

/**
 * Entity
 */
public abstract class Entity {
    private final FewQuery db;

    public Entity(FewQuery query) throws Exception {
        db = RVDB.getDB();
        this.processQuery(query);
    };

    public Entity() throws Exception {
        db = RVDB.getDB();
    }

    protected FewQuery getDB() {
        return this.db;
    }

    public abstract void processQuery(FewQuery query) throws Exception;
}
