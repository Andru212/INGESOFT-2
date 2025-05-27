module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; 

    exports com.example.vista;
    exports com.example.controlador;
    opens com.example.modelo to javafx.base;
    opens com.example.vista to javafx.graphics; // Allow JavaFX graphics module to access the view
    opens com.example.controlador to javafx.fxml; 
    
}
