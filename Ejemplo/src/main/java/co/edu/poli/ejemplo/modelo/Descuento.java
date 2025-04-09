package co.edu.poli.ejemplo.modelo;

// Decorador: Descuento del 20%
class Descuento extends CarritoDecorador {
    public Descuento(Carrito carrito) {
        super(carrito);
    }

    @Override
    public double calcularTotal() {
        return carrito.calcularTotal() * 0.8; // Aplica un 20% de descuento
    }
}