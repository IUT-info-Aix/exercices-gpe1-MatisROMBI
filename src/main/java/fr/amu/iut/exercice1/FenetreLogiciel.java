package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FenetreLogiciel extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Récupération des arguments passés en ligne de commande
        Parameters params = getParameters();
        int width = 600;
        int height = 400;

        if (params.getRaw().size() > 0) {
            // Utiliser le premier argument passé en ligne de commande comme largeur de la fenêtre
            width = Integer.parseInt(params.getRaw().get(0));
        }

        if (params.getRaw().size() > 1) {
            // Utiliser le deuxième argument passé en ligne de commande comme hauteur de la fenêtre
            height = Integer.parseInt(params.getRaw().get(1));
        }


        // Création d'une scène
        BorderPane fenetre = new BorderPane();
        Scene scene = new Scene(fenetre, width, height);


        // Définition de la scène principale
        primaryStage.setScene(scene);
        primaryStage.setTitle("Premier exemple manipulant les conteneurs");

        // Définition des éléments de la barre du haut
        MenuBar menuBar = new MenuBar();

        // Définition de l'élément file et de ses sous-items
        Menu menuFile = new Menu("File");
        MenuItem itemNew = new MenuItem("New");
        MenuItem itemOpen = new MenuItem("Open");
        MenuItem itemClose = new MenuItem("Close");
        menuFile.getItems().addAll(itemNew, itemOpen, itemClose);

        // Définition de l'élément Edit et de ses sous-items
        Menu menuEdit = new Menu("Edit");
        MenuItem itemCut = new MenuItem("Cut");
        MenuItem itemCopy = new MenuItem("Copy");
        MenuItem itemPaste = new MenuItem("Paste");
        menuEdit.getItems().addAll(itemCut, itemCopy, itemPaste);

        // Définition de l'élément Help
        Menu menuHelp = new Menu("Help");

        // Ajout des éléments dans menuBar
        fenetre.setTop(menuBar);
        menuBar.getMenus().addAll(menuFile, menuEdit, menuHelp);


        // Défintion des éléments de la partie gauche de la fenêtre
        Label Boutons = new Label("Boutons : ");
        Button Bouton1 = new Button("Bouton1");
        Button Bouton2 = new Button("Bouton2");
        Button Bouton3 = new Button("Bouton3");

        // Définition des éléments dans la barre de gauche
        VBox barreDeGauche = new VBox(Boutons, Bouton1, Bouton2, Bouton3);

        // Ajout des éléments dans la partie de gauche
        fenetre.setLeft(barreDeGauche);
        barreDeGauche.setAlignment(Pos.CENTER);



        // Création du contenu du label de bas de page
        Label label = new Label("Ceci est un label de bas de page");

        // Ajout des éléments de bas de page dans "basDePage"
        HBox basDePage = new HBox(label);

        // Ajout de "basDePage" en bas de la page
        fenetre.setBottom(basDePage);
        basDePage.setAlignment(Pos.CENTER);


        // Création du contenu du centre
        Label name = new Label("Name : ");
        TextField nameText = new TextField();
        Label email = new Label("Email : ");
        TextField emailText = new TextField();
        Label password = new Label("Password : ");
        PasswordField passwordText = new PasswordField();
        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");

        // Ajout des éléments du centre dans centre
        GridPane centre = new GridPane(name, nameText, email, emailText, password, passwordText, submit, cancel);

        // Ajout de centre au centre
        fenetre.setCenter(centre);

        // Affichage de la fenêtre
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);




    }
}

