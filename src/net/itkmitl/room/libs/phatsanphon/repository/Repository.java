package net.itkmitl.room.libs.phatsanphon.repository;

import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.entity.Entity;
import net.itkmitl.room.libs.phatsanphon.entity.User;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Repository<T extends Entity> {
    private final Class<T> c;

    protected Repository(Class<T> c) {
        this.c = c;
    }

    protected ArrayList<T> maps(FewQuery result) {
        ArrayList<T> objects = new ArrayList<>();

        while (result.nextBind()) {
            try {
                T object = this.c.getDeclaredConstructor().newInstance();
                object.processQuery(result);
                objects.add(object);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return objects;
    }

    protected T map(FewQuery result) {
        T object = null;

        while (result.nextBind()) {
            try {
                object = this.c.getDeclaredConstructor().newInstance();
                object.processQuery(result);
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        return object;
    }
}
