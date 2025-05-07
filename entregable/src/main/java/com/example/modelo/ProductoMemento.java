package com.example.modelo;

public class ProductoMemento {
    private final String nombre;
    private final double precioBase;

    public ProductoMemento(String nombre, double precioBase) {
        this.nombre = nombre;
        this.precioBase = precioBase;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }
}

