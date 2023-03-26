package net.itkmitl.room.libs.jarukrit;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.query.FewSelectMySQL;

public class KritSQL {
    private static FewQuery query = RVDB.getDB();

    public static FewQuery kritSelect(String table, String select, String whereColumn, String whereData) {
        FewSelectMySQL fewSelect = new FewSelectMySQL();
        fewSelect.table(table);
        fewSelect.select(select);
        fewSelect.where(whereColumn, whereData);
        return query.query(fewSelect);
    }

    public static FewQuery kritSelect(String table, String whereColumn, String whereData) {
        FewSelectMySQL fewSelect = new FewSelectMySQL();
        fewSelect.table(table);
        fewSelect.select("*");
        fewSelect.where(whereColumn, whereData);
        return query.query(fewSelect);
    }

    public static FewQuery kritSelect(String table, String whereColumn, int whereData) {
        return KritSQL.kritSelect(table, whereColumn, String.valueOf(whereData));
    }



    public static FewQuery kritSelect(String table, String select, String whereColumn, int whereData) {
        return KritSQL.kritSelect(table, select, whereColumn, String.valueOf(whereData));
    }

}
