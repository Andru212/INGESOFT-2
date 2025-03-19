package co.edu.poli.ejemplo.modelo;

public interface ProveedorBuilder {
    void setNombre(String nombre);
    void setRazonSocial(String razonSocial);
    void setCertificacion(String certificacion);
    void setCalificacion(String calificacion);
    void setPoliticaEntrega(String politicaEntrega);
    Proveedor build();
}
