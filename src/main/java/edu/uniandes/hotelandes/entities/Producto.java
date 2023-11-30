package edu.uniandes.hotelandes.entities;

import lombok.Data;

@Data
public class Producto {

    private String nombre;
    private double costo;
    
    public Producto() {
    }

    public Producto(String nombre, double costo) {
        this.nombre = nombre;
        this.costo = costo;
    }
}
