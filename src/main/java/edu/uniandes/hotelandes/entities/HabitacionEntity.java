package edu.uniandes.hotelandes.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Document(collection="habitaciones")
@ToString
@Data
public class HabitacionEntity {
    /* 
     * Costo
     * Capacidad
     * Numero
     * Tipo
     * asdasdkjlf
     * 
     */
    
    private String id;
    private String numero;
    private TipoHabitacionEntity tipoHabitacion;

    public HabitacionEntity(){}

    public HabitacionEntity( String numero , TipoHabitacionEntity tipoHabitacion) {
        
        this.numero = numero;
        this.tipoHabitacion = tipoHabitacion;
    }

}
