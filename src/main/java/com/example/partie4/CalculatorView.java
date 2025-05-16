package com.example.partie4;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CalculatorView extends Pane {
    public CalculatorView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CalculatorView.fxml"));
        VBox root = loader.load();  // Ne PAS faire setRoot(...)
        this.getChildren().add(root);
    }
}
