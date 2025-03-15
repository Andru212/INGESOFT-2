package poli.modelo;

// Clase Veterinario: Realiza el tratamiento a las mascotas

/*
 * Liskov Substitution Principle (LSP):
*  Podemos sustituir cualquier clase Tratamiento por sus subclases (como Vacuna o Cirugia)
*  sin que afecte el comportamiento esperado.
 * 
 */
public class Veterinario {
    /*
     * De este modo, si necesitamos agregar otro tipo de tratamiento,
     *  como un Desparacitado, podemos simplemente crear otra clase que implemente Tratamiento,
     *  y el Veterinario podrá usarla sin problemas.
     */
    public void aplicarTratamiento(Mascota mascota, Tratamiento tratamiento) {
        tratamiento.aplicarTratamiento(mascota);
    }
}
