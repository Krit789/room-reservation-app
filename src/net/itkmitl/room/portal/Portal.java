package net.itkmitl.room.portal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.effect.Effect;
import net.itkmitl.room.libs.peeranat.util.*;


import javax.sound.sampled.Port;
import java.io.IOException;

public abstract class Portal extends Application {
    protected FXMLLoader fxmlLoader;
    protected Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/hello-view.fxml"));
        fxmlLoader = new FXMLLoader(FewFile.getResource("account/Portal.fxml"));
        scene = new Scene(fxmlLoader.load());

//        String css = getClass().getResource("styles/portal.css").toExternalForm();
//        scene.getStylesheets().add(css);

        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.setResizable(true);
        stage.setTitle("Laew Tae Hong");
        stage.setScene(scene);
        stage.show();
    }

    protected void runFX(Stage stage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/hello-view.fxml"));
        fxmlLoader = new FXMLLoader(FewFile.getResource("account/Portal.fxml"));
        scene = new Scene(fxmlLoader.load());

//        String css = getClass().getResource("styles/portal.css").toExternalForm();
//        scene.getStylesheets().add(css);

        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.setResizable(true);
        stage.setTitle("Laew Tae Hong");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}