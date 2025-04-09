package co.edu.poli.ejemplo.modelo;

public class GestorInfoPersonal {
    public String mostrar() {
        return "Nombre: Juan Pérez\nDirección: Calle 123";
    }

    public void actualizar(String nombre, String direccion) {
        // Simular actualización
        System.out.println("Actualizado: " + nombre + ", " + direccion);
    }
}
