package edu.uniandes.hotelandes.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import edu.uniandes.hotelandes.entities.ReservaEntity;

public interface ReservaRepository extends MongoRepository<ReservaEntity,String>{

    @Query(value = "{'idHabitacion':?0}")
    public List<ReservaEntity> findByHabitacion();

    @Aggregation(pipeline = {
        "{ '$limit' : 100 }",
        "{ '$sort' : { 'fechaInicio' : 1 } }"
      })
    public ArrayList<ReservaEntity> findTopResults();

    @Query(value = "{'numeroHabitacion':?0}", sort = "{'fechaInicio': 1}")
    public ArrayList<ReservaEntity> findByNumeroHabitacion(String numHabitacion);
    

    @Aggregation(pipeline = {
        "{ '$match' : { 'numeroHabitacion' : ?2 } }",
        // "{ '$match' : { '$and': [{'fechaInicio': {'$lte': new Date('2024-01-21')}},{'fechaFin': {'$gte': new Date('2024-01-02')}}] } }",
        "{ '$sort' : { 'fechaInicio' : 1 } }"
      })
    public ArrayList<ReservaEntity> findReservasSuperpuestas(LocalDateTime fechaInicio, LocalDateTime fechaFin, String numHabitacion);
}
