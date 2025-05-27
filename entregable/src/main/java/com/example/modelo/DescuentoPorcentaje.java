package com.example.modelo;
// DescuentoPorcentaje.java
public class DescuentoPorcentaje implements PriceStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * 0.85; 
    }
}
