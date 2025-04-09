package co.edu.poli.ejemplo.modelo;

public class GestorCliente {
    private GestorInfoPersonal infoPersonal;
    private GestorPedidos pedidos;
    private GestorPagos pagos;

    public GestorCliente() {
        this.infoPersonal = new GestorInfoPersonal();
        this.pedidos = new GestorPedidos();
        this.pagos = new GestorPagos();
    }

    public String mostrarInfo() {
        return infoPersonal.mostrar();
    }

    public void actualizarInfo(String nombre, String direccion) {
        infoPersonal.actualizar(nombre, direccion);
    }

    public String verHistorial() {
        return pedidos.verHistorial();
    }

    public void realizarPedido(String pedido) {
        pedidos.realizar(pedido);
    }

    public String verPagos() {
        return pagos.verPagos();
    }

    public void cambiarEstado(String metodo, boolean activo) {
        pagos.cambiarEstado(metodo, activo);
    }
}

