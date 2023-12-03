package edu.uniandes.hotelandes.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import edu.uniandes.hotelandes.entities.Servicio;
import edu.uniandes.hotelandes.entities.ServicioEntity;
import edu.uniandes.hotelandes.errors.ErrorMessages;
import edu.uniandes.hotelandes.repositories.ServicioRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServicioService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired 
    private ServicioRepository servicioRepository;

    public ServicioEntity create(ServicioEntity servicioEntity) {
        ServicioEntity savedServicio;
        try {
            savedServicio = this.servicioRepository.insert(servicioEntity);
        } catch (DuplicateKeyException e) {
            log.error(ErrorMessages.DUPLICATEKEY.message);
            return servicioEntity;
        }
        return savedServicio;
    }

    public void update() {
    }

    public void deleteOne(String id) {
        this.servicioRepository.deleteById(id);
    }

    public void deleteAll() {
        this.servicioRepository.deleteAll();
    }

    public ServicioEntity getOne(String id) {
        return this.servicioRepository.findById(id).get();
    }

    public List<ServicioEntity> getAll() {

        List<ServicioEntity> servicios = this.mongoTemplate.findAll(ServicioEntity.class);
        return servicios;
    }

    public void populate() {
        ArrayList<ServicioEntity> servicios = new ArrayList<ServicioEntity>( );
        ServicioEntity servicioEntity ;
        for (Servicio servicio : Servicio.values()) {
            if (servicio.productos != null) {
                servicioEntity = new ServicioEntity(servicio.nombre, servicio.descripcion, servicio.productos);
            
            } else {
                servicioEntity = new ServicioEntity(servicio.nombre, servicio.costo, servicio.descripcion);

            }
            servicios.add(servicioEntity);
        }

        this.servicioRepository.insert(servicios);
        
    }
    
}
