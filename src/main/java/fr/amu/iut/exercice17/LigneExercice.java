package fr.amu.iut.exercice17;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LigneExercice extends HBox {

    private final Exercice exercice;
    private final TextField champReponse;

    public LigneExercice(Exercice exercice) {
        this.exercice = exercice;
        this.champReponse = new TextField();
        this.champReponse.setPrefColumnCount(4);

        Label enonce = new Label(exercice.getEnonce());
        enonce.setPadding(new Insets(8));
        enonce.setMinWidth(100);

        // ✅ Bordure verte + fond vert clair
        enonce.setBorder(new Border(new BorderStroke(
                Color.GREEN,
                BorderStrokeStyle.SOLID,
                new CornerRadii(5),
                new BorderWidths(2)
        )));
        enonce.setBackground(new Background(new BackgroundFill(
                Color.web("#90ee90"), // vert clair (lightgreen)
                new CornerRadii(5),
                Insets.EMPTY
        )));

        this.setSpacing(10);
        this.setPadding(new Insets(5));
        this.getChildren().addAll(enonce, champReponse);
    }

    public boolean isCorrect() {
        try {
            int reponse = Integer.parseInt(champReponse.getText().trim());
            return reponse == exercice.getSolution();
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
