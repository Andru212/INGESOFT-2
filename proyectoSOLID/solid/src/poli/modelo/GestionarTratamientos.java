package poli.modelo;

// Interfaz para gestionar tratamientos
/* Interface Segregation Principle (ISP):

No obligamos a una clase a implementar interfaces que no necesita. 
Aquí separamos las interfaces de los tratamientos y las citas, 
porque no todas las clases deberían conocer los métodos de ambas.
 */
public interface GestionarTratamientos {
    void aplicarTratamiento(Mascota mascota, Tratamiento tratamiento);
}