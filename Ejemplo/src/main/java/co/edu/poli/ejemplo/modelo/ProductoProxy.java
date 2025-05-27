package co.edu.poli.ejemplo.modelo;

public class ProductoProxy implements IProducto {
    private ProductoReal productoReal;
    private int nivelUsuario;

    public ProductoProxy(ProductoReal productoReal, int nivelUsuario) {
        this.productoReal = productoReal;
        this.nivelUsuario = nivelUsuario;
    }

    @Override
    public String verDetalles() {
        if (nivelUsuario >= 2) {
            return productoReal.verDetalles();
        } else {
            return "Acceso denegado. Nivel de autorización insuficiente.";
        }
    }
}
