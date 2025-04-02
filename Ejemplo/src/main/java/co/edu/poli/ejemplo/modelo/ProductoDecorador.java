package co.edu.poli.ejemplo.modelo;

// 📌 1. Decorador Base que extiende Producto
public abstract class ProductoDecorador extends Producto {
    protected Producto productoDecorado;

    public ProductoDecorador(Producto producto) {
        super(producto.getId(), producto.getDescripcion(), producto.getPrecio());
        this.productoDecorado = producto;
    }

    @Override
    public String getDescripcion() {
        return productoDecorado.getDescripcion();
    }

    @Override
    public double getPrecio() {
        return productoDecorado.getPrecio();
    }
}
