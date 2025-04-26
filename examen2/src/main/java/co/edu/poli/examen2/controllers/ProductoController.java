package co.edu.poli.examen2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.examen2.model.Producto;
import co.edu.poli.examen2.services.ProductoServices;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/vi/productos")
public class ProductoController {

    @Autowired
    private ProductoServices productoServices;

    @GetMapping
    public ResponseEntity <List<Producto>> getAllProductos() {
        List<Producto> productos = productoServices.getAllProductos();
        return ResponseEntity.ok(productos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable String id) {
        Producto producto = productoServices.getProductoById(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }



    }

    @PostMapping
    public ResponseEntity <Producto> saveProducto(@RequestBody Producto producto) {
        Producto savedProducto = productoServices.saveProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProducto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable String id, @RequestBody Producto producto) {
        producto.setId(id);
        Producto updatedProducto = productoServices.updateProducto(producto);
        if (updatedProducto != null) {
            return ResponseEntity.ok(updatedProducto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> deleteProducto(@PathVariable String id) {
        Producto deletedProducto = productoServices.deleteProducto(id);
        if (deletedProducto != null) {
            return ResponseEntity.ok(deletedProducto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}