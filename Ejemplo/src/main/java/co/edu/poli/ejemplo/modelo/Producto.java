package co.edu.poli.ejemplo.modelo;

public class Producto {
    private String nombre;
    private Proveedor proveedor;

    public Producto(String nombre, Proveedor proveedor) {
        this.nombre = nombre;
        this.proveedor = proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }
}
