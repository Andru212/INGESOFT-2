package com.example.modelo;

// DescuentoFijo.java
public class DescuentoFijo implements PriceStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice - 15; // Ejemplo: $15 de descuento
    }
}