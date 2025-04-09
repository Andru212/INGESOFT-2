package co.edu.poli.ejemplo.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Singleton {

    private static Connection connection;

    // Método estático para obtener la única instancia de la conexión
    public static Connection getConnection() throws SQLException {
        ResourceBundle rd = ResourceBundle.getBundle("config");
        
        // Variables de configuración para la base de datos
        String dbURL = rd.getString("db.url");
        String dbUSERNAME = rd.getString("db.username");
        String dbPASSWORD = rd.getString("db.password");

        
            // Crear la conexión si no está inicializada o cerrada
            if (connection == null || connection.isClosed()) {
                synchronized (Singleton.class) {
                    if (connection == null || connection.isClosed()) {
                        connection = DriverManager.getConnection(dbURL, dbUSERNAME, dbPASSWORD);
                    }
                }
            }
        return connection;
    }

    // Método para cerrar la conexión, si es necesario
    public static void cerrarConexion() throws SQLException{
    
            if (connection != null && !connection.isClosed()) {
               // System.out.println("Cerrando conexión a la base de datos");
                connection.close();
            }        
    }
    public static void main(String[] args) {
        try {
            Singleton.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
