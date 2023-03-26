package net.itkmitl.room.gui;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;

public class Login extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    protected Image createImage(String path) throws FileNotFoundException {
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == '.') {
                path = path.substring(0, i) + "/" + path.substring(i + 1);
            }
        }
        for (int i = path.length() - 1; i >= 0; i--) {
            if (path.charAt(i) == '/') {
                path = path.substring(0, i) + "." + path.substring(i + 1);
                break;
            }
        }

        String file = new File(path).toURI().toString().replace(path, "src/" + path);
        Image image = new Image(file);
        return image;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, Color.YELLOW);

        stage.setTitle("Laew Tae Hong");
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.setResizable(false);
        stage.getIcons().add(createImage("main.resources.gui.icon.png"));
        stage.setScene(scene);
        stage.show();
    }
}
