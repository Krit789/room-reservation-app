package net.itkmitl.room.libs.jarukrit.compute;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.libs.jarukrit.records.Feedback;
import net.itkmitl.room.libs.peeranat.query.FewInsertMySQL;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.peeranat.query.FewSelectMySQL;

import java.util.ArrayList;
import java.util.HashMap;

public class FeedbackActions {
    private Feedback feedback;
    private FewQuery query = RVDB.getDB();
    public boolean submit(Feedback feedback) {
        if (this.feedback.getRating() >= 0 && this.feedback.getRating() <= 10) {
            FewQuery query = RVDB.getDB();
            FewInsertMySQL insertMySQL = new FewInsertMySQL();
            insertMySQL.table("feedback");
            insertMySQL.insert("room_id", this.feedback.getRoom_id());
            insertMySQL.insert("user_id", this.feedback.getUser_id());
            insertMySQL.insert("comment", this.feedback.getComment());
            insertMySQL.insert("rating", this.feedback.getRating());
            query.query(insertMySQL);
            return true;
        }
        return false;
    }
    public ArrayList<Feedback> getFromUserID(int id){
        ArrayList<Feedback> queryData = new ArrayList<Feedback>();
        FewSelectMySQL select = new FewSelectMySQL();
        select.table("feedback");
        select.select("*");
        select.where("user_id", id);
        FewQuery result = query.query(select);
        while (result.nextBind()){
            queryData.add(new Feedback(
                    result.getValue("id").asInt(),
                    result.getValue("room_id").asInt(),
                    result.getValue("user_id").asInt(),
                    result.getValue("created_on").asString(),
                    result.getValue("comment").asString(),
                    result.getValue("rating").asDouble()
            ));
        }
        return queryData;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }
}
