package co.edu.poli.ejemplo.modelo;

class EnvioExpress implements TipoEnvio {
    public void enviar(String descripcion) {
        System.out.println("Envío Express: " + descripcion);
    }
}
