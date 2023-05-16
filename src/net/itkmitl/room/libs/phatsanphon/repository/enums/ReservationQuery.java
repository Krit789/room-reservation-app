package net.itkmitl.room.libs.phatsanphon.repository.enums;

public enum ReservationQuery {
    ID(0), USER_ID(1), ROOM_ID(2), REASON(3);

    private int query;

    ReservationQuery(int query) {
        this.setQuery(query);
    }

    public static ReservationQuery getQueryByID(int id) {
        for (ReservationQuery query : values()) {
            if (query.getQuery() == id) {
                return query;
            }
        }
        return null;
    }

    public int getQuery() {
        return query;
    }

    private void setQuery(int query) {
        this.query = query;
    }
}
