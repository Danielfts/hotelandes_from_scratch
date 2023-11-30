package edu.uniandes.hotelandes.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Document(collection="clientes")
@ToString
@Data
public class ClienteEntity {

    private String id;
    private String nombre;
    private List<CuentaEntity> cuentas;

    public ClienteEntity( String nombre , List<CuentaEntity> cuentas) {
        
        this.nombre = nombre;
        this.cuentas = cuentas;
    }
    
}
