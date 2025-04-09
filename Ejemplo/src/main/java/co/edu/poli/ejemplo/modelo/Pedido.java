package co.edu.poli.ejemplo.modelo;

import java.util.List;

/**
 * 
 */
public class Pedido {

    /**
     * Default constructor
     */
    public Pedido() {
    }

    /**
     * 
     */
    private String numero;

    /**
     * 
     */
    private String fecha;

    /**
     * 
     */
    private Cliente cliente;

    /**
     * 
     */
    private List <Producto> producto;

    public Pedido(Cliente cliente, String fecha, String numero, List<Producto> producto) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.numero = numero;
        this.producto = producto;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }

}