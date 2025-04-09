package co.edu.poli.ejemplo.controlador;

import co.edu.poli.ejemplo.modelo.GestorCliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MainController {

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
        txtResultado.setText("📄 Aplicación iniciada. Usa los botones para interactuar.");
    }

    @FXML
    void actualizarCliente(ActionEvent event) {
        fachada.actualizarInfo("Carlos Díaz", "Calle 456");
        fachada.realizarPedido("Audífonos Bluetooth");
        fachada.cambiarEstado("Nequi", false);
        txtResultado.setText("✅ Cliente actualizado, pedido agregado y método de pago cambiado.");
    }

    @FXML
    void mostrarCliente(ActionEvent event) {
        String info = fachada.mostrarInfo();
        txtResultado.setText("🧾 Información del cliente:\n" + info);
    }

    @FXML
    void verPedidos(ActionEvent event) {
        String pedidos = fachada.verHistorial();
        String pagos = fachada.verPagos();
        txtResultado.setText("📦 Historial de pedidos:\n" + pedidos + "\n\n💳 Métodos de pago:\n" + pagos);
    }
}
