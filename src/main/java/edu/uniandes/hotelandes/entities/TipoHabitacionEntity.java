package edu.uniandes.hotelandes.entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TipoHabitacionEntity {

    private Double costo;
    private int capacidad;
    private TipoHabitacion tipo;
    private String descripcion;

    public TipoHabitacionEntity(){}

    public TipoHabitacionEntity(Double costo, int capacidad, TipoHabitacion tipo, String descripcion) {
        this.costo = costo;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }
    
}
