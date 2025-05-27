package com.example.modelo;

// SinDescuento.java
public class SinDescuento implements PriceStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice;
    }
}