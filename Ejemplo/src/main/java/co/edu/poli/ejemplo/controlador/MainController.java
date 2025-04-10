package co.edu.poli.ejemplo.controlador;

import co.edu.poli.ejemplo.modelo.GestorCliente;
import co.edu.poli.ejemplo.modelo.IProducto;
import co.edu.poli.ejemplo.modelo.Producto;
import co.edu.poli.ejemplo.modelo.ProductoProxy;
import co.edu.poli.ejemplo.modelo.ProductoReal;
import co.edu.poli.ejemplo.modelo.Proveedor;
import co.edu.poli.ejemplo.modelo.ProveedorFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtNivelUsuario;
    @FXML private Button btnVerDetalles;
    @FXML private TextArea txtResultado1;


    @FXML
    private Button btnActualizarCliente;

    @FXML
    private Button btnMostrarCliente;

    @FXML
    private Button btnVerPedidos;

    @FXML
    private TextArea txtResultado;

    private GestorCliente fachada;

        @FXML
    private TextField nombreProductoField;

    @FXML
    private TextField nombreProveedorField;

    @FXML
    private TextField direccionProveedorField;

    @FXML
    private Button agregarProductoBtn;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, String> colNombreProducto;

    @FXML
    private TableColumn<Producto, String> colNombreProveedor;

    @FXML
    private TableColumn<Producto, String> colDireccionProveedor;


        private final ObservableList<Producto> productos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        fachada = new GestorCliente();
        txtResultado.setText(" Aplicación iniciada. Usa los botones para interactuar.");
          colNombreProducto.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colNombreProveedor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProveedor().getNombre()));
        colDireccionProveedor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProveedor().getDireccion()));

        // Cargar lista a la tabla
        tablaProductos.setItems(productos);
    }

    @FXML
        private void agregarProducto() {
        String nombreProducto = nombreProductoField.getText().trim();
        String nombreProveedor = nombreProveedorField.getText().trim();
        String direccionProveedor = direccionProveedorField.getText().trim();

        if (nombreProducto.isEmpty() || nombreProveedor.isEmpty() || direccionProveedor.isEmpty()) {
            mostrarAlerta("Todos los campos son obligatorios.");
            return;
        }

        // Obtener proveedor desde el Flyweight
        Proveedor proveedor = ProveedorFactory.getProveedor(nombreProveedor, direccionProveedor);

        // Crear producto y agregarlo a la lista
        Producto producto = new Producto(nombreProducto, proveedor);
        productos.add(producto);

        limpiarCampos();
    }

    private void limpiarCampos() {
        nombreProductoField.clear();
        nombreProveedorField.clear();
        direccionProveedorField.clear();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Advertencia");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }


@FXML
    void VerDetallesProxy() {
        try {
            String nombre = txtNombre.getText();
            float precio = Float.parseFloat(txtPrecio.getText());
            int nivel = Integer.parseInt(txtNivelUsuario.getText());

            // Crear el producto real
            ProductoReal productoReal = new ProductoReal(nombre, precio);

            // Crear el proxy con el nivel de usuario
            IProducto proxy = new ProductoProxy(productoReal, nivel);

            // Mostrar resultado en el Label
            String resultado = proxy.verDetalles();
            txtResultado1.setText(resultado);

        } catch (NumberFormatException e) {
            txtResultado1.setText("⚠️ Error: Precio o nivel no válido.");
        } catch (Exception e) {
            txtResultado1.setText("❌ Ocurrió un error inesperado.");
        }
    }

    @FXML
void actualizarCliente(ActionEvent event) {
    String msg1 = fachada.actualizarInfo("Carlos Díaz", "Calle 456");
    String msg2 = fachada.realizarPedido("Audífonos Bluetooth");
    String msg3 = fachada.cambiarEstado("Nequi", false);

    txtResultado.setText("Cliente actualizado, pedido agregado y método de pago cambiado.\n"+"Resultados:\n" + msg1 + "\n" + msg2 + "\n" + msg3);
}

    @FXML
    void mostrarCliente(ActionEvent event) {
        String info = fachada.mostrarInfo();
        txtResultado.setText("Información del cliente:\n" + info);
    }

    @FXML
    void verPedidos(ActionEvent event) {
        String pedidos = fachada.verHistorial();
        String pagos = fachada.verPagos();
        txtResultado.setText("Historial de pedidos:\n" + pedidos + "\n\nMétodos de pago:\n" + pagos);
    }
}
