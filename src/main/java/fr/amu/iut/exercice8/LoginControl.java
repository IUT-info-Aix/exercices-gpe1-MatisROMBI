package fr.amu.iut.exercice8;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginControl extends GridPane {

    private int nbEtoiles = 0;
    private String mdp = " ";

    @FXML
    private TextField userName;

    @FXML
    private PasswordField pwd;

    @FXML
    private void okClicked() {
        System.out.println("Nom d'utilisateur : " + userName.getText());
        nbEtoiles = pwd.getText().length();
        for (int i = 0; i < nbEtoiles; i++) {
            mdp = mdp + ("*");
        }
        System.out.println("Mot de passe : " + mdp);
        mdp = "";
    }

    @FXML
    private void cancelClicked() {
        userName.clear();
        pwd.clear();
    }
}