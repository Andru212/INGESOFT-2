package co.edu.poli.ejemplo.modelo;

public class CarritoConcreto extends Carrito {
    private double total;

    public CarritoConcreto(double total) {
        this.total = total;
    }

    @Override
    public double calcularTotal() { // Debe ser "public" para coincidir con la superclase
        return total;
    }
}
