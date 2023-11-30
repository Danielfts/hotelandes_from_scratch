package edu.uniandes.hotelandes.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.uniandes.hotelandes.entities.ServicioEntity;

public interface ServicioRepository extends MongoRepository<ServicioEntity, String>{
    
}
