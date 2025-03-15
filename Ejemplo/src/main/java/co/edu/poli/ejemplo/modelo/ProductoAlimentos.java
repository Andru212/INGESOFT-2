package co.edu.poli.ejemplo.modelo;

public class ProductoAlimentos extends Producto implements PrototipoProducto {

    private float aporteCalorico;

    public float getAporteCalorico() {
        return aporteCalorico;
    }

    public void setAporteCalorico(float aporteCalorico) {
        this.aporteCalorico = aporteCalorico;
    }

    public ProductoAlimentos(float aporteCalorico) {
        this.aporteCalorico = aporteCalorico;
    }

    public ProductoAlimentos(String id, String descripcion, double precio, float aporteCalorico) {
        super(id,descripcion, precio);
        this.aporteCalorico = aporteCalorico;
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
