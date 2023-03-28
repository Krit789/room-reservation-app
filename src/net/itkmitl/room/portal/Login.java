package net.itkmitl.room.portal;

import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Login extends Portal{

    @Override
    public void start(Stage stage) throws IOException {
        super.start(stage);
        Button button = new Button("Click me!");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
