package edu.uniandes.hotelandes.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import edu.uniandes.hotelandes.entities.HabitacionEntity;
import edu.uniandes.hotelandes.entities.TipoHabitacionEntity;

public interface HabitacionRepository extends MongoRepository<HabitacionEntity, String> {
    
    @Query(value = "{}", fields = "{ 'tipoHabitacion': 1 }")
    List<TipoHabitacionEntity> findAllTypes();

    @Query(value = "{'tipoHabitacion.tipo':?0}")
    List<HabitacionEntity> findAllByType(String tipo);
    
}
