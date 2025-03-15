package poli.modelo;

// Clase Mascota: Maneja los datos de la mascota.
/*
 * Cada clase tendrá una única responsabilidad. 
 * Por ejemplo, Mascota solo maneja los datos de la mascota,
 *  Cita solo maneja la programación de la cita, etc.
 * 
 */
public class Mascota {
    private String nombre;
    private String especie;
    private String raza;

    public Mascota(String nombre, String especie, String raza) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaza() {
        return raza;
    }
}
