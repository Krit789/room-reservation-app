package net.itkmitl.room.portal.components;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.libs.peeranat.FewDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.libs.phatsanphon.repository.RoomRepository;
import net.itkmitl.room.libs.phatsanphon.repository.UserRepository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LeftSelectorPanel extends JPanel {
    public JLabel parentCategory;
    public JButton backButton;
    public ArrayList<LeftSelectorBox> leftBoxHolder = new ArrayList<LeftSelectorBox>();
    public Set<String> buildingList = new HashSet<>();

    public LeftSelectorPanel(){
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(0, 50, 20, 50));
        this.setPreferredSize(
                new Dimension(534, (int) this.getBounds().getSize().getHeight())
        );
        this.setOpaque(false);
        parentCategory = new JLabel("Building");
        parentCategory.setFont(new Font("Calibri", Font.BOLD, 18));
        parentCategory.setForeground(Color.WHITE);
        backButton = new JButton("Back");
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setForeground(Color.WHITE);
        this.add(parentCategory);
        try {
            FewQuery db = LaewTaeDB.getDB();
            RoomRepository roomRepository = new RoomRepository(db);
            this.getBuilding(roomRepository);
            int i = 0;
            for(String buildingName: buildingList) {
                LeftSelectorBox box = new LeftSelectorBox(buildingName, i);
                this.add(box);
                leftBoxHolder.add(box);
                i++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //test importing floor data(idk how this database work so have this for now)

        //would be in a loop for all component to add

        this.add(backButton);
    }
    public void getBuilding(RoomRepository roomRepository){
        try {
            ArrayList<Room> roomsList = roomRepository.getRooms();
            for (Room room : roomsList) {
                buildingList.add(room.getBuilding());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
