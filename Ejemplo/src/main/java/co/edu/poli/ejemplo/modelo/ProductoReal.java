package co.edu.poli.ejemplo.modelo;

public class ProductoReal implements IProducto {
    private String nombre;
    private float precio;

    public ProductoReal(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public String verDetalles() {
        return "Producto: " + nombre + "\nPrecio: $" + precio;
    }
}
