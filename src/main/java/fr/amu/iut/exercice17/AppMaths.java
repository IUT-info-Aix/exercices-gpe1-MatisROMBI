package fr.amu.iut.exercice17;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;
import java.util.stream.Collectors;

public class AppMaths extends Application {

    private VBox exercicesBox = new VBox(10);
    private ComboBox<Integer> comboBox;
    private List<LigneExercice> lignes = new ArrayList<>();
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;

        comboBox = new ComboBox<>();
        comboBox.getItems().addAll(6, 9, 12, 15);
        comboBox.setValue(6); // Valeur par défaut

        Label label = new Label("Nombre d'exercices ?");

        ScrollPane scrollPane = new ScrollPane(exercicesBox);
        scrollPane.setFitToWidth(true);  // pour que les éléments fassent toute la largeur
        scrollPane.setPrefHeight(350);   // ajustable selon besoin

        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(10));
        root.getChildren().addAll(label, comboBox, scrollPane);

        comboBox.valueProperty().addListener((obs, oldVal, newVal) -> mettreAJourExercices(newVal));
        mettreAJourExercices(comboBox.getValue());

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("App Maths");
        stage.show();
    }

    private void mettreAJourExercices(int n) {
        List<LigneExercice> incorrects = lignes.stream()
                .filter(l -> !l.isCorrect())
                .limit(n)
                .collect(Collectors.toList());

        exercicesBox.getChildren().clear();
        lignes.clear();
        lignes.addAll(incorrects);

        while (lignes.size() < n) {
            lignes.add(new LigneExercice(new Exercice()));
        }

        exercicesBox.getChildren().addAll(lignes);

        Button valider = new Button("Voir les résultats");
        valider.setOnAction(e -> verifierResultats());
        exercicesBox.getChildren().add(valider);

        Platform.runLater(() -> {
            if (primaryStage != null) {
                primaryStage.sizeToScene();
            }
        });
    }

    private void verifierResultats() {
        long nbCorrects = lignes.stream().filter(LigneExercice::isCorrect).count();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Résultat");
        alert.setHeaderText(null);
        alert.setContentText(nbCorrects + " réponses correctes sur " + lignes.size());
        alert.showAndWait();
    }
}
