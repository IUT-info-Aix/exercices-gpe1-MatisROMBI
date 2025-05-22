package fr.amu.iut.exercice12;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class CustomButton extends Button {
    private final Color couleur;
    private final IntegerProperty nbClics = new SimpleIntegerProperty(0);

    public CustomButton(String text, Color couleur) {
        super(text);
        this.couleur = couleur;
    }

    // Getter pour la propriété nbClics
    public IntegerProperty nbClicsProperty() {
        return nbClics;
    }

    // Getter pour la valeur de nbClics
    public int getNbClics() {
        return nbClics.get();
    }

    // Setter pour nbClics
    public void setNbClics(int nbClics) {
        this.nbClics.set(nbClics);
    }

    // Getter pour couleur
    public Color getCouleur() {
        return couleur;
    }
}