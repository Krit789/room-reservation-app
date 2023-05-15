package net.itkmitl.room.portal.content.components;

import net.itkmitl.room.libs.phatsanphon.date.DateTime;

public class ReservableEntity {
    public DateTime begin, end;
    private Object[] mytime;

    public ReservableEntity(Object[] mytime){
        this.mytime = mytime;
        begin = new DateTime((long) mytime[0]);
        end = new DateTime((long) mytime[1]);
    }
    @Override
    public String toString(){
        return begin + " " + end;
    }
}
