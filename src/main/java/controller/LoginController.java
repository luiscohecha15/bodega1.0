package controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import view.LoginView;
import view.ProductsView;

public class LoginController {
    private LoginView loginView;
    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        initializeListeners();
    }
    private void initializeListeners() {
        loginView.getLoginButton().setOnAction(e -> handleLogin());
    }
    private void handleLogin() {
        String username = loginView.getUsernameField().getText();
        String password = loginView.getPasswordField().getText();
        if ("HHHH".equals(username) && "1234*".equals(password)) {
            showAlert("Éxito", "Inicio de sesión exitoso", Alert.AlertType.INFORMATION);
            // Aquí iría la lógica para abrir la siguiente ventana
            openProductosWindow();
        } else {
            showAlert("Error", "Usuario o contraseña incorrectos", Alert.AlertType.ERROR);
        }
    }
    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private void openProductosWindow() {
        ProductsView productosView = new ProductsView();
        ProductsController productosController = new ProductsController(productosView);
        Stage productosStage = new Stage();
        productosStage.setTitle("Consulta de Productos");
        productosStage.setScene(new Scene(productosView, 700, 400));
        productosStage.show();

        // Cerrar la ventana de login
        ((Stage) loginView.getScene().getWindow()).close();
    }


}
