package com.example.modelo;
import java.util.Stack;

public class Historial {
    private final Stack<ProductoMemento> historial = new Stack<>();

    public void guardar(ProductoMemento memento) {
        historial.push(memento);
    }

    public ProductoMemento deshacer() {
        if (!historial.isEmpty()) {
            return historial.pop();
        }
        return null;
    }
}

