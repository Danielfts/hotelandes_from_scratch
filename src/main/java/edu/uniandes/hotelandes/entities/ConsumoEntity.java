package edu.uniandes.hotelandes.entities;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ConsumoEntity {

    private Date fecha;
    private String idServicio;

    public ConsumoEntity( Date fecha , String idServicio) {
        
        this.fecha = fecha;
        this.idServicio = idServicio;
    }
    
}
