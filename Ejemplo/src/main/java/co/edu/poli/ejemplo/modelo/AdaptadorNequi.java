package co.edu.poli.ejemplo.modelo;

public class AdaptadorNequi {
    private ProcesadorPago procesador;

    public AdaptadorNequi(String metodo, String nombre, String correo) {
     if (metodo.equalsIgnoreCase("nequi")) {
            procesador = new PagoNequi(nombre, correo);
        } else {
            throw new IllegalArgumentException("Método de pago no soportado.");
        }
    }

    public String realizarPago() {
        return procesador.procesarPago();
    }
}
