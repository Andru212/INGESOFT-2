package com.example.modelo;

// Producto.java
public class Producto {
    private String nombre;
    private double precioBase;
    private PriceStrategy estrategia;

    public Producto(String nombre, double precioBase) {
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.estrategia = new SinDescuento(); // Por defecto sin descuento
    }

    public void setEstrategia(PriceStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public double getPrecioFinal() {
        return estrategia.calculatePrice(precioBase);
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }
}
