package fr.amu.iut.exercice10;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class Conteneurs extends Application {
    @FXML
    private TextField emailField;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
    );

    public boolean isEmailValid() {
        String email = emailField.getText();
        return EMAIL_PATTERN.matcher(email).matches();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            // Charger le fichier FXML une seule fois
            Parent root = FXMLLoader.load(getClass().getResource("ConteneursView.fxml"));

            // Créer une nouvelle scène avec le nœud racine chargé
            Scene scene = new Scene(root, 600, 400);

            // Définir la scène et afficher le stage
            stage.setScene(scene);
            stage.setTitle("Premier exemple manipulant les conteneurs");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
