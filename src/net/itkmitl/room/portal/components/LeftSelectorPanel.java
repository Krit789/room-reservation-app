package net.itkmitl.room.portal.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.libs.phatsanphon.repository.RoomRepository;

public class LeftSelectorPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 634260063969366698L;
    public JLabel parentCategory;
    public JButton backButton;
    public ArrayList<LeftSelectorBox> leftBoxHolder = new ArrayList<LeftSelectorBox>();
    public Set<String> buildingList = new HashSet<>();

    public LeftSelectorPanel() {
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
        backButton.setActionCommand("Back to Dashboard");
        this.add(parentCategory);

        loadBuilding();
        //test importing floor data(idk how this database work so have this for now)

        //would be in a loop for all component to add
    }

    private void loadBuilding() {
        SwingWorker<?, ?> worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                try {
                    FewQuery db = LaewTaeDB.getDB();
                    RoomRepository roomRepository = new RoomRepository(db);
                    try {
                        ArrayList<Room> roomsList = roomRepository.getRooms();
                        for (Room room : roomsList) {
                            buildingList.add(room.getBuilding());
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    int i = 0;
                    for (String buildingName : buildingList) {
                        LeftSelectorBox box = new LeftSelectorBox(buildingName, i);
                        box.setActionCommand(buildingName);
                        add(box);
                        leftBoxHolder.add(box);
                        i++;
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return null;
            }

            @Override
            protected void done() {
                JOptionPane.showMessageDialog(null, "Finished Loading", "Notice", JOptionPane.INFORMATION_MESSAGE);
                add(backButton);
            }
        };
        worker.execute();
    }

    public void getBuilding(RoomRepository roomRepository) {
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
