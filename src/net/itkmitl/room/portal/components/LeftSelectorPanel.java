package net.itkmitl.room.portal.components;

import net.itkmitl.room.db.LaewTaeDB;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.entity.Cache;
import net.itkmitl.room.libs.phatsanphon.entity.Room;
import net.itkmitl.room.libs.phatsanphon.repository.RoomRepository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LeftSelectorPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 634260063969366698L;
    public static volatile boolean finishedLoading = false;
    public JLabel parentCategory;
    public ButtonGradient backButton;
    public JScrollPane buttonScrollPane;
    public JPanel buttonPanel;
    public ArrayList<LeftSelectorBox> leftBoxHolder = new ArrayList<LeftSelectorBox>();
    public Set<String> buildingList = new HashSet<>();

    public LeftSelectorPanel() {
        super();
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(0, 50, 20, 50));
        this.setPreferredSize(
                new Dimension(534, (int) this.getBounds().getSize().getHeight())
        );
        this.setOpaque(false);
        parentCategory = new JLabel("<HTML><U>Select a Building</U></HTML>");
        parentCategory.setFont(new Font("Cousine", Font.BOLD, 25));
        parentCategory.setForeground(Color.WHITE);
        buttonPanel = new TransparentPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        buttonScrollPane = new JScrollPane(buttonPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        buttonScrollPane.setAlignmentY(JScrollPane.TOP_ALIGNMENT);
        buttonScrollPane.setOpaque(false);
        buttonScrollPane.getViewport().setOpaque(false);
        buttonScrollPane.setBorder(null);

        backButton = new ButtonGradient("Back", new Color(44, 102, 188), new Color(94, 135, 197));
        backButton.setFont(new Font("Cousine", Font.BOLD, 18));
        backButton.setSizeSpeed(10f);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setForeground(Color.WHITE);
        backButton.setActionCommand("Back to Dashboard");
        add(parentCategory, new GBCBuilder(GridBagConstraints.CENTER, 1, 0.1, 0, 0).getGBC());

        loadBuilding();
        RoomLoader.loadRoom();
    }


    private void loadBuilding() {
        SwingWorker<?, ?> worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                try {
//                    FewQuery db = LaewTaeDB.getDB();
//                    RoomRepository roomRepository = new RoomRepository(db);
                    try {
                        ArrayList<Room> roomsList = Cache.getRooms();
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

                        buttonPanel.add(box);
                        buttonPanel.add(Box.createVerticalStrut(5));

                        leftBoxHolder.add(box);
                        buttonPanel.revalidate();
                        i++;
                    }

                    JButton component = (JButton) buttonPanel.getComponent(0);
                    component.setText("√ " + component.getText());

                    buttonPanel.add(Box.createVerticalGlue());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return null;
            }

            @Override
            protected void done() {
                registerPane();
                finishedLoading = true;
            }
        };
        worker.execute();
    }

    private void registerPane() {
        this.add(buttonScrollPane, new GBCBuilder(GridBagConstraints.BOTH, 1, 0.8, 0, 1).getGBC());
        this.add(backButton, new GBCBuilder(GridBagConstraints.BOTH, 1, 0.1, 0, 2).getGBC());
    }

    public void getBuilding() {
        try {
            ArrayList<Room> roomsList = Cache.getRooms();
            for (Room room : roomsList) {
                buildingList.add(room.getBuilding());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
