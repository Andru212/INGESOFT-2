package poli.modelo;

// Interfaz para gestionar las citas
/*
 * Interface Segregation Principle (ISP):

No obligamos a una clase a implementar interfaces que no necesita. 
Aquí separamos las interfaces de los tratamientos y las citas, 
porque no todas las clases deberían conocer los métodos de ambas.
 */
public interface GestionarCitas {
    void agendarCita(String fecha, Mascota mascota);
}