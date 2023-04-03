package net.itkmitl.room.portal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.itkmitl.room.libs.peeranat.util.FewFile;

import java.awt.*;
import java.io.IOException;

public class Login extends Application {
    protected FXMLLoader fxmlLoader;
    protected Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/hello-view.fxml"));
        fxmlLoader = new FXMLLoader(FewFile.getResource("account/Login.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setMaximized(true);

//        String css = getClass().getResource("styles/Login.css").toExternalForm();
//        scene.getStylesheets().add(css);

        ControllerLogin controllerLogin = new ControllerLogin();
        controllerLogin.setStage(stage);
        controllerLogin.setSizeToRectangle();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        stage.setSize(screenSize);
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setWidth(screenSize.getWidth());
        stage.setHeight(screenSize.getHeight());
        stage.setResizable(true);
        stage.setTitle("Laew Tae Hong");
        stage.setScene(scene);
        stage.show();
    }

    protected void runFX(Stage stage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/hello-view.fxml"));
        fxmlLoader = new FXMLLoader(FewFile.getResource("account/Login.fxml"));
        scene = new Scene(fxmlLoader.load());
//        String css = getClass().getResource("styles/Login.css").toExternalForm();
//        scene.getStylesheets().add(css);

        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
//        stage.setWidth(1280);
//        stage.setHeight(720);
        stage.setResizable(true);
        stage.setTitle("Laew Tae Hong");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}