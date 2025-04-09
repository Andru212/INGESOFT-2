package co.edu.poli.ejemplo.modelo;

class CargaFragil extends Mercancia {
    public CargaFragil(TipoEnvio tipoEnvio) {
        super(tipoEnvio);
    }

    public void enviar() {
        tipoEnvio.enviar("Carga Frágil manejada con cuidado.");
    }
}
