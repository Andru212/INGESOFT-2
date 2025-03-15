package co.edu.poli.ejemplo.modelo;

/**
 *
 */
public class Cliente {

    /**
     * Default constructor
     */
    public Cliente() {
    }

    /**
     *
     */
    private String id;

    /**
     *
     */
    private String nombre;

    public Cliente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente");
        sb.append(" id: ").append(id);
        sb.append(" nombre: ").append(nombre);
        return sb.toString();
    }

}
