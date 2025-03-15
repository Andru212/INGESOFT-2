module co.edu.poli.ejemplo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; 
    
    exports co.edu.poli.ejemplo.vista; // This is for your view
    exports co.edu.poli.ejemplo.controlador; // Export your controller package
    opens co.edu.poli.ejemplo.modelo to javafx.base;
    opens co.edu.poli.ejemplo.vista to javafx.graphics; // Allow JavaFX graphics module to access the view
    opens co.edu.poli.ejemplo.controlador to javafx.fxml; 
}