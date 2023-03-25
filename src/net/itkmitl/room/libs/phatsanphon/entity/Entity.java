package net.itkmitl.room.libs.phatsanphon.entity;

import net.itkmitl.room.libs.peeranat.query.FewQuery;

import java.util.ArrayList;

public interface Entity<T> {

    <T> ArrayList<T> maps(FewQuery result);
    <T> T map(FewQuery result);

}
