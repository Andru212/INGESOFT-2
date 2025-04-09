package co.edu.poli.ejemplo.modelo;

public class Main {
    public static void main(String[] args) {
        Carrito carrito = new CarritoConcreto(100); // Carrito con total de 100 USD
        System.out.println("Total original: " + carrito.calcularTotal());

        carrito = new PagarPuntos(carrito);
        System.out.println("Con PagarPuntos: " + carrito.calcularTotal());

        carrito = new EnvioGratis(carrito);
        System.out.println("Con Envío Gratis: " + carrito.calcularTotal());

        carrito = new Descuento(carrito);
        System.out.println("Con Descuento: " + carrito.calcularTotal());
    }
}
