package edu.uniandes.hotelandes.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Document(collection = "clientes")
@ToString
@Data
public class ClienteEntity {
    @Id
    private String id;

    private String nombre;

    @Indexed(unique = true)
    private String email;
    
    @Indexed(unique = true)
    private String identificacion;

    private List<CuentaEntity> cuentas;

    public ClienteEntity() {
    }

    public ClienteEntity(String nombre, String email, String identificacion) {

        this.nombre = nombre;
        this.email = email;
        this.identificacion = identificacion;

    }

}
