package edu.uniandes.hotelandes.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import edu.uniandes.hotelandes.entities.ConsumoEntity;
import edu.uniandes.hotelandes.entities.CuentaEntity;
import edu.uniandes.hotelandes.entities.HabitacionEntity;
import edu.uniandes.hotelandes.repositories.ConsumoRepository;
import edu.uniandes.hotelandes.repositories.HabitacionRepository;

@Service
public class CuentaService {

    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private ConsumoRepository consumoRepository;

    public void insertDocumentWithoutId(CuentaEntity document) {
        mongoTemplate.insert(document);
    }

    public void insertDummies(){
        List<HabitacionEntity> habitaciones = habitacionRepository.findAll();
        List<ConsumoEntity> consumos = consumoRepository.findAll();

        ArrayList<CuentaEntity> cuentas = new ArrayList<CuentaEntity>(
            Arrays.asList(
                new CuentaEntity( habitaciones.get(0).getId(), new Date(), new Date(), consumos),
                new CuentaEntity( habitaciones.get(1).getId(), new Date(), new Date(), consumos)
            )
        );

        mongoTemplate.insertAll(cuentas);
    }
}
