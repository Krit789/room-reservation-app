package net.itkmitl.room;

import javafx.application.Application;
import javafx.embed.swing.*;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;

public class SwingNodeExample extends Application {
    private JTable table = new JTable(new Object[][]{
            {"John", "Doe"},
            {"Jane", "Doe"},
            {"Bob", "Smith"}
    }, new Object[]{"First Name", "Last Name"});

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(table);

        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);
        Scene scene = new Scene(pane, 400, 400);

        stage.setScene(scene);
        stage.show();
    }
}
