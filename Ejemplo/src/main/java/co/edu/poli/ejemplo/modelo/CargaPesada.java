package co.edu.poli.ejemplo.modelo;

class CargaPesada extends Mercancia {
    public CargaPesada(TipoEnvio tipoEnvio) {
        super(tipoEnvio);
    }

    public void enviar() {
        tipoEnvio.enviar("Carga pesada con equipo especializado.");
    }
}
