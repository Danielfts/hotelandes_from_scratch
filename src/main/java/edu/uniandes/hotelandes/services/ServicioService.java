package edu.uniandes.hotelandes.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import edu.uniandes.hotelandes.entities.Producto;
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

    private void validateServicio(ServicioEntity servicioEntity) throws IllegalArgumentException {
        if (servicioEntity.getCosto() != null && servicioEntity.getCosto() < 0) {
            throw new IllegalArgumentException(ErrorMessages.NEGATIVECOST.message);
        }
    }

    public ServicioEntity create(ServicioEntity servicioEntity) {
        try {
            validateServicio(servicioEntity);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            return servicioEntity;
        }
        ServicioEntity insertedServicio;
        try {
            insertedServicio = this.servicioRepository.insert(servicioEntity);
        } catch (DuplicateKeyException e) {
            log.error(ErrorMessages.DUPLICATEKEY.message);
            return servicioEntity;
        } 
        return insertedServicio;
    }

    public ServicioEntity update(ServicioEntity servicioEntity) {
        try {
            validateServicio(servicioEntity);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            return servicioEntity;
        }
        ServicioEntity savedServicio;
        try {
            savedServicio = this.servicioRepository.save(servicioEntity);
        } catch (DuplicateKeyException e) {
            log.error(ErrorMessages.DUPLICATEKEY.message);
            return servicioEntity;
        } 
        
        return savedServicio;
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

    public void addProduct(String id, Producto producto){
        ServicioEntity servicio = this.servicioRepository.findById(id).get();
        servicio.getProductos().add(producto);
        this.servicioRepository.save(servicio);
    }

    public void deleteProduct(String id, String producto){
        this.servicioRepository.eliminarProducto(id, producto);
    }
    
}
