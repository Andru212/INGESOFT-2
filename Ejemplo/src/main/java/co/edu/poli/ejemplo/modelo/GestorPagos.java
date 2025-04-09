package co.edu.poli.ejemplo.modelo;

public class GestorPagos {
    public String verFormas() {
        return "Tarjeta de crédito\nNequi\nEfectivo";
    }

    public void cambiarEstado(String metodo, boolean activo) {
        System.out.println((activo ? "Activado" : "Bloqueado") + " método: " + metodo);
    }
}

