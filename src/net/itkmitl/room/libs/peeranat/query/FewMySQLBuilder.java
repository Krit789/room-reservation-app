package net.itkmitl.room.libs.peeranat.query;

public abstract class FewMySQLBuilder {

    protected String table;

    public abstract String builder();

    public FewMySQLBuilder table(String table) {
        this.table = table;
        return this;
    }

}
