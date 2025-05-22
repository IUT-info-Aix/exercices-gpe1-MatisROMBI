package fr.amu.iut.exercice15;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class LoginControl extends BorderPane {

    private String mdp = "";

    @FXML
    private TextField userName;

    @FXML
    private PasswordField pwd;

    @FXML
    private Button ok;

    @FXML
    private Button cancel;

    @FXML
    private void initialize() {
        createBindings();
    }

    @FXML
    private void okClicked() {
        System.out.println("Nom d'utilisateur : " + userName.getText());
        mdp = "*".repeat(pwd.getText().length());
        System.out.println("Mot de passe : " + mdp);
        mdp = "";
    }

    @FXML
    private void cancelClicked() {
        userName.clear();
        pwd.clear();
    }

    private void createBindings() {
        // 1. pwd non éditable si username < 6 caractères
        pwd.editableProperty().bind(
                userName.textProperty().length().greaterThanOrEqualTo(6)
        );

        // 2. cancel désactivé si les deux champs sont vides
        BooleanBinding champsVides = userName.textProperty().isEmpty().and(pwd.textProperty().isEmpty());
        cancel.disableProperty().bind(champsVides);

        // 3. ok désactivé si mdp < 8 ou sans majuscule ou sans chiffre
        BooleanBinding motDePasseInvalide = Bindings.createBooleanBinding(() -> {
            String motDePasse = pwd.getText();
            return motDePasse.length() < 8 ||
                    !motDePasse.matches(".*[A-Z].*") ||
                    !motDePasse.matches(".*\\d.*");
        }, pwd.textProperty());

        ok.disableProperty().bind(motDePasseInvalide);
    }
}
