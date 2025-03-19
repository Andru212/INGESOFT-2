package co.edu.poli.ejemplo.modelo;

public class Proveedor {
    private String nombre;
    private String razonSocial;
    private String certificacion;
    private String calificacion;
    private String politicaEntrega;

    public Proveedor(String nombre, String razonSocial, String certificacion, String calificacion, String politicaEntrega) {
        this.nombre = nombre;
        this.razonSocial = razonSocial;
        this.certificacion = certificacion;
        this.calificacion = calificacion;
        this.politicaEntrega = politicaEntrega;
    }

    public String getNombre() { return nombre; }
    public String getRazonSocial() { return razonSocial; }
    public String getCertificacion() { return certificacion; }
    public String getCalificacion() { return calificacion; }
    public String getPoliticaEntrega() { return politicaEntrega; }

    @Override
    public String toString() {
        return "Proveedor: " + nombre + "\nRazón Social: " + razonSocial + "\nCertificación: " + certificacion + 
               "\nCalificación: " + calificacion + "\nPolítica de Entrega: " + politicaEntrega;
    }
}

