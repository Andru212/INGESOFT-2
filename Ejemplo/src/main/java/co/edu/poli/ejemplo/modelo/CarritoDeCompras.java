package co.edu.poli.ejemplo.modelo;

public class CarritoDeCompras {
    public static void main(String[] args) {
      
        Producto producto = new ProductoElectronico("001", "Smartphone", 600.000, 220);
        System.out.println(producto.getDescripcion() + " -> $" + producto.getPrecio());

        producto = new EnvioGratis(producto);
        System.out.println(producto.getDescripcion() + " -> $" + producto.getPrecio());

        
        producto = new Descuento(producto, 20);
        System.out.println(producto.getDescripcion() + " -> $" + producto.getPrecio());

    
        producto = new PagarPuntos(producto, 1000, 0.05);
        System.out.println(producto.getDescripcion() + " -> $" + producto.getPrecio());
    }
}
