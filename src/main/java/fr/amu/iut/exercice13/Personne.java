package fr.amu.iut.exercice13;

// AJOUT: Imports nécessaires
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Personne {
    private String nom;
    // CHANGEMENT: Remplacer "private int age;" par cette ligne
    private IntegerProperty age;

    public Personne(String nom, int age) {
        this.nom = nom;
        // CHANGEMENT: Remplacer "this.age = age;" par cette ligne
        this.age = new SimpleIntegerProperty(age);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // CHANGEMENT: Modifier cette méthode
    public int getAge() {
        return age.get();  // au lieu de "return age;"
    }

    // CHANGEMENT: Modifier cette méthode
    public void setAge(int age) {
        this.age.set(age);  // au lieu de "this.age = age;"
    }

    // AJOUT: Cette méthode est OBLIGATOIRE - elle n'existait pas avant
    public IntegerProperty ageProperty() {
        return age;
    }

    @Override
    public String toString() {
        // CHANGEMENT: Modifier cette ligne si elle existait
        return nom + " (" + age.get() + " ans)";  // au lieu de age directement
    }
}