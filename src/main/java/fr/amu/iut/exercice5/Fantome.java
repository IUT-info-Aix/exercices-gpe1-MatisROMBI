package fr.amu.iut.exercice5;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Fantome extends Personnage {

    private Rectangle basCorps;


    private Circle oeilGauche;
    private Circle retineGauche;
    private Circle retineGaucheGauche;

    private Circle oeilDroit;
    private Circle retineDroite;
    private Circle retineDroiteGauche;


    public Fantome() {
        super("droite", Color.BLUE, Color.BLUE);
        basCorps = new Rectangle(0, 10, 20, 10);
        basCorps.setFill(Color.BLUE);

        oeilGauche = new Circle(6, 6, 2, Color.WHITE);
        //regarde vers la droite
        retineGauche = new Circle(oeilGauche.getCenterX() + 1, 6, 1, Color.BLACK);

        oeilDroit = new Circle(14, 6, 2, Color.WHITE);
        //regarde vers la droite
        retineDroite = new Circle(oeilDroit.getCenterX() + 1, 6, 1, Color.BLACK);

        //regarde vers la gauche
        retineDroiteGauche = new Circle(oeilDroit.getCenterX() - 1, 3, 1, Color.BLACK);

        //regarde vers la gauche
        retineGaucheGauche = new Circle(oeilGauche.getCenterX() - 1, 3, 1, Color.BLACK);


        super.getChildren().add(basCorps);
        super.getChildren().add(oeilGauche);
        super.getChildren().add(retineGauche);
        super.getChildren().add(oeilDroit);
        super.getChildren().add(retineDroite);
    }

    @Override
    public void deplacerAGauche() {
        super.deplacerAGauche();
        //Placement des yeux
        retineGaucheGauche.setLayoutX(retineGaucheGauche.getLayoutX() - 2);
        retineDroiteGauche.setLayoutX(retineDroiteGauche.getLayoutX() - 2);
    }

    @Override
    public void deplacerADroite(double largeurJeu) {
        super.deplacerADroite(largeurJeu);
        //Placement des yeux
        retineGauche.setLayoutX(retineGauche.getLayoutX());
        retineDroite.setLayoutX(retineDroite.getLayoutX());
    }

    @Override
    public void deplacerEnBas(double hauteurJeu) {
        super.deplacerEnBas(hauteurJeu);
        //Placement des yeux
        retineGauche.setLayoutX(retineGauche.getLayoutX());
        retineGauche.setLayoutY(retineGauche.getLayoutY());
    }

    @Override
    public void deplacerEnHaut() {
        super.deplacerEnHaut();
        //Placement des yeux
        retineGauche.setLayoutX(retineGauche.getLayoutX());
        retineGauche.setLayoutY(retineGauche.getLayoutY());
    }


}
