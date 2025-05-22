package fr.amu.iut.exercice9;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class CustomButton extends Button {

    public CustomButton(String text, Color color) {
        super(text);
        setTextFill(color);
        setPrefSize(100, 50);
    }
}