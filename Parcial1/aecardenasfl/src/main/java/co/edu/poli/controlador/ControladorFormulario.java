package co.edu.poli.controlador;

import co.edu.poli.modelo.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControladorFormulario {

    @FXML
    private TextField caja1;

    @FXML
    private TextField caja2;

    @FXML
    private Button guardar;

    Cliente cliente;

    @FXML
    void guardarClick(ActionEvent event) {

        cliente = new Cliente(caja2.getText(), caja1.getText());
        
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setContentText(cliente.toString());
        alerta.show();

    }

}
