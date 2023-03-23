package net.itkmitl.room.libs.peeranat.query;

import java.util.ArrayList;

public class FewDeleteMySQL extends FewMySQLBuilder {

    private ArrayList<FewMySQLWhere> wheres;
    private int limit;

    public FewDeleteMySQL where(String column, String value) {
        this.wheres.add(new FewMySQLWhere(column, FewMySQLCompare.EQUAL, value, FewMySQLOperator.AND));
        return this;
    }
    public FewDeleteMySQL where(String column, FewMySQLCompare compare, String value) {
        this.wheres.add(new FewMySQLWhere(column, compare, value, FewMySQLOperator.AND));
        return this;
    }
    public FewDeleteMySQL where(String column, FewMySQLCompare compare, String value, FewMySQLOperator operator) {
        this.wheres.add(new FewMySQLWhere(column, compare, value, operator));
        return this;
    }
    public FewDeleteMySQL limit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public String builder() {
        StringBuilder sb = new StringBuilder(32);
        sb.append("DELETE FROM ");
        sb.append('`');
        sb.append(this.table);
        sb.append('`');
        if(wheres.size() > 0) {
            sb.append(" WHERE ");
            boolean isAlreadyWhere = false;
            for(FewMySQLWhere simpleWhere : wheres) {
                if(isAlreadyWhere) {
                    sb.append(' ');
                    sb.append(simpleWhere.getOperator().getValue());
                    sb.append(' ');
                }
                sb.append(simpleWhere.getWhereString());
                isAlreadyWhere = true;
            }
        }

        if(limit > 0) {
            sb.append(" LIMIT ");
            sb.append(limit);
        }

        return sb.toString();
    }
}
