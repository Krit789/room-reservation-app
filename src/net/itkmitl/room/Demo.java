package net.itkmitl.room;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.itkmitl.room.libs.peeranat.util.FewFile;

public class Demo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(FewFile.getResource("account/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
//        Image icon = new Image("icon.png");
//        stage.getIcons().add(icon);

        stage.setWidth(1280);
        stage.setHeight(720);
        stage.setResizable(false);
        stage.setTitle("Laew Tae Hong");
        stage.setScene(scene);
        stage.show();
    }
}

