package com.example.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.modelo.DescuentoFijo;
import com.example.modelo.DescuentoPorcentaje;
import com.example.modelo.PriceStrategy;
import com.example.modelo.Producto;
import com.example.modelo.SinDescuento;

public class MainController implements Initializable {

    @FXML
    private Button BttAplicar;

    @FXML
    private Label MostrarFinal;

    @FXML
    private TextField NombreProducto;

    @FXML
    private TextField PrecioBase;

    @FXML
    private ComboBox<String> Seleccion;

    private Producto producto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Seleccion.getItems().addAll("Sin descuento", "Descuento fijo", "Descuento porcentual");
        Seleccion.getSelectionModel().selectFirst(); // por defecto
    }

    @FXML
    void AplicarDescuento(ActionEvent event) {
        try {
            String nombre = NombreProducto.getText();
            double base = Double.parseDouble(PrecioBase.getText());

            producto = new Producto(nombre, base);

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
}
