package co.edu.poli.ejemplo.modelo;

class Documentos extends Mercancia {
    public Documentos(TipoEnvio tipoEnvio) {
        super(tipoEnvio);
    }

    public void enviar() {
        tipoEnvio.enviar("Documentos importantes.");
    }
}