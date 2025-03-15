package co.edu.poli.ejemplo.modelo;

public class FactoryAlimento implements ProductoFactory {

    @Override
    public Producto crearProducto() {
        return new ProductoAlimentos(0);
    }
}
