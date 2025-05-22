package fr.amu.iut.exercice13;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

@SuppressWarnings("Duplicates")
public class MainPersonnes extends Application {

    private static ObservableList<Personne> lesPersonnes;
    private static ListChangeListener<Personne> unChangementListener;
    private static ListChangeListener<Personne> plusieursChangementsListener;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initializeListeners();

        // Appel d'une question pour test
        question5(); // Changez ici selon la question à tester

        primaryStage.setTitle("Test JavaFX Listeners");
        primaryStage.show();

        // Fermeture automatique après 2 secondes pour voir les résultats
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            javafx.application.Platform.exit();
        }).start();
    }

    private static void initializeListeners() {
        // Liste observable qui écoute les changements sur la propriété age
        lesPersonnes = FXCollections.observableArrayList(personne -> new Observable[] {personne.ageProperty()});

        // Écouteur simple pour les questions 1-3
        unChangementListener = change -> {
            if (change.next()) {
                // Question 1: Gestion des ajouts
                if (change.wasAdded()) {
                    Personne personneAjoutee = change.getAddedSubList().get(0);
                    System.out.println("Personne ajoutée : " + personneAjoutee.getNom());
                }

                // Question 2: Gestion des suppressions
                if (change.wasRemoved()) {
                    Personne personneSupprimee = change.getRemoved().get(0);
                    System.out.println("Personne supprimée : " + personneSupprimee.getNom());
                }

                // Question 3: Gestion des modifications d'âge
                if (change.wasUpdated()) {
                    for (int i = change.getFrom(); i < change.getTo(); i++) {
                        Personne personneModifiee = lesPersonnes.get(i);
                        System.out.println(personneModifiee.getNom() + " a maintenant " +
                                personneModifiee.getAge() + " ans");
                    }
                }
            }
        };

        // Écouteur pour plusieurs changements (Question 5)
        plusieursChangementsListener = change -> {
            System.out.println("=== Début du traitement des changements ===");

            while (change.next()) {
                if (change.wasAdded()) {
                    for (Personne personneAjoutee : change.getAddedSubList()) {
                        System.out.println("Personne ajoutée : " + personneAjoutee.getNom());
                    }
                }

                if (change.wasRemoved()) {
                    for (Personne personneSupprimee : change.getRemoved()) {
                        System.out.println("Personne supprimée : " + personneSupprimee.getNom());
                    }
                }

                if (change.wasUpdated()) {
                    for (int i = change.getFrom(); i < change.getTo(); i++) {
                        Personne personneModifiee = lesPersonnes.get(i);
                        System.out.println(personneModifiee.getNom() + " a maintenant " +
                                personneModifiee.getAge() + " ans");
                    }
                }
            }

            System.out.println("=== Fin du traitement des changements ===");
        };

        // Choix de l'écouteur selon la question testée
        // Pour les questions 1-3, utilisez unChangementListener
        // Pour la question 5, utilisez plusieursChangementsListener
        lesPersonnes.addListener(plusieursChangementsListener);
    }

    public static void question1() {
        System.out.println("=== Test Question 1 : Ajouts ===");
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
    }

    public static void question2() {
        System.out.println("=== Test Question 2 : Ajouts et Suppression ===");
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        lesPersonnes.remove(paul);
    }

    public static void question3() {
        System.out.println("=== Test Question 3 : Modification d'âge ===");
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);

        // Cette modification sera maintenant détectée
        paul.setAge(5);
    }

    public static void question5() {
        System.out.println("=== Test Question 5 : Plusieurs changements ===");
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);

        // Ajout de plusieurs éléments en une fois
        lesPersonnes.addAll(pierre, paul, jacques);

        // Modification de l'âge de chaque personne
        for (Personne p : lesPersonnes)
            p.setAge(p.getAge() + 10);

        // Suppression de plusieurs éléments
        lesPersonnes.removeAll(paul, pierre);
    }
}