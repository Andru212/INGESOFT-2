package co.edu.poli.ejemplo.modelo;

public class GestorPedidos {
    public String verHistorial() {
        return "Pedido #1 - Entregado\nPedido #2 - En camino";
    }

    public void realizar(String pedido) {
        System.out.println("Pedido realizado: " + pedido);
    }
}

