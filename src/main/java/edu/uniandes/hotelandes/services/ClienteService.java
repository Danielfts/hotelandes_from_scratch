package edu.uniandes.hotelandes.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import edu.uniandes.hotelandes.controllers.HabitacionController;
import edu.uniandes.hotelandes.entities.ClienteEntity;
import edu.uniandes.hotelandes.entities.CuentaEntity;
import edu.uniandes.hotelandes.entities.HabitacionEntity;
import edu.uniandes.hotelandes.repositories.HabitacionRepository;
import java.util.Calendar;

@Service
public class ClienteService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private HabitacionRepository habitacionRepository;

    public void insertDocumentWithoutId(ClienteEntity document) {
        mongoTemplate.insert(document);
    }

    public void insertDummies(){
        List<HabitacionEntity> habitaciones = habitacionRepository.findAll();

        ArrayList<CuentaEntity> cuentas = new ArrayList<CuentaEntity>(
            Arrays.asList(
                new CuentaEntity( habitaciones.get(0), null, null),
                new CuentaEntity( habitaciones.get(1), null, null),
                new CuentaEntity( habitaciones.get(2), null, null),
                new CuentaEntity( habitaciones.get(3), null, null),
                new CuentaEntity( habitaciones.get(4), null, null)
            )
        );

        ArrayList<ClienteEntity> clientes = new ArrayList<ClienteEntity>(
            Arrays.asList(
                new ClienteEntity( "Juan", cuentas),
                new ClienteEntity( "Pedro", cuentas),
                new ClienteEntity( "Maria", cuentas),
                new ClienteEntity( "Jose", cuentas),
                new ClienteEntity( "Luis", cuentas),
                new ClienteEntity( "Andres", cuentas)
            )
        );

        mongoTemplate.insertAll(clientes);

    }
}
