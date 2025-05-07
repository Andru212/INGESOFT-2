package com.example.controlador;

import com.example.modelo.DescuentoFijo;
import com.example.modelo.DescuentoPorcentaje;
import com.example.modelo.Historial;
import com.example.modelo.PriceStrategy;
import com.example.modelo.Producto;
import com.example.modelo.ProductoMemento;
import com.example.modelo.SinDescuento;
import com.example.modelo.VistaLabel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button BttAplicar;

    @FXML
    private Button BttGuardarEstado;

    @FXML
    private Button BttRestaurarEstado;

    @FXML
    private Label MostrarFinal;

    @FXML
    private Label EstadoObserver;

    @FXML
    private TextField NombreProducto;

    @FXML
    private TextField PrecioBase;

    @FXML
    private ComboBox<String> Seleccion;

    private Producto producto;
    private Historial historial;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Seleccion.getItems().addAll("Sin descuento", "Descuento fijo", "Descuento porcentual");
        Seleccion.getSelectionModel().selectFirst(); // por defecto

        // Producto inicial con valores por defecto
        producto = new Producto("Producto", 0.0);

        // Iniciar historial para Memento
        historial = new Historial();

        // Agregar observador que actualiza el Label EstadoObserver
        VistaLabel vista = new VistaLabel(EstadoObserver);
        producto.agregarObservador(vista);

        // Disparar notificación inicial
        producto.setNombre(producto.getNombre());
    }

    @FXML
    void AplicarDescuento(ActionEvent event) {
        try {
            String nombre = NombreProducto.getText();
            double base = Double.parseDouble(PrecioBase.getText());

            producto.setNombre(nombre);
            producto.setPrecioBase(base);

            String estrategiaSeleccionada = Seleccion.getValue();
            PriceStrategy estrategia;

            switch (estrategiaSeleccionada) {
                case "Descuento fijo":
                    estrategia = new DescuentoFijo();
                    break;
                case "Descuento porcentual":
                    estrategia = new DescuentoPorcentaje();
                    break;
                case "Sin descuento":
                default:
                    estrategia = new SinDescuento();
                    break;
            }

            producto.setEstrategia(estrategia);
            double precioFinal = producto.getPrecioFinal();

            MostrarFinal.setText(String.format("$ %.2f", precioFinal));

        } catch (NumberFormatException e) {
            MostrarFinal.setText("Precio inválido");
        }
    }

    @FXML
    void Guardar_estado(ActionEvent event) {
        historial.guardar(producto.guardarEstado());
        EstadoObserver.setText("Estado guardado");
    }

    @FXML
    void RestaurarEstado(ActionEvent event) {
        ProductoMemento estado = historial.deshacer();
        if (estado != null) {
            producto.restaurarEstado(estado);
            EstadoObserver.setText("Estado restaurado");
        } else {
            EstadoObserver.setText("No hay estados guardados");
        }
    }
}
