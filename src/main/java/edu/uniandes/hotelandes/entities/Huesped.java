package edu.uniandes.hotelandes.entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Huesped {

    private String nombre;
    private String identificacion;

    public Huesped() {
    }

    public Huesped(String nombre, String identificacion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
    }
}
