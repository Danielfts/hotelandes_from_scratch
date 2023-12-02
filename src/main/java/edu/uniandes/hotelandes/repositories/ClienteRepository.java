package edu.uniandes.hotelandes.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import edu.uniandes.hotelandes.entities.ClienteEntity;

public interface ClienteRepository extends MongoRepository<ClienteEntity, String> {


}


