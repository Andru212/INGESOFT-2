package co.edu.poli.ejemplo.modelo;

// 📌 4. Decorador: Pago con Puntos
public class PagarPuntos extends ProductoDecorador {
    private int puntosUsados;
    private double valorPunto;

    public PagarPuntos(Producto producto, int puntosUsados, double valorPunto) {
        super(producto);
        this.puntosUsados = puntosUsados;
        this.valorPunto = valorPunto;
    }

    @Override
    public String getDescripcion() {
        return productoDecorado.getDescripcion() + " (Pagado con " + puntosUsados + " puntos 🏆)";
    }

    @Override
    public double getPrecio() {
        double descuentoPuntos = puntosUsados * valorPunto;
        double nuevoPrecio = productoDecorado.getPrecio() - descuentoPuntos;
        return Math.max(nuevoPrecio, 0); 
    }
}
