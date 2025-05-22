package fr.amu.iut.exercice14;

import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.beans.Observable;

@SuppressWarnings("Duplicates")
public class MainPersonnes {

    private static SimpleListProperty<Personne> lesPersonnes;
    private static IntegerProperty ageMoyen;
    private static IntegerProperty nbParisiens;

    private static IntegerBinding calculAgeMoyen;
    private static IntegerBinding calculNbParisiens;

    public static void main(String[] args) {

        // Bind à chaque changement dans la liste OU dans les propriétés des éléments (age, ville)
        lesPersonnes = new SimpleListProperty<>(
                FXCollections.observableArrayList(personne -> new Observable[]{
                        personne.ageProperty(),
                        personne.villeDeNaissanceProperty()
                })
        );

        ageMoyen = new SimpleIntegerProperty(0);
        nbParisiens = new SimpleIntegerProperty(0);

        // --- Binding bas niveau pour âge moyen ---
        calculAgeMoyen = new IntegerBinding() {
            {
                bind(lesPersonnes);
                lesPersonnes.addListener((ListChangeListener<Personne>) change -> {
                    while (change.next()) {
                        if (change.wasAdded() || change.wasRemoved()) {
                            invalidate();
                        }
                    }
                });
                for (Personne p : lesPersonnes) {
                    bind(p.ageProperty());
                }
            }

            @Override
            protected int computeValue() {
                if (lesPersonnes.isEmpty()) return 0;
                int somme = 0;
                for (Personne p : lesPersonnes) {
                    somme += p.getAge();
                }
                return somme / lesPersonnes.size();
            }
        };

        // Binding de la propriété ageMoyen à ce binding calculé
        ageMoyen.bind(calculAgeMoyen);

        // --- Binding bas niveau pour nombre de parisiens ---
        calculNbParisiens = new IntegerBinding() {
            {
                bind(lesPersonnes);
                lesPersonnes.addListener((ListChangeListener<Personne>) change -> {
                    while (change.next()) {
                        if (change.wasAdded() || change.wasRemoved()) {
                            invalidate();
                        }
                    }
                });
                for (Personne p : lesPersonnes) {
                    bind(p.villeDeNaissanceProperty());
                }
            }

            @Override
            protected int computeValue() {
                int count = 0;
                for (Personne p : lesPersonnes) {
                    if ("Paris".equalsIgnoreCase(p.getVilleDeNaissance())) {
                        count++;
                    }
                }
                return count;
            }
        };

        nbParisiens.bind(calculNbParisiens);

        // Test :
        question1();
        question2();
    }

    public static void question1() {
        System.out.println("1 - L'âge moyen est de " + ageMoyen.get() + " ans");
        Personne pierre = new Personne("Pierre", 20);
        lesPersonnes.add(pierre);
        System.out.println("2 - L'âge moyen est de " + ageMoyen.get() + " ans");
        Personne paul = new Personne("Paul", 40);
        lesPersonnes.add(paul);
        System.out.println("3 - L'âge moyen est de " + ageMoyen.get() + " ans");
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(jacques);
        System.out.println("4 - L'âge moyen est de " + ageMoyen.get() + " ans");
        paul.setAge(100);
        System.out.println("5 - L'âge moyen est de " + ageMoyen.get() + " ans");
        lesPersonnes.remove(paul);
        System.out.println("6 - L'âge moyen est de " + ageMoyen.get() + " ans");
    }

    public static void question2() {
        System.out.println("Il y a " + nbParisiens.get() + " parisiens");
        lesPersonnes.get(0).setVilleDeNaissance("Marseille");
        System.out.println("Il y a " + nbParisiens.get() + " parisiens");
        lesPersonnes.get(1).setVilleDeNaissance("Montpellier");
        System.out.println("Il y a " + nbParisiens.get() + " parisiens");
        for (Personne p : lesPersonnes)
            p.setVilleDeNaissance("Paris");
        System.out.println("Il y a " + nbParisiens.get() + " parisiens");
    }
}
