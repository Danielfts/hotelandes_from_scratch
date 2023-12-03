package edu.uniandes.hotelandes.entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

// @Document(collection="cuentas")
@ToString
@Data
public class CuentaEntity {

    @Id
    private String id;
    
    private String idHabitacion;
    private Date checkIn;
    private Date checkOut;
    private List<ConsumoEntity> consumos;

    public CuentaEntity(){
    }

    public CuentaEntity( String idHabitacion , Date checkIn , Date checkOut, List<ConsumoEntity> consumos) {

        this.idHabitacion = idHabitacion;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.consumos = consumos;
    }
    
}
