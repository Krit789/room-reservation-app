package main.resources.account;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Login extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/hello-view.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("account/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
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