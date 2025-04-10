package co.edu.poli.ejemplo.controlador;

import co.edu.poli.ejemplo.modelo.GestorCliente;
import co.edu.poli.ejemplo.modelo.IProducto;
import co.edu.poli.ejemplo.modelo.ProductoProxy;
import co.edu.poli.ejemplo.modelo.ProductoReal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    public void initialize() {
        fachada = new GestorCliente();
        txtResultado.setText(" Aplicación iniciada. Usa los botones para interactuar.");
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
