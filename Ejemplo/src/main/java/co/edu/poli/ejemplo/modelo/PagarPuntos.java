package co.edu.poli.ejemplo.modelo;

// Decorador: Pagar con puntos (descuento del 10%)
class PagarPuntos extends CarritoDecorador {
    public PagarPuntos(Carrito carrito) {
        super(carrito);
    }

    @Override
    public double calcularTotal() {
        return carrito.calcularTotal() * 0.9; // Aplica un 10% de descuento
    }
}
