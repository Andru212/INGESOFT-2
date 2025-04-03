package co.edu.poli.ejemplo.modelo;

public abstract class CarritoDecorador extends Carrito {
    protected Carrito carrito;

    public CarritoDecorador(Carrito carrito) {
        this.carrito = carrito;
    }

    @Override
    public double calcularTotal() {
        return carrito.calcularTotal();
    }
}
