package co.edu.poli.ejemplo.modelo;

class EnvioInternacional implements TipoEnvio {
    public void enviar(String descripcion) {
        System.out.println("Envío Internacional: " + descripcion);
    }
}
