package net.itkmitl.room.portal.components;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.libs.phatsanphon.repository.RoomRepository;
import net.itkmitl.room.portal.components.entity.Floor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RoomLoader {
    public static ArrayList<Room> roomList; // 1
    public static HashMap<String, ArrayList<Room>> buildingList = new HashMap<>();
    // 2.) HashMap<String(Building Name), ArrayList<Room(Room in the Building)>>
    public static HashMap<String, HashMap<String, Floor>> floorList = new HashMap<>();
    // 3.) HashMap<String(Building Name), HashMap<String(Floor Name), Floor(Object)>>
    public static boolean done = false;
    public RoomLoader() {
        loadRoom();
    }

    public static void loadRoom() {
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                FewQuery db = LaewTaeDB.getDB();
                roomList = new RoomRepository(db).getRooms();
                for (Room room : roomList) {
//                    System.out.println("1.) "+room.getBuilding() + " " + room.getFloor() + " " + room.getName());
                    if (buildingList.containsKey(room.getBuilding())) {
                        buildingList.get(room.getBuilding()).add(room);
                    } else {
                        buildingList.put(room.getBuilding(), new ArrayList<Room>());
                        buildingList.get(room.getBuilding()).add(room);
                    }
                }

                for (String building: buildingList.keySet()) {
                    floorList.put(building, new HashMap<String, Floor>());
//                    System.out.println("1.25) " + building);
                }


                for (String building: buildingList.keySet()){ // Get Key
//                    floorList.put(building, new HashMap<String, Floor>());
//                    System.out.println("1.5) " + building);
                    for (Room room: buildingList.get(building)){ // Get Value
//                        System.out.println("2.) RoomOBJ: "+ building + " " + room.getBuilding() + " " + room.getFloor() + " " + room.getName() + " " + floorList.containsKey(building));
                        if(floorList.get(building).containsKey(room.getFloor())) {
                            for (Map.Entry<String, Floor> insideMap: floorList.get(building).entrySet()){
                                if (insideMap.getKey().equals(room.getFloor())){
                                    floorList.get(building).get(room.getFloor()).addRoom(room);
                                }
                            }
                        } else{
                            Floor floor = new Floor();
                            floor.addRoom(room);
                            floorList.get(building).put(room.getFloor(), floor);
                        }
                        System.out.println(floorList.get(building).size());
                    }
                }

//                for (String name: floorList.keySet()) {
//                    String key = name.toString();
//                    HashMap<String, Floor> value = floorList.get(name);
//                    System.out.println("FloorList: " + key);
//                    for (Map.Entry<String, Floor> data : value.entrySet()) {
//                        String floor = data.getKey();
//                        Floor myFloor = data.getValue();
//                        System.out.println("  Fl: " + floor);
//                        for (Map.Entry<String, Room> finalData : myFloor.getRooms().entrySet()) {
//                            System.out.println("   Room: " + finalData.getKey());
//                        }
//                    }
//                }

                buildingList = null;

                return null;
            }

            @Override
            protected void done() {
                System.out.println("room loader finished!");
                done = true;
            }
        };
        worker.execute();
    }
}
