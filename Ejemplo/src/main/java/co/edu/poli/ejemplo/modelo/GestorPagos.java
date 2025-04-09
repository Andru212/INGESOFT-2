package co.edu.poli.ejemplo.modelo;

public class GestorPagos {
    public String verPagos() {
        return "Tarjeta de crédito\nNequi\nEfectivo";
    }

    public String cambiarEstado(String metodo, boolean activo) {
        return (activo ? "Activado" : "Bloqueado") + " método: " + metodo;
    }
}

