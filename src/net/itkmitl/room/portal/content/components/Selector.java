package net.itkmitl.room.portal.content.components;

import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.portal.CardView;
import net.itkmitl.room.portal.components.*;
import net.itkmitl.room.portal.components.entity.Floor;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.HashMap;

public class Selector extends CardView {
    @Serial
    private static final long serialVersionUID = -8564430568200760268L;
    public JPanel leftPanel, innerPanel, mainPanel, header, leftSelectorPanel, cardsOfBuildingsFloorPanel;
    public JLabel testLabel;//delete later
    public ArrayList<MainRoomSelectionBox> roomBoxHolder = new ArrayList<MainRoomSelectionBox>();
    public static boolean doneRoomBox = false;

    public Selector() {
        super();
    }

    @Override
    public void initialize() {
        leftPanel = new LeftPanel();
        innerPanel = new MainPanel();
        mainPanel = new JPanel(new BorderLayout());
        cardsOfBuildingsFloorPanel = new JPanel(new CardLayout());
        leftSelectorPanel = new LeftSelectorPanel();

        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.white);

        header = new Header("Select a Building", "Enjoy seemless reservation experience");

        testLabel = new JLabel("Hi");

        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                while (!RoomLoader.done) {
                    Thread.onSpinWait();
                }
                return null;
            }

            @Override
            protected void done() {
//                System.out.println("Done with room data!");
                populateList();
            }
        };
        worker.execute();

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(innerPanel, BorderLayout.CENTER);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
        leftPanel.add(leftSelectorPanel, BorderLayout.CENTER);
    }

    public void populateList() {
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                for (HashMap.Entry<String, HashMap<String, Floor>> row : RoomLoader.floorList.entrySet()) {
                    String buildingName = row.getKey();
//                    System.out.println(buildingName);
                    HashMap<String, Floor> buildingsFloor = row.getValue();
                    JPanel allPanel = new JPanel(new BorderLayout());
                    // Horizontal Floor List
                    JPanel floorPanel = new JPanel(new GridBagLayout());
                    JScrollPane floorScrollPane = new JScrollPane(floorPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    floorScrollPane.setOpaque(false);
                    floorScrollPane.setBorder(null);
                    int floorCount = 0;

                    // Floor Box, horizontal "Room box" list
                    for (HashMap.Entry<String, Floor> row2 : buildingsFloor.entrySet()) {
                        String floorName = row2.getKey();
                        Floor eachFloor = row2.getValue();

                        // Vertical Room List in each Floor (floorPanel)
                        MainFloorSelectionBox floorBox = new MainFloorSelectionBox(floorName); // Outer Pane -> Building, Inner Pane -> Building's Floor
                        int roomCount = 0;
                        // Room box, vertical room list

                        for (Room myRoom : eachFloor.getRooms().values()) {
//                            System.out.println(myRoom.getBuilding() + " " + myRoom.getFloor() + " " + myRoom.getName());
                            MainRoomSelectionBox roomBox = new MainRoomSelectionBox(myRoom);
                            roomBoxHolder.add(roomBox);
                            floorBox.roomPanel.add(roomBox, new GBCBuilder(GridBagConstraints.HORIZONTAL, 1, 1, roomCount, new Insets(10, 15, 0, 15)).getGBC());
                            roomCount++;
                        }

                        floorPanel.add(floorBox, new GBCBuilder(GridBagConstraints.VERTICAL, 0, 1, floorCount, 0, new Insets(10, 20, 10, 20)).getGBC());

                        cardsOfBuildingsFloorPanel.add(buildingName, floorScrollPane);

                        cardsOfBuildingsFloorPanel.revalidate();
                        floorCount++;
                    }
                }
                innerPanel.add(cardsOfBuildingsFloorPanel, BorderLayout.CENTER);
                return null;
            }

            @Override
            protected void done() {
                doneRoomBox = true;
            }
        };

        worker.execute();

    }

    public LeftSelectorPanel getSelectorPanel() {
        return (LeftSelectorPanel) this.leftSelectorPanel;
    }

    public MainPanel getMainPanel() {
        return (MainPanel) mainPanel;
    }

    public JPanel getInnerPanel() {
        return this.innerPanel;
    }

}

