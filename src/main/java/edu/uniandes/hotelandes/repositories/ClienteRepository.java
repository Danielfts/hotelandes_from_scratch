package edu.uniandes.hotelandes.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.uniandes.hotelandes.entities.ClienteEntity;

public interface ClienteRepository extends MongoRepository<ClienteEntity, String> {
    
}
