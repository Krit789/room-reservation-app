package net.itkmitl.room.libs.phatsanphon.repository;

import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.entity.Entity;

import java.util.ArrayList;

public class Repository<T extends Entity> {
    private final Class<T> c;
    private final FewQuery query;

    protected Repository(Class<T> clazz, FewQuery query) {

        this.c = clazz;
        this.query = query;
    }

    protected FewQuery getQuery() {
        return this.query;
    }

    protected ArrayList<T> maps(FewQuery result) throws Exception{
        ArrayList<T> objects = new ArrayList<>();

        while (result.nextBind()) {
            try {
                T object = this.c.getDeclaredConstructor().newInstance();
                object.processQuery(result);
                objects.add(object);
            } catch (Exception e) {
                throw e;
            }
        }

        return objects;
    }

    protected T map(FewQuery result) throws Exception {
        ArrayList<T> tempArray = this.maps(result);
        return !tempArray.isEmpty() ? tempArray.get(0) : null;
    }
}
