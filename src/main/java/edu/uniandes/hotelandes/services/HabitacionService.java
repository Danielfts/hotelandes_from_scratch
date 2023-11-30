package edu.uniandes.hotelandes.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.MongoBulkWriteException;

import edu.uniandes.hotelandes.entities.HabitacionEntity;
import edu.uniandes.hotelandes.entities.TipoHabitacion;
import edu.uniandes.hotelandes.entities.TipoHabitacionEntity;
import edu.uniandes.hotelandes.errors.ErrorMessages;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HabitacionService {
	@Autowired
	private MongoTemplate mongoTemplate;

	public boolean validateDocument(HabitacionEntity document) throws IllegalArgumentException{
		String error;
		if (document.getTipoHabitacion() == null){
			document.setTipoHabitacion(new TipoHabitacionEntity(
				100.0, TipoHabitacion.SENCILLA.capacidad, TipoHabitacion.SENCILLA, "Una habitacion básica pero bonita"
			));
		}
		if (document.getNumero() == null || document.getNumero().isEmpty()) {
			error = ErrorMessages.EMPTYFIELD.message;
			log.error(error);
			throw new IllegalArgumentException(error);
			
		}
		if (Integer.parseInt(document.getNumero()) < 0 || document.getTipoHabitacion().getCapacidad() < 0  || document.getTipoHabitacion().getCosto() < 0)
		{
			error = ErrorMessages.INVALIDFIELD.message;
			log.error(error);
			throw new IllegalArgumentException(error);
			
		}
		return true;
	}

	public void insertDocument(HabitacionEntity document) {
		
		boolean isvalid = validateDocument(document);
		if (isvalid){
			if (document.getTipoHabitacion() == null){
				document.setTipoHabitacion(new TipoHabitacionEntity(
					100.0, TipoHabitacion.SENCILLA.capacidad, TipoHabitacion.SENCILLA, "Una habitacion básica pero bonita"
				));
			}
			try {
				mongoTemplate.insert(document);
			} catch (DuplicateKeyException e) {
				log.error(ErrorMessages.DUPLICATEKEY.message);
			}
			catch (Exception e) {
				log.error("Error inserting document", e.getClass().getCanonicalName());
			}
		}
	}

	public HabitacionEntity saveDocument(HabitacionEntity document)throws DuplicateKeyException,Exception{
		try {
			boolean isvalid = validateDocument(document);
			if (document.getTipoHabitacion() == null){
				document.setTipoHabitacion(new TipoHabitacionEntity(
					100.0, TipoHabitacion.SENCILLA.capacidad, TipoHabitacion.SENCILLA, "Una habitacion básica pero bonita"
				));
			}
			try {
				return mongoTemplate.save(document);
			} catch (DuplicateKeyException e) {
				log.error(ErrorMessages.DUPLICATEKEY.message);
				throw e;
			}
			catch (Exception e) {
				log.error("Error inserting document", e.getClass().getCanonicalName());
				throw e;
			}
		} catch (Exception e) {
			throw e;
		}
		
	}

	public List<TipoHabitacionEntity> getHabitacionTypes() {
		Query query = new Query();
		query.fields().include("tipoHabitacion");

		List<HabitacionEntity> result = mongoTemplate.find(query, HabitacionEntity.class,
				"habitaciones");
		ArrayList<TipoHabitacionEntity> tiposHabitacion = new ArrayList<TipoHabitacionEntity>();
		for(HabitacionEntity habitacion : result) {
			TipoHabitacionEntity tipoHabitacion = habitacion.getTipoHabitacion();
			tipoHabitacion.setHabitacionId(habitacion.getId());
			tiposHabitacion.add(tipoHabitacion);
		}
		return tiposHabitacion;
	}

	public void insertDummies() {

		ArrayList<TipoHabitacionEntity> tiposHabitacion = new ArrayList<TipoHabitacionEntity>(Arrays.asList(
				new TipoHabitacionEntity(100.00, TipoHabitacion.SENCILLA.capacidad, TipoHabitacion.SENCILLA,
						"Una habitacion básica pero bonita"),
				new TipoHabitacionEntity(200.00, TipoHabitacion.DOBLE.capacidad, TipoHabitacion.DOBLE,
						"Esta habitación sencilla ofrece una cama cómoda, un escritorio de trabajo y un baño privado. Ideal para viajeros solitarios."),
				new TipoHabitacionEntity(300.00, TipoHabitacion.TRIPLE.capacidad, TipoHabitacion.TRIPLE,
						"La habitación doble cuenta con dos camas individuales, un espacio de trabajo y un baño privado. Perfecta para compañeros de viaje."),
				new TipoHabitacionEntity(400.00, TipoHabitacion.MULTIPLE.capacidad, TipoHabitacion.MULTIPLE,
						"Nuestra habitación triple ofrece tres camas individuales, un amplio espacio de trabajo y un baño privado. Ideal para grupos pequeños."),
				new TipoHabitacionEntity(500.00, TipoHabitacion.TRIPLE.capacidad, TipoHabitacion.TRIPLE,
						"Nuestra habitación triple ofrece tres camas individuales, un amplio espacio de trabajo y un baño privado. Ideal para grupos pequeños."),
				new TipoHabitacionEntity(600.00, TipoHabitacion.EJECUTIVA.capacidad, TipoHabitacion.EJECUTIVA,
						"La suite ejecutiva ofrece una cama king size, un área de estar separada, un escritorio de trabajo y un baño de lujo. Ideal para aquellos que buscan lujo y comodidad."),
				new TipoHabitacionEntity(700.00, TipoHabitacion.SEMISUITE.capacidad, TipoHabitacion.SEMISUITE,
						"Una habitacion mela")));

		ArrayList<HabitacionEntity> habitaciones = new ArrayList<HabitacionEntity>(
				Arrays.asList(
						new HabitacionEntity("102", tiposHabitacion.get(0)),
						new HabitacionEntity("103", tiposHabitacion.get(1)),
						new HabitacionEntity("104", tiposHabitacion.get(2)),
						new HabitacionEntity("105", tiposHabitacion.get(3)),
						new HabitacionEntity("106", tiposHabitacion.get(4)),
						new HabitacionEntity("107", tiposHabitacion.get(5))));

		try {
			mongoTemplate.insertAll(habitaciones);
		} catch (DuplicateKeyException e) {
			log.error(ErrorMessages.DUPLICATEKEY.message);
		}
		catch (Exception e) {
			log.error("Error inserting dummies", e.getClass().getCanonicalName());
		}
	}
}
