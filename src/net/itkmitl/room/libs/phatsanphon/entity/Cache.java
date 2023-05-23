package net.itkmitl.room.libs.phatsanphon.entity;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.repository.RoomRepository;
import net.itkmitl.room.libs.phatsanphon.repository.UserRepository;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Cache {
    private static HashMap<Integer, Room> roomCache = null;
    private static HashMap<Integer, User> userCache = null;
    private static FewQuery db;
    private static int cacheTimeout = 300; // Seconds

    private static long userCacheFetchTime;
    private static long roomCacheFetchTime;

    private static void refreshDB(){
        try {
            db = LaewTaeDB.getDB();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Cache(FewQuery db) throws Exception {
        Cache.db = db;
    }

    public void setCacheTimeout(int time) {
        Cache.cacheTimeout = time;
    }

    public static synchronized User getUser(int id) throws Exception {
        if (userCache != null) {
            User myUser = userCache.get(id);
            if (myUser == null || ((System.currentTimeMillis() / 1000L) - (userCacheFetchTime / 1000L)) > cacheTimeout) {
                userCache = null;
                userCache = new HashMap<>();
                refreshDB();
                for (User u : new UserRepository(Cache.getDB()).getUsers()) {
                    userCache.put(u.getId(), u);
                }
                userCacheFetchTime = System.currentTimeMillis();
            }
        } else {
            userCache = new HashMap<>();
            refreshDB();
            for (User u : new UserRepository(Cache.getDB()).getUsers()) {
                userCache.put(u.getId(), u);
            }
            userCacheFetchTime = System.currentTimeMillis();
        }
        return userCache.getOrDefault(id, null);
    }

    public static synchronized ArrayList<User> getUsers() throws Exception {
        if (userCache != null) {
            if (((System.currentTimeMillis() / 1000L) - (userCacheFetchTime / 1000L)) > cacheTimeout) {
                userCache = null;
                userCache = new HashMap<>();
                refreshDB();
                for (User u : new UserRepository(Cache.getDB()).getUsers()) {
                    userCache.put(u.getId(), u);
                }
                userCacheFetchTime = System.currentTimeMillis();
            }
        } else {
            userCache = new HashMap<>();
            refreshDB();
            for (User u : new UserRepository(Cache.getDB()).getUsers()) {
                userCache.put(u.getId(), u);
            }
            userCacheFetchTime = System.currentTimeMillis();
        }
        return new ArrayList<>(userCache.values());
    }

    public static synchronized Room getRoom(int id) throws Exception {
        if (roomCache != null) {
            Room myRoom = roomCache.get(id);
            if (myRoom == null || ((System.currentTimeMillis() / 1000L) - (roomCacheFetchTime / 1000L)) > cacheTimeout) {
                refreshDB();
                roomCache = null;
                roomCache = new HashMap<>();
                refreshDB();
                for (Room r : new RoomRepository(Cache.getDB()).getRooms()) {
                    roomCache.put(r.getId(), r);
                }
                roomCacheFetchTime = System.currentTimeMillis();
            }
        } else {
            roomCache = new HashMap<>();
            refreshDB();
            for (Room r : new RoomRepository(Cache.getDB()).getRooms()) {
                roomCache.put(r.getId(), r);
            }
            roomCacheFetchTime = System.currentTimeMillis();
        }
        return roomCache.getOrDefault(id, null);

    }

    public static synchronized ArrayList<Room> getRooms() throws Exception {
        if (roomCache != null) {
            if (((System.currentTimeMillis() / 1000L) - (roomCacheFetchTime / 1000L)) > cacheTimeout) {
                roomCache = null;
                roomCache = new HashMap<>();
                refreshDB();
                for (Room r : new RoomRepository(Cache.getDB()).getRooms()) {
                    roomCache.put(r.getId(), r);
                }
                roomCacheFetchTime = System.currentTimeMillis();
            }
        } else {
            roomCache = new HashMap<>();
            refreshDB();
            for (Room r : new RoomRepository(Cache.getDB()).getRooms()) {
                roomCache.put(r.getId(), r);
            }
            roomCacheFetchTime = System.currentTimeMillis();
        }
        return new ArrayList<>(roomCache.values());
    }

    public static void purgeRoomCache() {
        roomCache = null;
    }

    public static void purgeUserCache() {
        userCache = null;
    }

    public static void purgeCache(){
        roomCache = null;
        userCache = null;
        SwingWorker<?, ?> worker = new SwingWorker<>() {
            @Override
            protected Object doInBackground() throws Exception {
                getUsers();
                getRooms();
                return null;
            }
        };
        worker.execute();
    }

    private static FewQuery getDB() {
        return db;
    }
}

