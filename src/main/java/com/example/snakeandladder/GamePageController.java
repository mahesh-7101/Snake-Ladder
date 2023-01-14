package com.example.snakeandladder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GamePageController {
    @FXML
    public void start(MouseEvent event) throws IOException {
        AnchorPane start= FXMLLoader.load(getClass().getResource("PlayArea.fxml"));
        HelloApplication.root.getChildren().setAll(start);
    }
}
