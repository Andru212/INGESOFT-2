package co.edu.poli.ejemplo.modelo;

abstract class Mercancia {
    protected TipoEnvio tipoEnvio;

    public Mercancia(TipoEnvio tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public abstract void enviar();
}