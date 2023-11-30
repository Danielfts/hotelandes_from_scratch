package edu.uniandes.hotelandes.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Document(collection="servicios")
@ToString
@Data
public class ServicioEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String nombre;

    private double costo;
    private String descripcion;

    private List<Producto> productos;    

    public ServicioEntity(){}

    public ServicioEntity(String nombre, double costo, String descripcion) {
        this.nombre = nombre;
        this.costo = costo;
        this.descripcion = descripcion;
    }
    
}
