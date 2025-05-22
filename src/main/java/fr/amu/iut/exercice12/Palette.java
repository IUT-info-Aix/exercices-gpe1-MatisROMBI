package fr.amu.iut.exercice12;

import fr.amu.iut.exercice12.CustomButton;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Palette extends Application {
    private Label texteDuHaut;
    private Label texteDuBas;
    private BorderPane root;
    private CustomButton sourceOfEvent; // Pour garder une référence au dernier bouton cliqué

    @Override
    public void start(Stage primaryStage) {
        // Création des composants
        root = new BorderPane();
        texteDuHaut = new Label("Aucun bouton cliqué");
        texteDuBas = new Label("Cliquez sur un bouton");

        // Configuration des labels
        texteDuHaut.setFont(new Font("Arial", 20));
        texteDuBas.setFont(new Font("Arial", 15));

        // Création des boutons personnalisés
        CustomButton boutonRouge = new CustomButton("Rouge", Color.RED);
        CustomButton boutonVert = new CustomButton("Vert", Color.GREEN);
        CustomButton boutonBleu = new CustomButton("Bleu", Color.BLUE);

        // Création du conteneur pour les boutons
        HBox boutons = new HBox(10);
        boutons.getChildren().addAll(boutonRouge, boutonVert, boutonBleu);
        boutons.setAlignment(Pos.CENTER);
        boutons.setPadding(new Insets(10));

        // Gestionnaire d'événements générique pour tous les boutons
        EventHandler<ActionEvent> gestionnaireEvenement = event -> {
            // Récupération du bouton source
            sourceOfEvent = (CustomButton) event.getSource();

            // Incrémentation du compteur interne au bouton
            sourceOfEvent.setNbClics(sourceOfEvent.getNbClics() + 1);
        };

        // Ajout du gestionnaire d'événements à chaque bouton
        boutonRouge.setOnAction(gestionnaireEvenement);
        boutonVert.setOnAction(gestionnaireEvenement);
        boutonBleu.setOnAction(gestionnaireEvenement);

        // Création du listener qui sera associé à chaque compteur de bouton
        ChangeListener<Number> nbClicsListener = (observable, oldValue, newValue) -> {
            if (sourceOfEvent != null) {
                // Mise à jour du texteDuHaut
                texteDuHaut.setText("Bouton " + sourceOfEvent.getText() + " cliqué " + newValue + " fois");

                // Mise à jour du style du panneau
                String colorStyle = String.format("-fx-background-color: rgb(%d, %d, %d, 0.3);",
                        (int) (sourceOfEvent.getCouleur().getRed() * 255),
                        (int) (sourceOfEvent.getCouleur().getGreen() * 255),
                        (int) (sourceOfEvent.getCouleur().getBlue() * 255));
                root.setStyle(colorStyle);

                // Mise à jour du texteDuBas
                if (newValue.intValue() == 1) {
                    texteDuBas.setText("Le bouton " + sourceOfEvent.getText() + " a été cliqué 1 fois");
                } else {
                    texteDuBas.setText("Le bouton " + sourceOfEvent.getText() + " a été cliqué " + newValue + " fois");
                }
            }
        };

        // Association du listener aux propriétés nbClics de chaque bouton
        boutonRouge.nbClicsProperty().addListener(nbClicsListener);
        boutonVert.nbClicsProperty().addListener(nbClicsListener);
        boutonBleu.nbClicsProperty().addListener(nbClicsListener);

        // Organisation des composants dans la fenêtre
        VBox top = new VBox();
        top.getChildren().add(texteDuHaut);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(10));

        VBox bottom = new VBox();
        bottom.getChildren().add(texteDuBas);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(10));

        root.setTop(top);
        root.setCenter(boutons);
        root.setBottom(bottom);

        // Configuration de la scène
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Palette");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}