package edu.uniandes.hotelandes.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Document(collection="reservas")
public class ReservaEntity {

    @Id
    private String id;

    private String idHabitacion;
    private String numeroHabitacion;
    private String idClienteTitular;
    private String nombreClienteTitular;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<String> idsClientes;
    private Double costoTotal;
    private ArrayList<Huesped> huespedes;

    public ReservaEntity() {
    }
    
}
