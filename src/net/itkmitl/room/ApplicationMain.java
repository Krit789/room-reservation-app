package net.itkmitl.room;

import net.itkmitl.room.libs.peeranat.config.*;
import net.itkmitl.room.libs.jarukrit.records.Feedback;
import java.io.*;

public class ApplicationMain {

    public static void main(String[] args) {
//        FewConfig config = new FewConfig(new File("test.yml"));
//        config.dumpInfo();
        new Feedback(1, 2, 8.5).submit();

    }


}
