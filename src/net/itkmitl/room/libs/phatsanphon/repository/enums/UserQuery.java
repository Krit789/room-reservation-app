package net.itkmitl.room.libs.phatsanphon.repository.enums;

public enum UserQuery {
    ID(0), FIRSTNAME(1), LASTNAME(2), EMAIL(3), PHONE_NUMBER(4);

    private int query;

    UserQuery(int query) {
        this.setQuery(query);
    }

    public static UserQuery getQueryByID(int id) {
        for (UserQuery query : values()) {
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
