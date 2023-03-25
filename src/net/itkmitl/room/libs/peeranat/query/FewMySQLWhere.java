package net.itkmitl.room.libs.peeranat.query;

public class FewMySQLWhere {

    private FewMySQLCompare compare;
    private FewMySQLOperator operator;
    private String column;
    private Object value;

    public FewMySQLWhere(String column, FewMySQLCompare compare, Object value, FewMySQLOperator operator) {
        this.column = column;
        this.value = value;
        this.compare = compare;
        this.operator = operator;

        if (this.value == null && this.compare.equals(FewMySQLCompare.EQUAL)) {
            this.compare = FewMySQLCompare.IS_NULL;
        }
    }

    public String getWhereString() {
        StringBuilder output = new StringBuilder();
        output.append(this.column).append(" ").append(this.compare.getValue());
        if (!this.compare.equals(FewMySQLCompare.IS_NULL) && !this.compare.equals(FewMySQLCompare.IS_NOT_NULL)) {
            output.append(" '").append(this.value).append("'");
        }
        return output.toString();
    }

    public FewMySQLCompare getCompare() {
        return compare;
    }
    public FewMySQLOperator getOperator() {
        return operator;
    }
    public String getColumn() {
        return column;
    }
    public Object getValue() {
        return value;
    }

}
