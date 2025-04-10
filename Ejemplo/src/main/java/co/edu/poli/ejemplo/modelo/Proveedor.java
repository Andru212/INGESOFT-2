package co.edu.poli.ejemplo.modelo;

public class Proveedor {
    private String nombre;
    private String direccion;

    public Proveedor(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }
}
