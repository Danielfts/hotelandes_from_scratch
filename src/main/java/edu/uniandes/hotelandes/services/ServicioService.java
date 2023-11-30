package edu.uniandes.hotelandes.services;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import edu.uniandes.hotelandes.entities.Servicio;
import edu.uniandes.hotelandes.entities.ServicioEntity;
import edu.uniandes.hotelandes.repositories.ServicioRepository;

@Service
public class ServicioService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired 
    private ServicioRepository servicioRepository;

    public void create() {
    }

    public void update() {
    }

    public void deleteOne() {
    }

    public void deleteAll() {
    }

    public void get() {
    }

    public void getAll() {
    }

    public void populate() {
        ArrayList<ServicioEntity> servicios = new ArrayList<ServicioEntity>( );

        for (Servicio servicio : Servicio.values()) {
            ServicioEntity servicioEntity = new ServicioEntity(servicio.nombre, servicio.costo, servicio.descripcion);
            servicios.add(servicioEntity);
        }

        this.servicioRepository.insert(servicios);
        
    }
    
}
