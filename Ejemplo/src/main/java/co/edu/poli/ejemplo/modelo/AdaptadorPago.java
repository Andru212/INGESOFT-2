package co.edu.poli.ejemplo.modelo;

public class AdaptadorPago {
    private ProcesadorPago procesador;

    public AdaptadorPago(String metodo, String nombre, String correo) {
        if (metodo.equalsIgnoreCase("paypal")) {
            procesador = new PagoPaypal(nombre, correo);
        }  else {
            throw new IllegalArgumentException("Método de pago no soportado.");
        }
    }

    public String realizarPago() {
       return  procesador.procesarPago();
    }
}
