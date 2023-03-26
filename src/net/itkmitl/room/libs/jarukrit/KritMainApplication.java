package net.itkmitl.room.libs.jarukrit;

import net.itkmitl.room.libs.jarukrit.compute.FeedbackActions;
import net.itkmitl.room.libs.jarukrit.records.Feedback;

import java.util.ArrayList;

public class KritMainApplication {
    public static void main(String[] args) {
        ArrayList<Feedback> MyFeedback = FeedbackActions.getFromUserID(2);
        for (int i = 0; i < MyFeedback.size(); i++){
            System.out.println(MyFeedback.get(i).getCreated_on().toLocalDateTime());
        }
    }
}
