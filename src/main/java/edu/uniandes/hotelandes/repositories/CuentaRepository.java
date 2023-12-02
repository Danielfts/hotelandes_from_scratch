package edu.uniandes.hotelandes.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import edu.uniandes.hotelandes.entities.CuentaEntity;

public interface CuentaRepository extends MongoRepository<CuentaEntity, String>{
        
        @Query(value = "{}", fields = "{ 'id': 1 }")
        List<String> findAllIds();
}
