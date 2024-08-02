package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginView extends GridPane {
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button registerButton;

    public LoginView() {
        this.setPadding(new Insets(20));
        this.setVgap(10);
        this.setHgap(10);

        Label usernameLabel = new Label("Usuario:");
        usernameField = new TextField();
        Label passwordLabel = new Label("Contraseña:");
        passwordField = new PasswordField();
        loginButton = new Button("Iniciar Sesión");
        registerButton = new Button("Registrarse");

        this.add(usernameLabel, 0, 0);
        this.add(usernameField, 1, 0);
        this.add(passwordLabel, 0, 1);
        this.add(passwordField, 1, 1);
        this.add(loginButton, 1, 2);
        this.add(registerButton, 1, 3);

        Image backgroundImage = new Image(getClass().getResource("/img/back.jpg").toExternalForm());
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        this.setBackground(new Background(background));

        // Acción del botón de registro
        registerButton.setOnAction(event -> openRegistroView());
    }

    private void openRegistroView() {
        Stage loginStage = (Stage) this.getScene().getWindow();
        Stage registroStage = new Stage();
        RegistroView registroView = new RegistroView(loginStage);
        Scene scene = new Scene(registroView, 400, 300);
        registroStage.setTitle("Registro de Usuario");
        registroStage.setScene(scene);
        registroStage.show();

        // Ocultar la ventana de login mientras la de registro está abierta
        loginStage.hide();
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getRegisterButton() {
        return registerButton;
    }
}

