package com.example.modelo;

// DescuentoFijo.java
public class DescuentoFijo implements PriceStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        if (basePrice <= 20) {
            // Si el precio es <= 20, no aplicamos descuento
            return basePrice;
        } else {
            return basePrice - 15;
        }
    }
}