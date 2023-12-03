package edu.uniandes.hotelandes.entities;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReservaEntity {

    private String idHabitacion;
    private String idClienteTitular;
    private Date fechaInicio;
    private Date fechaFin;
    private List<String> idsClientes;
    private Double costoTotal;

    public ReservaEntity() {
    }
    
}
