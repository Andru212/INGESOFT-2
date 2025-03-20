package co.edu.poli.ejemplo.modelo;

class PagoNequi implements ProcesadorPago {
    private String nombre;
    private String correo;

    public PagoNequi(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    @Override
    public String procesarPago() {
        return "Pago realizado con Nequi por " + nombre + " (" + correo + ")";
    }
}