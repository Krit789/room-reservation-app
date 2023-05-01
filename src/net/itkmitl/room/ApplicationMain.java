package net.itkmitl.room;

import net.itkmitl.room.db.RVDB;
import net.itkmitl.room.enums.EnumDBSchema;
import net.itkmitl.room.libs.peeranat.FewDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.date.DateTime;
import net.itkmitl.room.libs.phatsanphon.entity.*;
import net.itkmitl.room.libs.phatsanphon.repository.*;

import java.util.ArrayList;
import java.util.Date;

public class ApplicationMain {
    public static void main(String[] args) {
        FewQuery db = RVDB.getDB();

        RoomRepository roomRepository = new RoomRepository(db);
        FeedbackRepository feedbackRepository = new FeedbackRepository(db);

        Room pjBased = new Room();
        pjBased.setName("Project Based 1");
        pjBased.setBuilding("School of IT");
        pjBased.setFloor("2");
        pjBased.setOpenTime(new DateTime(new Date(2023 - 1900, 3, 25, 1, 0, 0)));
        pjBased.setCloseTime(new DateTime(new Date(2023 - 1900, 3, 25, 11, 0, 0)));

        Feedback feedback = new Feedback();
        feedback.setRoom(7);
        feedback.setUser(6);
        feedback.setComment("Good Job!");
        feedback.setRating(4.5);

//        roomRepository.createRoom(pjBased);
        feedbackRepository.createFeedback(feedback);
    }
}
