package org.example.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Producto {
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;

    @Override
    public String toString() {
        return nombre + " (" + categoria + ", $" + precio + ", stock " + stock + ")";
    }


















}
