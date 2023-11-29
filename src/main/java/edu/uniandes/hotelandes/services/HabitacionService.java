package edu.uniandes.hotelandes.services;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import edu.uniandes.hotelandes.entities.HabitacionEntity;
import edu.uniandes.hotelandes.entities.TipoHabitacion;
import edu.uniandes.hotelandes.entities.TipoHabitacionEntity;

@Service
public class HabitacionService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertDocumentWithoutId(HabitacionEntity document) {
        mongoTemplate.insert(document);
    }

    public void insertDummies(){
        TipoHabitacionEntity tipoHabitacion = new TipoHabitacionEntity(100.00, 4, TipoHabitacion.SENCILLA,"Una habitacion mela" );
		

		ArrayList<HabitacionEntity> habitaciones = new ArrayList<HabitacionEntity>(
			Arrays.asList(
				new HabitacionEntity( "102", tipoHabitacion),
				new HabitacionEntity( "103", tipoHabitacion),
				new HabitacionEntity( "104", tipoHabitacion),
				new HabitacionEntity( "105", tipoHabitacion),
				new HabitacionEntity( "106", tipoHabitacion),
				new HabitacionEntity( "107", tipoHabitacion)
			)
		);

		mongoTemplate.insertAll(habitaciones);
    }
}
