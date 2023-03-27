package net.itkmitl.room;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.entity.*;
import net.itkmitl.room.libs.phatsanphon.repository.*;

import java.util.ArrayList;

public class ApplicationMain {

    public static void main(String[] args) {
        FewQuery db = RVDB.getDB();
        FeedbackRepository feedbackRepository = new FeedbackRepository(db);

        ArrayList<Feedback> feedback = feedbackRepository.getFeedbacks();

        for (Feedback fb : feedback) {
            System.out.println(fb.getId());
        }
    }
}
