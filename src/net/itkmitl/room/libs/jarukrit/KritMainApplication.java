package net.itkmitl.room.libs.jarukrit;

import net.itkmitl.room.libs.jarukrit.compute.FeedbackActions;
import net.itkmitl.room.libs.jarukrit.records.Feedback;

import java.util.ArrayList;

public class KritMainApplication {
    public static void main(String[] args) {
        ArrayList<Feedback> MyFeedback = FeedbackActions.getByUserID(2);
        for (int i = 0; i < MyFeedback.size(); i++){
            System.out.print(MyFeedback.get(i).getUser_id() + " ");
            System.out.print(MyFeedback.get(i).getRoom_id() + " ");
            System.out.println(MyFeedback.get(i).getCreated_on().toLocalDateTime());

        }
//        ConfigManager.saveConnection("room_reservation", "49.228.131.109", 3357, "test_user","Pu57HDrrXgvVLn");
    }
}
