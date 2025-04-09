package co.edu.poli.ejemplo.modelo;

// Decorador: Envío Gratis (descuento de 5 USD)
class EnvioGratis extends CarritoDecorador {
    public EnvioGratis(Carrito carrito) {
        super(carrito);
    }

    @Override
    public double calcularTotal() {
        return Math.max(0, carrito.calcularTotal() - 5); // Resta 5 USD
    }
}
