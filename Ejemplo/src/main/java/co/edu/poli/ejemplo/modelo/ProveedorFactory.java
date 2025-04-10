package co.edu.poli.ejemplo.modelo;

import java.util.HashMap;
import java.util.Map;

public class ProveedorFactory {
    private static Map<String, Proveedor> proveedores = new HashMap<>();

    public static Proveedor getProveedor(String nombre, String direccion) {
        if (!proveedores.containsKey(nombre)) {
            proveedores.put(nombre, new Proveedor(nombre, direccion));
        }
        return proveedores.get(nombre);
    }
}
