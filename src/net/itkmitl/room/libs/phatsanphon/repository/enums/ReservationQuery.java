package net.itkmitl.room.libs.phatsanphon.repository.enums;

public enum ReservationQuery {
    ID(0), ROOM_ID(1), USER_ID(2), REASON(3);

    private int query;

    private ReservationQuery(int query) {
        this.setQuery(query);
    }

    public int getQuery() {
        return query;
    }

    private void setQuery(int query) {
        this.query = query;
    }
}
