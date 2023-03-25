package net.itkmitl.room.libs.peeranat.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FewUpdateMySQL extends FewMySQLBuilder {

    private Map<String, Object> toUpdate;
    private ArrayList<FewMySQLWhere> wheres;

    private int limit;

    public FewUpdateMySQL() {
        toUpdate = new HashMap<>();
        wheres = new ArrayList<>();
    }

    public FewUpdateMySQL set(String key, Object value) {
        this.toUpdate.put(key, value);
        return this;
    }
    public FewUpdateMySQL where(String column, Object value) {
        this.wheres.add(new FewMySQLWhere(column, FewMySQLCompare.EQUAL, value, FewMySQLOperator.AND));
        return this;
    }
    public FewUpdateMySQL where(String column, FewMySQLCompare compare, Object value) {
        this.wheres.add(new FewMySQLWhere(column, compare, value, FewMySQLOperator.AND));
        return this;
    }
    public FewUpdateMySQL where(String column, FewMySQLCompare compare, Object value, FewMySQLOperator operator) {
        this.wheres.add(new FewMySQLWhere(column, compare, value, operator));
        return this;
    }

    public FewUpdateMySQL limit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public String builder() {
        return null;
    }
}
