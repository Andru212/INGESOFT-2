package co.edu.poli.ejemplo.modelo;

public class FactoryElectronico implements ProductoFactory {

    @Override
    public Producto crearProducto() {
        return new ProductoElectronico(0);
    }

}
