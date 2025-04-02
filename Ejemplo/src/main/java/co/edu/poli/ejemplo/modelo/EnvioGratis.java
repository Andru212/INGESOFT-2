package co.edu.poli.ejemplo.modelo;

// 📌 2. Decorador: Envío Gratis
public class EnvioGratis extends ProductoDecorador {
    public EnvioGratis(Producto producto) {
        super(producto);
    }

    @Override
    public String getDescripcion() {
        return productoDecorado.getDescripcion() + " con Envío Gratis";
    }

    @Override
    public double getPrecio() {
        return productoDecorado.getPrecio();
    }
}
