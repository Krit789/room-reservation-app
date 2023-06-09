package net.itkmitl.room.libs.peeranat.query;

import java.util.HashMap;
import java.util.Map;

public class FewInsertMySQL implements FewMySQLBuilder {

    private final Map<String, Object> inserts;
    private String table;

    public FewInsertMySQL() {
        this.inserts = new HashMap<>();
    }

    public FewInsertMySQL insert(String key, Object value) {
        this.inserts.put(key, value);
        return this;
    }

    public FewInsertMySQL table(String table) {
        this.table = table;
        return this;
    }

    @Override
    public String builder() {
        StringBuilder output = new StringBuilder("INSERT INTO ");
        output.append("`").append(this.table).append("`");

        output.append(" (");
        for (String key : this.inserts.keySet()) {
            output.append("`").append(key).append("`, ");
        }
        output.delete(output.length() - 2, output.length());
        output.append(") VALUES (");

        for (Object value : this.inserts.values()) {
            output.append("\"").append(value).append("\", ");
        }
        output.delete(output.length() - 2, output.length());
        output.append(")");

        return output.toString();
    }
}
