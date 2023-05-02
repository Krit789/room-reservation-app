package net.itkmitl.room.libs.phatsanphon.repository.enums;

public enum FeedbackQuery {
    ID(0), ROOM_ID(1), USER_ID(2), RATING(3), COMMENT(4);

    private int query;

    private FeedbackQuery(int query) {
        this.setQuery(query);
    }

    public int getQuery() {
        return query;
    }

    private void setQuery(int query) {
        this.query = query;
    }
    
    public static FeedbackQuery getQueryByID(int id) {
    	for (FeedbackQuery query : values()) {
    		if (query.getQuery() == id) {
    			return query;
    		}
    	}
    	return null;
    }
}
