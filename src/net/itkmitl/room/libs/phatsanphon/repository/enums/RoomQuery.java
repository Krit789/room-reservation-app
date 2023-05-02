package net.itkmitl.room.libs.phatsanphon.repository.enums;

public enum RoomQuery {
    ID(0), NAME(1), BUILDING(2), STATE(3);

    private int query;

    private RoomQuery(int query) {
        this.setQuery(query);
    }

    public int getQuery() {
        return query;
    }

    private void setQuery(int query) {
        this.query = query;
    }
    
    public static RoomQuery getQueryByID(int id) {
    	for (RoomQuery query : values()) {
    		if (query.getQuery() == id) {
    			return query;
    		}
    	}
    	return null;
    }
}
