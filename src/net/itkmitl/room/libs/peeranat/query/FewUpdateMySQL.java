package net.itkmitl.room.libs.peeranat.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FewUpdateMySQL implements FewMySQLBuilder {

    private String table;
    private final Map<String, Object> toUpdate;
    private final ArrayList<FewMySQLWhere> wheres;

    private int limit;

    public FewUpdateMySQL() {
        toUpdate = new HashMap<>();
        wheres = new ArrayList<>();
    }

    public FewUpdateMySQL table(String table) {
        this.table = table;
        return this;
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
        StringBuilder output = new StringBuilder();
        output.append("UPDATE ");
        output.append("`").append(this.table).append("` SET ");

        for (Entry<String, Object> entry : this.toUpdate.entrySet()) {
            output.append("`").append(entry.getKey()).append("` = '").append(entry.getValue()).append("', ");
        }
        output.delete(output.length() - 2, output.length());

        if (!this.wheres.isEmpty()) {
            boolean isAlreadyWhere = false;
            output.append(" WHERE");
            for (FewMySQLWhere where : this.wheres) {
                if (isAlreadyWhere) {
                    output.append(" ").append(where.getOperator().getValue()).append(" ");
                }
                output.append(" ").append(where.getWhereString()).append(" ");
                isAlreadyWhere = true;
            }
        }
        if (this.limit > 0) {
            output.append(" LIMIT ").append(this.limit);
        }
        return output.toString();
    }
}
