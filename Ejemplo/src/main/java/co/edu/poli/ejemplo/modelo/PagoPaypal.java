package co.edu.poli.ejemplo.modelo;

class PagoPaypal implements ProcesadorPago {
    private String nombre;
    private String correo;

    public PagoPaypal(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    @Override
    public String procesarPago() {
        return "Pago realizado con Paypal por " + nombre + " (" + correo + ")";

    }
}
