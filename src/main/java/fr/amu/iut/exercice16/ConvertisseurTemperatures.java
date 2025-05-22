package fr.amu.iut.exercice16;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class ConvertisseurTemperatures extends Application {

    private boolean isUpdating = false;

    @Override
    public void start(Stage primaryStage) {
        // Sliders
        Slider celsiusSlider = new Slider(0, 100, 0);
        Slider fahrenheitSlider = new Slider(32, 212, 32);

        celsiusSlider.setOrientation(Orientation.VERTICAL);
        fahrenheitSlider.setOrientation(Orientation.VERTICAL);

        // Graduation Celsius
        celsiusSlider.setShowTickMarks(true);
        celsiusSlider.setShowTickLabels(true);
        celsiusSlider.setMajorTickUnit(10);
        celsiusSlider.setMinorTickCount(4);
        celsiusSlider.setSnapToTicks(true);

        // Graduation Fahrenheit
        fahrenheitSlider.setShowTickMarks(true);
        fahrenheitSlider.setShowTickLabels(true);
        fahrenheitSlider.setMajorTickUnit(20);
        fahrenheitSlider.setMinorTickCount(4);
        fahrenheitSlider.setSnapToTicks(true);

        // Labels
        Label celsiusLabel = new Label("°C");
        Label fahrenheitLabel = new Label("°F");

        // TextFields
        TextField celsiusField = new TextField();
        TextField fahrenheitField = new TextField();

        // Convertisseurs texte <-> nombre
        StringConverter<Number> converter = new NumberStringConverter();
        Bindings.bindBidirectional(celsiusField.textProperty(), celsiusSlider.valueProperty(), converter);
        Bindings.bindBidirectional(fahrenheitField.textProperty(), fahrenheitSlider.valueProperty(), converter);

        // Listeners pour synchronisation manuelle bidirectionnelle
        celsiusSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (isUpdating) return;
            isUpdating = true;
            double fahrenheit = newVal.doubleValue() * 9 / 5 + 32;
            fahrenheitSlider.setValue(fahrenheit);
            isUpdating = false;
        });

        fahrenheitSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (isUpdating) return;
            isUpdating = true;
            double celsius = (newVal.doubleValue() - 32) * 5 / 9;
            celsiusSlider.setValue(celsius);
            isUpdating = false;
        });

        // Layout
        VBox celsiusBox = new VBox(10, celsiusLabel, celsiusSlider, celsiusField);
        celsiusBox.setAlignment(Pos.CENTER);

        VBox fahrenheitBox = new VBox(10, fahrenheitLabel, fahrenheitSlider, fahrenheitField);
        fahrenheitBox.setAlignment(Pos.CENTER);

        HBox root = new HBox(20, celsiusBox, fahrenheitBox);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(root, 250, 500);
        primaryStage.setTitle("Convertisseur de Température");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
