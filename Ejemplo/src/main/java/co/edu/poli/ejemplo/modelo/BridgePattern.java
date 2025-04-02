package co.edu.poli.ejemplo.modelo;

public class BridgePattern {
        public static void main(String[] args) {
            // Creamos tipos de envío
            TipoEnvio envioNacional = new EnvioNacional();
            TipoEnvio envioInternacional = new EnvioInternacional();
    
            // Probamos diferentes combinaciones
            Mercancia cargaFragil = new CargaFragil(envioNacional);
            Mercancia documentos = new Documentos(envioInternacional);
            Mercancia cargaPesada = new CargaPesada(envioNacional);
    
            // Enviamos la mercancía
            cargaFragil.enviar();
            documentos.enviar();
            cargaPesada.enviar();
        }
}
