package edu.uniandes.hotelandes.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import edu.uniandes.hotelandes.entities.Producto;
import edu.uniandes.hotelandes.entities.ServicioEntity;

public interface ServicioRepository extends MongoRepository<ServicioEntity, String>{
    

    @Query("{_id: ?0}")
    @Update("{$pull: {'productos': {'nombre': ?1}}}")
    void eliminarProducto(String id, String nombreProducto);
}
