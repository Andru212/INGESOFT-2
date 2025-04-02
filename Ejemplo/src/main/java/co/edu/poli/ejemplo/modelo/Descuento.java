package co.edu.poli.ejemplo.modelo;

public class Descuento extends ProductoDecorador {
    private double porcentaje;

    public Descuento(Producto producto, double porcentaje) {
        super(producto);
        this.porcentaje = porcentaje;
    }

    @Override
    public String getDescripcion() {
        return productoDecorado.getDescripcion() + " con " + porcentaje + "% de descuento";
    }

    @Override
    public double getPrecio() {
        return productoDecorado.getPrecio() * (1 - porcentaje / 100);
    }
}
