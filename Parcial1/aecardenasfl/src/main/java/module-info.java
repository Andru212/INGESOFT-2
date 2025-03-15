module co.edu.poli {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.poli.controlador to javafx.fxml;
    opens co.edu.poli.vista to javafx.fxml;
    exports co.edu.poli.controlador;
    exports co.edu.poli.vista;
}
