package poli.modelo;

// Interfaz Tratamiento: Abierta para extensión
/*
 * Open/Closed Principle (OCP):

Las clases deben estar abiertas para extensión, 
pero cerradas para modificación. 
Si queremos agregar nuevos tipos de tratamientos o servicios,
 podemos extender el código sin modificar lo existente.
 */
public interface Tratamiento {
    void aplicarTratamiento(Mascota mascota);
}

