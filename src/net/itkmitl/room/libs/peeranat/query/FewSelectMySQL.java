package net.itkmitl.room.libs.peeranat.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FewSelectMySQL extends FewMySQLBuilder {


    private ArrayList<String> selects;
    private ArrayList<FewMySQLWhere> wheres;
    private Map<String, FewMySQLOrder> orderBy;

    private int limit;
    private int offset;

    public FewSelectMySQL() {
        this.selects = new ArrayList<>();
        this.wheres = new ArrayList<>();
        this.orderBy = new HashMap<>();
    }

    public FewSelectMySQL select(String... vars) {
        selects.addAll(Arrays.asList(vars));
        return this;
    }
    public FewSelectMySQL where(String column, Object value) {
        this.wheres.add(new FewMySQLWhere(column, FewMySQLCompare.EQUAL, value, FewMySQLOperator.AND));
        return this;
    }
    public FewSelectMySQL where(String column, FewMySQLCompare compare, Object value) {
        this.wheres.add(new FewMySQLWhere(column, compare, value, FewMySQLOperator.AND));
        return this;
    }
    public FewSelectMySQL where(String column, FewMySQLCompare compare, Object value, FewMySQLOperator operator) {
        this.wheres.add(new FewMySQLWhere(column, compare, value, operator));
        return this;
    }
    public FewSelectMySQL limit(int limit) {
        this.limit = limit;
        return this;
    }
    public FewSelectMySQL offset(int offset) {
        this.offset = offset;
        return this;
    }
    public FewSelectMySQL orderBy(String column, FewMySQLOrder orderBy) {
        this.orderBy.put(column, orderBy);
        return this;
    }

    @Override
    public String builder() {
        StringBuilder output = new StringBuilder();
        output.append("SELECT ");

        for (String select : this.selects) {
            output.append(select).append(", ");
        }
        output.delete(output.length() - 2, output.length());
        output.append(" FROM `").append(this.table).append("`");
        if (!this.wheres.isEmpty()) {
            boolean isAlreadyWhere = false;
            output.append(" WHERE ");
            for (FewMySQLWhere where : this.wheres) {
                if (isAlreadyWhere) {
                    output.append(where.getOperator().getValue()).append(" ");
                }
                output.append(where.getWhereString()).append(" ");
                isAlreadyWhere = true;
            }
        }
        if (!this.orderBy.isEmpty()) {
            output.append(" ORDER BY ");
            for (Map.Entry<String, FewMySQLOrder> entry : this.orderBy.entrySet()) {
                output.append(entry.getKey()).append(" ").append(entry.getValue()).append(", ");
            }
            output.delete(output.length() - 2, output.length());
        }
        if (this.limit > 0) {
            output.append(" LIMIT ");
            if (this.offset == 0) {
                output.append(this.limit);
            } else if (this.offset > 0) {
                output.append(this.offset).append(", ").append(this.limit);
            }
        }
        return output.toString();
    }
}
