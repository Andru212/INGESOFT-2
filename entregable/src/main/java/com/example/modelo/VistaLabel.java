package com.example.modelo;

import javafx.scene.control.Label;

public class VistaLabel implements Observer {
    private Label label;

    public VistaLabel(Label label) {
        this.label = label;
    }

    @Override
    public void actualizar(Producto producto) {
        label.setText("Producto: " + producto.getNombre() + "\nPrecio base: " + producto.getPrecioBase());
    }
}
