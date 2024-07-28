package view;


import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Product;


public class ProductsView extends VBox {
    private TableView<Product> tablaProductos;
    private Button btnConsultar;
    private TextField txtNombre;
    private TextField txtPrecio;
    private TextField txtUbicacion;
    public ProductsView(){
        // Campos de búsqueda
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");
        txtPrecio = new TextField();
        txtPrecio.setPromptText("Precio");
        txtUbicacion = new TextField();
        txtUbicacion.setPromptText("Ubicación");
        HBox searchBox = new HBox(10);
        searchBox.getChildren().addAll(
                new Label("Nombre:"), txtNombre,
                new Label("Precio:"), txtPrecio,
                new Label("Ubicación:"), txtUbicacion
        );
        btnConsultar = new Button("Consultar Productos");
        tablaProductos = new TableView<>();
        TableColumn<Product, Number> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(cellData -> cellData.getValue().idProductoProperty());
        TableColumn<Product, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        TableColumn<Product, Number> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(cellData -> cellData.getValue().precioProperty());
        TableColumn<Product, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());
        TableColumn<Product, String> colUbicacion = new TableColumn<>("Ubicación");
        colUbicacion.setCellValueFactory(cellData -> cellData.getValue().ubicacionProperty());
        tablaProductos.getColumns().addAll(colId, colNombre, colPrecio, colTipo, colUbicacion);
        this.setSpacing(10);
        this.getChildren().addAll(searchBox, btnConsultar, tablaProductos);
    }


    // Getters para los nuevos campos
    public TextField getTxtNombre() { return txtNombre; }
    public TextField getTxtPrecio() { return txtPrecio; }
    public TextField getTxtUbicacion() { return txtUbicacion; }
    // Getters existentes
    public TableView<Product> getTablaProductos() { return tablaProductos; }
    public Button getBtnConsultar() { return btnConsultar; }
}


