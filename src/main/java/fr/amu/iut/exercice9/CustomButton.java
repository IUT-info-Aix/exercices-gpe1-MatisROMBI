package fr.amu.iut.exercice9;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomButton extends Parent {

    private StackPane buttonStack;
    private ImageView image;

    public CustomButton() {
        buttonStack = new StackPane();

        try {
            // Plusieurs façons possibles d'accéder à l'image, l'une d'entre elles devrait fonctionner
            // Option 1: Chemin absolu dans le classpath
            image = new ImageView(new Image(getClass().getResourceAsStream("/exercice9/Rond.png")));

            // Si l'option 1 échoue, essayez l'une de ces alternatives:
            // Option 2: Chemin relatif au package
            // image = new ImageView(new Image(getClass().getResourceAsStream("Rond.png")));

            // Option 3: Utilisation du file: protocol (pour fichiers dans le système de fichiers)
            // image = new ImageView(new Image("file:src/main/resources/exercice9/Rond.png"));
        } catch (Exception e) {
            // En cas d'échec de chargement, créez une image par défaut pour éviter que l'application ne plante
            System.err.println("Impossible de charger l'image: " + e.getMessage());
            // Une image vide de 80x80 pixels
            image = new ImageView(new Image("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFAAAABQCAYAAACOEfKtAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAABmJLR0QA/wD/AP+gvaeTAAAAB3RJTUUH5QEFBgwadYjftQAAA9VJREFUeNrtnE9IVFEUxr975s2bGRwbcsqSTAoLImhTUZJEQRQRQUFBRRAt2kRRtIigTaugiKJFBC0iaBFBi2gRRdEiglaB/hG2KC0z0XHe7dwSHM3nNC3mncHvvZ/7wQHnnHsP95t7z333zoAQQgghhJCiQ1mOGhPGPpTB3wBQA6AKQFn2TwVAPoCnAGYA+ABMAQgAJLE4ik/DqMZNADsBbARQbRDiDMCvAMYADAC4DSHGP1QP5i86Qnl5bEm+P2hraKCUUqxbg+sQ1EOIrr5QOLQ5lfQ2JONeXzLu9fWHW0MbUklvneuuO+S6a/e0NdSTUoqOzo7P8Pw9rY0NlOe5FNB5EHVDCM7xKBiAEMckMFVKBR2dHd8hxKMOv58AUGtrKwVaOQjxTOr5GxqtQYdNQojYKdeFjEePpVLb0qnUDtdd/cnR2fmLLTzn+gPdFsDVRQCcnj+JiwC+/K8AHZ0dmHu1C56vgOdXnHPdPdmPDHzSgM5PCBGH569TAFT+QGtYKTXY1NRUN+96WTT46OzqegMhTrQ1NlK7P0AAQkRcz98I4GOhAnoKIY7LAL76QyGWOb/WhuZDixBtl2KxL5zr7nEAmBugiUPIuJlORO5DlHWbnMW3bNny3tCjhwWg7xDi/IVYbMrv9RCA9K/iKvbsOrRp48YCEP8QQpyUOeAzAIoADCcSW8fHx8MZLw3lCpEBIQIyEDmUTqW6CMAshIidTCQ+51dUAMDfI0fyZIBvZDCxj5QaYAApwGwCHiGDyJskJqmBAcxXCNHjQnGEaQDcNzRsA0yRIEqmz1MAl8g4LpO1cI3Mgs5QvAaYJktv6dYfC5AAlJQAlJQAlNQPAVjfFmqTmaCkBJCUAJKkBJCkBJCUAJKkBJCkBJCkBJCkBJCkBJCkBJCkBJCkBJCkBJCkBJCkBJCkBJCkBJCkBJCkBJCkBJCkBJCkBJCkLKJ6GEBhewWsBUi0yp4v1wBnZTARKJWA5X3SqikBBpAPVPemUt8ImCUAeYIAZACZB/LUVJEARkdGAPAUDvr9XulDDyDjaZAADMklnYFQKFhKEZsE4wBQW1sLgMkWDCQnSh1iSwZijwBkJwfnhQDcODVFP/1AXZJ21uC6azHXOOz4/ZpdLSUSCaTT6XRPMnmLdRZeS3cksmJ0dDTDvvCSiTkeCrVzT1jSCzdM8lXO7QvT3D4wzdY2kHUdtprZxqFx3kYcJnkbIlk+aJrfk8/z+wLLwMfLeH6fMwAAAABJRU5ErkJggg=="));
        }

        Label label = new Label("Clic");
        label.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        label.setTextFill(Color.ORANGERED);

        buttonStack.getChildren().addAll(image, label);
        this.getChildren().add(buttonStack);
    }

    // Remplacer la méthode qui cause l'erreur par la méthode originale
    public void addOnMousePressed(EventHandler<MouseEvent> eventHandler) {
        image.setOnMousePressed(eventHandler);
    }
}