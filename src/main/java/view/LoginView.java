package view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class LoginView extends GridPane {
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;

    public LoginView() {

        this.setPadding(new Insets(20));
        this.setVgap(10);
        this.setHgap(10);
        Label usernameLabel = new Label("Usuario:");
        usernameField = new TextField();
        Label passwordLabel = new Label("Contraseña:");
        passwordField = new PasswordField();
        loginButton = new Button("Iniciar Sesión");
        this.add(usernameLabel, 0, 0);
        this.add(usernameField, 1, 0);
        this.add(passwordLabel, 0, 1);
        this.add(passwordField, 1, 1);
        this.add(loginButton, 1, 2);
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
}