package edu.uniandes.hotelandes.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import edu.uniandes.hotelandes.entities.ConsumoEntity;

public interface ConsumoRepository extends MongoRepository<ConsumoEntity, String>{

    @Query(value = "{}", fields = "{ 'idServicio': 1 }")
    List<ObjectId> findAllServices();
}
