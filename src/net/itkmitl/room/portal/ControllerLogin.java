package net.itkmitl.room.portal;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {
    @FXML
    private Pane pane, root;
    @FXML
    private Rectangle login_background;
    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        root.prefHeightProperty().bind(scene.heightProperty());
//        login_background.widthProperty().bind(new Pane());
//        login_background.heightProperty().bind(pane.heightProperty());
//        login_background.setX((screenSize.getWidth() - borderPane.getWidth()) / 2);
//        login_background.setY((screenSize.getHeight() - borderPane.getHeight()) / 2);

    }
}
