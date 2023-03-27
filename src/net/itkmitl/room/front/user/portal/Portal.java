package net.itkmitl.room.front.user.portal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.itkmitl.room.libs.peeranat.util.*;

import java.io.IOException;

public class Portal extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/hello-view.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(FewFile.getResource("account/Portal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());


//        String css = getClass().getResource("styles/portal.css").toExternalForm();
//        scene.getStylesheets().add(css);

        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        stage.setWidth(1280);
        stage.setHeight(720);
        stage.setResizable(false);
        stage.setTitle("Laew Tae Hong");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}