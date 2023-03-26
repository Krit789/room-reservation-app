package net.itkmitl.room.libs.peeranat.util;

import net.itkmitl.room.libs.peeranat.query.FewDeleteMySQL;
import net.itkmitl.room.libs.peeranat.query.FewInsertMySQL;
import net.itkmitl.room.libs.peeranat.query.FewSelectMySQL;
import net.itkmitl.room.libs.peeranat.query.FewUpdateMySQL;

public class FewMySQL {

    public static FewSelectMySQL select() {
        return new FewSelectMySQL();
    }

    public static FewDeleteMySQL delete() {
        return new FewDeleteMySQL();
    }

    public static FewInsertMySQL insert() {
        return new FewInsertMySQL();
    }

    public static FewUpdateMySQL update() {
        return new FewUpdateMySQL();
    }

}
