package net.itkmitl.room.libs.peeranat.query;

import net.itkmitl.room.libs.peeranat.simplevalue.FewSimpleValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;

public class FewQuery {

    public final Connection connection;
    private final HashMap<String, FewSimpleValue> fields;
    private int queryCount;
    private ResultSet result;
    private ResultSetMetaData resultMeta;
    private int insertedID;
    private int affectedRows;

    public FewQuery(Connection connection) {
        this.connection = connection;
        this.fields = new HashMap<>();
    }

    public FewQuery query(FewMySQLBuilder query) throws Exception {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    query.builder(),
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            statement.execute();
            this.affectedRows = statement.getUpdateCount();
            queryCount++;

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.insertedID = generatedKeys.getInt(1);
            }

            this.result = statement.getResultSet();
            if (this.result != null) {
                this.resultMeta = this.result.getMetaData();
                for (int col = 1; col <= this.resultMeta.getColumnCount(); col++) {
                    this.fields.put(this.resultMeta.getColumnName(col), null);
                }
            }

            return this;
        } catch (Exception e) {
            System.out.println("ERROR: Unable to prepare query (" + e.getMessage() + ")");
            throw e;
        }
    }

    public FewQuery unsafeQuery(String query) throws Exception {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    query,
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            statement.execute();
            this.affectedRows = statement.getUpdateCount();
            queryCount++;
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.insertedID = generatedKeys.getInt(1);
            }
            this.result = statement.getResultSet();
            if (this.result != null) {
                this.resultMeta = this.result.getMetaData();
                for (int col = 1; col <= this.resultMeta.getColumnCount(); col++) {
                    this.fields.put(this.resultMeta.getColumnName(col), null);
                }
            }

            return this;
        } catch (Exception e) {
            System.out.println("ERROR: Unable to prepare query (" + e.getMessage() + ")");
            throw e;
        }
    }

    public void close() {
        try {
            if (this.result == null) {
                return;
            }
            if (this.result.isClosed()) {
                return;
            }
            this.result.close();
        } catch (Exception e) {
            System.out.println("ERROR: Unable to close query (" + e.getMessage() + ")");
        }
    }

    public void bindValue(String fieldName, FewSimpleValue ref) {
        if (this.fields.containsKey(fieldName)) {
            this.fields.replace(fieldName, ref);
        } else {
            System.out.println("ERROR: Unable to bind \"" + fieldName + "\" (Field does not exists)");
        }
    }

    public FewSimpleValue getValue(String fieldName) {
        if (this.fields.containsKey(fieldName)) {
            return fields.get(fieldName);
        } else {
            System.out.println("ERROR: Unable to bind \"" + fieldName + "\" (Field does not exists)");
        }
        return null;
    }

    public boolean nextBind() {
        try {
            boolean isNext = this.result.next();
            if (!isNext) {
                return false;
            }
            for (int col = 1; col <= this.resultMeta.getColumnCount(); col++) {
                String fieldName = this.resultMeta.getColumnName(col);
                this.fields.put(fieldName, new FewSimpleValue(result.getObject(col)));
            }
        } catch (Exception e) {
            System.out.println("ERROR: Unable to fetch (" + e.getMessage() + ")");
        }
        return true;
    }

    public int getQueryCount() {
        return queryCount;
    }

    public int getAffectedRows() {
        return affectedRows;
    }

    public int getInsertedID() {
        return insertedID;
    }
}
