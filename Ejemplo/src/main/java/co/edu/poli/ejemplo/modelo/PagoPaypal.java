package co.edu.poli.ejemplo.modelo;

class PagoPaypal implements ProcesadorPago {
    private String nombre;
    private String correo;

    public PagoPaypal(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    @Override
    public void procesarPago() {
        System.out.println("Pago procesado con PayPal.");
        System.out.println(" Usuario: " + nombre + " | Email: " + correo);
    }
}
