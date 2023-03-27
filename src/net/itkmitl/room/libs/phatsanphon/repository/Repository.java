package net.itkmitl.room.libs.phatsanphon.repository;

import net.itkmitl.room.libs.peeranat.query.FewQuery;

import java.util.ArrayList;

public interface Repository<T> {
    <T> ArrayList<T> maps(FewQuery result);
    <T> T map(FewQuery result);
}
