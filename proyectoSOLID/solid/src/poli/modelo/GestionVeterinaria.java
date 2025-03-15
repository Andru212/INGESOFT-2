package poli.modelo;

// Clase de gestión de la veterinaria que depende de las interfaces

/*
 *  Dependency Inversion Principle (DIP):

 * Las clases de alto nivel no deben depender de clases de bajo nivel,
 * sino de abstracciones. 
 * Vamos a crear una clase de Gestión de Veterinaria que dependa de interfaces,
 * no de implementaciones específicas.
 * 
 */

public class GestionVeterinaria implements GestionarCitas, GestionarTratamientos {

    // Implementación de agendarCita de GestionarCitas
    @Override
    public void agendarCita(String fecha, Mascota mascota) {
        System.out.println("Cita agendada para " + mascota.getNombre() + " el " + fecha);
    }

    // Implementación de aplicarTratamiento de GestionarTratamientos
    @Override
    public void aplicarTratamiento(Mascota mascota, Tratamiento tratamiento) {
        tratamiento.aplicarTratamiento(mascota);
    }
}

