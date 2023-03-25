package net.itkmitl.room;

import net.itkmitl.room.libs.peeranat.config.*;

import java.io.*;
import java.util.*;

public class ApplicationMain {

    public static void main(String[] args) {
        FewConfig config = new FewConfig(new File("test.yml"));
        System.out.println(config.asString("name"));
        System.out.println(config.asInt("phone"));
        System.out.println(config.asDouble("point"));
        System.out.println(config.asDouble("kuy", 0.0));
        List<String> list = config.asList("x", String.class);
        list.stream().forEach(System.out::println);
    }
}
