package co.edu.poli.ejemplo.modelo;

public class ProductoElectronico extends Producto implements PrototipoProducto {

    private float voltajeEntrada;

    public ProductoElectronico(float voltajeEntrada) {
        this.voltajeEntrada = voltajeEntrada;
    }

    public ProductoElectronico(String id, String descripcion, double precio, float voltajeEntrada) {
        super(id,descripcion, precio);
        this.voltajeEntrada = voltajeEntrada;
    }

    public float getVoltajeEntrada() {
        return voltajeEntrada;
    }

    public void setVoltajeEntrada(float voltajeEntrada) {
        this.voltajeEntrada = voltajeEntrada;
    }

    @Override
    public PrototipoProducto clonar() {
        try {
            return (PrototipoProducto) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("No se pudo clonar el producto", e);
        }
    }
}
