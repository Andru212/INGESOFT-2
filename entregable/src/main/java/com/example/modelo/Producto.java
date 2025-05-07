package com.example.modelo;

import java.util.ArrayList;
import java.util.List;

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
        notificar();
    }
    
    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
        notificar();
    }
    

    // MEMENTO
    public ProductoMemento guardarEstado() {
        return new ProductoMemento(nombre, precioBase);
    }

    public void restaurarEstado(ProductoMemento memento) {
        if (memento != null) {
            this.nombre = memento.getNombre();
            this.precioBase = memento.getPrecioBase();
        }
    }

        // Lista de observadores
    private final List<Observer> observadores = new ArrayList<>();

    public void agregarObservador(Observer o) {
        observadores.add(o);
    }

    public void quitarObservador(Observer o) {
        observadores.remove(o);
    }

    private void notificar() {
        for (Observer o : observadores) {
            o.actualizar(this);
        }
    }



}
