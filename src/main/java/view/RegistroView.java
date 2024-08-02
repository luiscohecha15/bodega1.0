package view;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class RegistroView extends GridPane {
    private TextField nombreField;
    private DatePicker fechaNacimientoPicker;
    private Button registrarButton;
    private Button volverButton;
    private Stage primaryStage;

    // Datos de conexión a la base de datos
    private static final String URL = "jdbc:mysql://bc2hky8dpornvthdni1y-mysql.services.clever-cloud.com:3306/bc2hky8dpornvthdni1y";
    private static final String USER = "upgfp6ned3m77ha4";
    private static final String PASSWORD = "TdAsLKdnXx0XEHNwKFCB";

    public RegistroView(Stage primaryStage) {
        this.primaryStage = primaryStage;

        this.setPadding(new Insets(20));
        this.setVgap(10);
        this.setHgap(10);

        Label nombreLabel = new Label("Nombre:");
        nombreField = new TextField();
        Label fechaNacimientoLabel = new Label("Fecha de Nacimiento:");
        fechaNacimientoPicker = new DatePicker();

        registrarButton = new Button("Registrar");
        registrarButton.setOnAction(event -> registrarUsuario());

        volverButton = new Button("Volver a Inicio de Sesión");
        volverButton.setOnAction(event -> volverAlLogin());

        this.add(nombreLabel, 0, 0);
        this.add(nombreField, 1, 0);
        this.add(fechaNacimientoLabel, 0, 1);
        this.add(fechaNacimientoPicker, 1, 1);
        this.add(registrarButton, 1, 2);
        this.add(volverButton, 1, 3);
    }

    private void registrarUsuario() {
        String nombre = nombreField.getText();
        LocalDate fechaNacimiento = fechaNacimientoPicker.getValue();

        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar en blanco.");
            return;
        }

        if (fechaNacimiento == null || !esMayorDeEdad(fechaNacimiento)) {
            System.out.println("Debe ser mayor de edad para registrarse.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO clientes (nombre, fecha_nacimiento) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setDate(2, java.sql.Date.valueOf(fechaNacimiento));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Usuario registrado con éxito.");
                volverAlLogin();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al registrar el usuario en la base de datos.");
        }
    }

    private boolean esMayorDeEdad(LocalDate fechaNacimiento) {
        return fechaNacimiento.plusYears(18).isBefore(LocalDate.now());
    }

    private void volverAlLogin() {
        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();

        primaryStage.show();
    }
}
