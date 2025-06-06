package co.edu.poli.ejemplo.modelo;

//esta es la fachada del patterns facade



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

    public String actualizarInfo(String nombre, String direccion) {
        return infoPersonal.actualizar(nombre, direccion);
    }

    public String verHistorial() {
        return pedidos.verHistorial();
    }

    public String realizarPedido(String pedido) {
        return pedidos.realizar(pedido);
    }

    public String verPagos() {
        return pagos.verPagos();
    }

    public String cambiarEstado(String metodo, boolean activo) {
        return pagos.cambiarEstado(metodo, activo);
    }
}

