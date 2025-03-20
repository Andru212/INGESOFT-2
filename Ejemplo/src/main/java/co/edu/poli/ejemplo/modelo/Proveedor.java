package co.edu.poli.ejemplo.modelo;

public class Proveedor {
    private String nombre;
    private String razonSocial;
    private Certificacion certificacion;
    private Evaluacion calificacion;
    private PoliticaEntrega politicaEntrega;

    // Constructor privado para forzar el uso del Builder
    private Proveedor(Builder builder) {
        this.nombre = builder.nombre;
        this.razonSocial = builder.razonSocial;
        this.certificacion = builder.certificacion;
        this.calificacion = builder.calificacion;
        this.politicaEntrega = builder.politicaEntrega;
    }

    // Métodos Getters
    public String getNombre() { return nombre; }
    public String getRazonSocial() { return razonSocial; }
    public Certificacion getCertificacion() { return certificacion; }
    public Evaluacion getCalificacion() { return calificacion; }
    public PoliticaEntrega getPoliticaEntrega() { return politicaEntrega; }

    @Override
    public String toString() {
        return "Proveedor: " + nombre + 
               "\nRazón Social: " + razonSocial + 
               "\nCertificación: " + (certificacion != null ? certificacion.getDetalle() : "No asignada") + 
               "\nCalificación: " + (calificacion != null ? calificacion.getDetalle() : "No asignada") + 
               "\nPolítica de Entrega: " + (politicaEntrega != null ? politicaEntrega.getDetalle() : "No asignada");
    }

    // ======================== PATRÓN BUILDER ========================
    public static class Builder {
        private String nombre;
        private String razonSocial;
        private Certificacion certificacion;
        private Evaluacion calificacion;
        private PoliticaEntrega politicaEntrega;

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setRazonSocial(String razonSocial) {
            this.razonSocial = razonSocial;
            return this;
        }

        public Builder setCertificacion(String detalle) {
            this.certificacion = new Certificacion(detalle);
            return this;
        }

        public Builder setCalificacion(String detalle) {
            this.calificacion = new Evaluacion(detalle);
            return this;
        }

        public Builder setPoliticaEntrega(String detalle) {
            this.politicaEntrega = new PoliticaEntrega(detalle);
            return this;
        }

        public Proveedor build() {
            return new Proveedor(this);
        }
    }

    // ======================== CLASES INTERNAS ========================

    public static class Certificacion {
        private String detalle;

        public Certificacion(String detalle) {
            this.detalle = detalle;
        }

        public String getDetalle() {
            return detalle;
        }
    }

    public static class Evaluacion {
        private String detalle;

        public Evaluacion(String detalle) {
            this.detalle = detalle;
        }

        public String getDetalle() {
            return detalle;
        }
    }

    public static class PoliticaEntrega {
        private String detalle;

        public PoliticaEntrega(String detalle) {
            this.detalle = detalle;
        }

        public String getDetalle() {
            return detalle;
        }
    }
}
