package co.edu.poli.ejemplo.modelo;

public class Certificacion implements ProveedorBuilder {
    private String nombre;
    private String razonSocial;
    private String certificacion;
    private String calificacion;
    private String politicaEntrega;

    @Override
    public void setNombre(String nombre) { this.nombre = nombre; }
    @Override
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
    @Override
    public void setCertificacion(String certificacion) { this.certificacion = certificacion; }
    @Override
    public void setCalificacion(String calificacion) { this.calificacion = calificacion; }
    @Override
    public void setPoliticaEntrega(String politicaEntrega) { this.politicaEntrega = politicaEntrega; }

    @Override
    public Proveedor build() {
        return new Proveedor(nombre, razonSocial, certificacion, calificacion, politicaEntrega);
    }
}
