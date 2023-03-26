package net.itkmitl.room;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class ControllerDemo {
    @FXML
    private ImageView saul;

    @FXML
    protected void onHelloButtonClick() {
        double w = 594;
        if (saul.getFitWidth() == w) {
            saul.setFitWidth(1280);
        } else {
            saul.setFitWidth(w);
        }
    }
}
