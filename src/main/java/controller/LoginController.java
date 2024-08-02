package controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import view.LoginView;
import view.RegistroView;
import view.ProductsView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private LoginView loginView;

    // Datos de conexión a la base de datos
    private static final String URL = "jdbc:mysql://bc2hky8dpornvthdni1y-mysql.services.clever-cloud.com:3306/bc2hky8dpornvthdni1y";
    private static final String USER = "upgfp6ned3m77ha4";
    private static final String PASSWORD = "TdAsLKdnXx0XEHNwKFCB";

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.loginView.getLoginButton().setOnAction(event -> handleLogin());
        this.loginView.getRegisterButton().setOnAction(event -> openRegisterView());
    }

    private void handleLogin() {
        String username = loginView.getUsernameField().getText();
        String password = loginView.getPasswordField().getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Por favor, complete todos los campos.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT fecha_nacimiento FROM clientes WHERE nombre = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Extraer la fecha de nacimiento del usuario encontrado
                java.sql.Date fechaNacimiento = resultSet.getDate("fecha_nacimiento");
                String expectedPassword = formatFechaNacimientoAsPassword(fechaNacimiento);

                if (password.equals(expectedPassword)) {
                    showAlert("Éxito", "Inicio de sesión exitoso.");
                    openProductsView();
                } else {
                    showAlert("Error", "Contraseña incorrecta.");
                }
            } else {
                showAlert("Error", "Usuario no encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Error al conectar con la base de datos.");
        }
    }

    private String formatFechaNacimientoAsPassword(java.sql.Date fechaNacimiento) {
        String[] parts = fechaNacimiento.toString().split("-");
        return parts[1] + parts[2]; // mmdd
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void openProductsView() {
        Stage loginStage = (Stage) loginView.getScene().getWindow();
        Stage productsStage = new Stage();
        ProductsView productsView = new ProductsView();
        Scene scene = new Scene(productsView, 800, 600);
        productsStage.setTitle("Productos");
        productsStage.setScene(scene);
        productsStage.show();

        // Cerrar la ventana de inicio de sesión
        loginStage.close();
    }

    private void openRegisterView() {
        Stage registerStage = new Stage();
        RegistroView registroView = new RegistroView(registerStage);
        // Configurar la escena y mostrar la ventana de registro
        registerStage.setScene(new Scene(registroView, 400, 300));
        registerStage.setTitle("Registro de Usuario");
        registerStage.show();

        // Cerrar la ventana de login actual
        Stage loginStage = (Stage) loginView.getScene().getWindow();
        loginStage.close();
    }
}

