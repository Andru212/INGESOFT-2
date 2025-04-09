package co.edu.poli.ejemplo.modelo;

public class GestorInfoPersonal {
    public String mostrar() {
        return "Nombre: Juan Pérez\nDirección: Calle 123";
    }

    public String actualizar(String nombre, String direccion) {
        return "Actualizado: " + nombre + ", " + direccion;
    }
}
