package net.itkmitl.room.portal.components;

import java.util.ArrayList;
import java.util.HashMap;

public class Building {
    public HashMap<String, Floor> floors = new HashMap<String, Floor>();
    public String name;
    public Building(){
        this("");
    }
    public Building(String name){
        this.name = name;
    }
    public void addFloor(Floor floor){
        floors.put(floor.getName(), floor);
    }

}
