package co.edu.poli.ejemplo.modelo;

class EnvioNacional implements TipoEnvio {
    public void enviar(String descripcion) {
        System.out.println("Envío Nacional: " + descripcion);
    }
}
