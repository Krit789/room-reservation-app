
package net.itkmitl.room.portal;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {
    @FXML
    private MenuBar menuBar;
    @FXML
    private Pane loginBackground;
    @FXML
    private BorderPane outerPane;

    @FXML
    private Scene scene;

    private Stage stage;


    @FXML
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setSize(double width, double height) {
        stage.setWidth(width);
        stage.setHeight(height);
    }

    public void setSizeToRectangle() {
//        stage.setMinHeight(loginBackground.getHeight());
//        stage.setMinWidth(loginBackground.getWidth());
        stage.setMinHeight(700);
        stage.setMinWidth(700);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        root.prefHeightProperty().bind(scene.heightProperty());
//        loginBackground.widthProperty().bind(new Pane());
//        loginBackground.heightProperty().bind(pane.heightProperty());
//        loginBackground.setX((screenSize.getWidth() - borderPane.getWidth()) / 2);
//        loginBackground.setY((screenSize.getHeight() - borderPane.getHeight()) / 2);
//        stage.setMinHeight(loginBackground.getHeight());
//        stage.setMinWidth(loginBackground.getWidth());
//        scene = menuBar.getScene();
//        stage = (Stage) scene.getWindow();
        menuBar.useSystemMenuBarProperty().set(true);

    }
}
