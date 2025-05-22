package fr.amu.iut.exercice11;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Palette extends Application {

    // propriétés dynamiques
    private final IntegerProperty nbFois = new SimpleIntegerProperty(0);
    private final StringProperty message = new SimpleStringProperty("");
    private final StringProperty couleurPanneau = new SimpleStringProperty("#000000");

    private Label texteDuHaut;
    private Label texteDuBas;
    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Pane panneau;
    private HBox boutons;

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();

        texteDuHaut = new Label();
        texteDuHaut.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(texteDuHaut, Pos.CENTER);

        panneau = new Pane();
        panneau.setPrefSize(400, 200);

        VBox bas = new VBox();
        boutons = new HBox(10);
        boutons.setAlignment(Pos.CENTER);
        boutons.setPadding(new Insets(10, 5, 10, 5));

        texteDuBas = new Label();
        bas.setAlignment(Pos.CENTER_RIGHT);
        bas.getChildren().addAll(boutons, texteDuBas);

        // création des boutons
        vert = new Button("Vert");
        rouge = new Button("Rouge");
        bleu = new Button("Bleu");

        // événements
        vert.setOnAction(event -> handleButtonClick("Vert", "#00FF00"));
        rouge.setOnAction(event -> handleButtonClick("Rouge", "#FF0000"));
        bleu.setOnAction(event -> handleButtonClick("Bleu", "#0000FF"));

        // assemblage
        boutons.getChildren().addAll(vert, rouge, bleu);
        root.setCenter(panneau);
        root.setTop(texteDuHaut);
        root.setBottom(bas);

        // lier les propriétés
        createBindings();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Palette de Couleurs");
        primaryStage.show();
    }

    private void handleButtonClick(String nomCouleur, String couleurHex) {
        nbFois.set(nbFois.get() + 1);
        message.set("Le " + nomCouleur + " est une jolie couleur !");
        couleurPanneau.set(couleurHex);
    }

    private void createBindings() {
        // Binding conditionnel pour texte du haut
        BooleanBinding pasEncoreDeClic = Bindings.equal(nbFois, 0);

        texteDuHaut.textProperty().bind(
                Bindings.when(pasEncoreDeClic)
                        .then("Cliquez sur un bouton.")
                        .otherwise(Bindings.concat("Nombre de clics : ", nbFois.asString()))
        );

        // Binding du fond du panneau
        panneau.styleProperty().bind(
                Bindings.concat("-fx-background-color: ", couleurPanneau)
        );

        // texte du bas : texte et couleur dynamiques
        texteDuBas.textProperty().bind(message);
        texteDuBas.styleProperty().bind(
                Bindings.concat("-fx-text-fill: ", couleurPanneau)
        );
    }
}
