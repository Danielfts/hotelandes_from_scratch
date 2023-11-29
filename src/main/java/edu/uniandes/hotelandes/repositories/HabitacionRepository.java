package edu.uniandes.hotelandes.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import edu.uniandes.hotelandes.entities.HabitacionEntity;

public interface HabitacionRepository extends MongoRepository<HabitacionEntity, String> {
    
    
}
