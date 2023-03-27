package net.itkmitl.room.libs.front.user.portal;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPortal implements Initializable {
    private Pane root;

    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        root.prefHeightProperty().bind(scene.heightProperty());

    }
}
