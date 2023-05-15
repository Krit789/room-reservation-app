package net.itkmitl.room.portal.content.components;

import net.itkmitl.room.libs.phatsanphon.date.DateTime;

public class ReservableEntity {
    public DateTime begin, end;

    public ReservableEntity(DateTime begin, DateTime end){
        this.begin = begin;
        this.end = end;
    }
    @Override
    public String toString(){
        return String.format("%d:%02d - %d:%02d", begin.getHours(), begin.getMinutes(), end.getHours(), end.getMinutes());
    }
}
