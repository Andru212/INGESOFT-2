package co.edu.poli.ejemplo.modelo;

public class BridgePattern {
        public static void main(String[] args) {
            // Creamos tipos de envío
            TipoEnvio envioNacional = new EnvioNacional();
            TipoEnvio envioInternacional = new EnvioInternacional();
            TipoEnvio envioExpress = new EnvioExpress();
    
            // Probamos diferentes combinaciones
            Mercancia cargaFragil = new CargaFragil(envioNacional);
            Mercancia documentos = new Documentos(envioInternacional);
            Mercancia cargaPesada = new CargaPesada(envioNacional);
            Mercancia cargaFragil2 = new CargaFragil(envioExpress);
    
            // Enviamos la mercancía
            cargaFragil.enviar();
            documentos.enviar();
            cargaPesada.enviar();
            cargaFragil2.enviar();
        }
}
