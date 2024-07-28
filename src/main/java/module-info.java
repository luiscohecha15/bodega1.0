module org.example.productosappmvc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens org.example.productosappmvc to javafx.fxml;
    exports org.example.productosappmvc;
}