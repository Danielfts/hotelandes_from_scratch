package edu.uniandes.hotelandes.entities;

import java.sql.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CuentaEntity {

    private HabitacionEntity habitacion;
    private List<Date> checkIn;
    private List<Date> checkOut;

    public CuentaEntity( HabitacionEntity habitacion , List<Date> checkIn , List<Date> checkOut) {
        
        this.habitacion = habitacion;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
    
}
