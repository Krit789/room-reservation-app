package net.itkmitl.room.libs.phatsanphon.entity;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;

/**
 * Entity
 */
public abstract class Entity {
    private final FewQuery db = RVDB.getDB();

    public Entity(FewQuery query) {
        this.processQuery(query);
    };

    public Entity() {}

    protected FewQuery getDB() {
        return this.db;
    }

    public abstract void processQuery(FewQuery query);
}
