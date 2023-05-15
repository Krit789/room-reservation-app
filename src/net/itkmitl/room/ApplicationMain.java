package net.itkmitl.room;

import net.itkmitl.room.enums.EnumRoomState;
import net.itkmitl.room.libs.peeranat.util.FewRoom;
import net.itkmitl.room.libs.phatsanphon.entity.Room;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ApplicationMain {
    public static void main(String[] args) throws Exception {
        Collection<Room> room = FewRoom.getRoomByState(EnumRoomState.AVAILABLE);
        System.out.println(room.size());
    }
}
