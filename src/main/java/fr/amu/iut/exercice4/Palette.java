package fr.amu.iut.exercice4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.awt.*;

public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Label label;
    private Pane panneau;
    private HBox bas;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Définition des paramètres de la fenêtre
        Parameters params = getParameters();
        int width = 400;
        int height = 200;

        // création d'une scène
        root = new BorderPane();
        Scene scene = new Scene(root, width, height);

        //Défintion de la scène principale
        primaryStage.setScene(scene);
        primaryStage.setTitle("Palette");

        //Défintion des buttons
        vert = new Button("Vert");
        rouge = new Button("Rouge");
        bleu = new Button("Bleu");

        //Groupage des boutons dans une HBox "RGB"
        HBox RGB = new HBox(vert, rouge, bleu);
        RGB.setSpacing(10);
        BorderPane.setMargin(RGB, new Insets(0, 0, 10, 0));

        //Ajout de la VBox "RGB" dans le bas de la page
        root.setBottom(RGB);
        RGB.setAlignment(Pos.CENTER);

        //EventHandler qui permettent de changer la couleur de fond
        vert.setOnAction(e -> changerBackgroudColor(Color.GREEN));
        rouge.setOnAction(e -> changerBackgroudColor(Color.RED));
        bleu.setOnAction(e -> changerBackgroudColor(Color.BLUE));

        // Affichage de la fenêtre
        primaryStage.show();
    }

    //Méthode pour changer la couleur de fond
    private void changerBackgroudColor(Color color) {
        root.setBackground(new Background(new BackgroundFill(color, null, null)));
    }
}

