package co.edu.poli.ejemplo.modelo;

class PagoNequi implements ProcesadorPago {
    private String nombre;
    private String correo;

    public PagoNequi(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    @Override
    public void procesarPago() {
        System.out.println("Pago procesado con Nequi.");
        System.out.println("Usuario: " + nombre + " | Email: " + correo);
    }
}