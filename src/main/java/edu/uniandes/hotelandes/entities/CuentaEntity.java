package edu.uniandes.hotelandes.entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CuentaEntity {

    private String idHabitacion;
    private List<Date> checkIn;
    private List<Date> checkOut;
    private List<ConsumoEntity> consumos;

    public CuentaEntity( String idHabitacion , List<Date> checkIn , List<Date> checkOut, List<ConsumoEntity> consumos) {
        
        this.idHabitacion = idHabitacion;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.consumos = consumos;
    }
    
}
