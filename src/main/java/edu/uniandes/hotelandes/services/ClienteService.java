package edu.uniandes.hotelandes.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import edu.uniandes.hotelandes.entities.ClienteEntity;
import edu.uniandes.hotelandes.entities.CuentaEntity;
import edu.uniandes.hotelandes.repositories.CuentaRepository;

@Service
public class ClienteService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    CuentaService cuentaService;

    @Autowired
    private CuentaRepository cuentaRepository;

    public void insertDocumentWithoutId(ClienteEntity document) {
        mongoTemplate.insert(document);
    }

    public void insertDummies(){

        cuentaService.insertDummies();

        List<CuentaEntity> cuentas = cuentaRepository.findAll();
        List<String> cuentasIds = new ArrayList<String>();

        for (CuentaEntity cuenta : cuentas) {
            cuentasIds.add(cuenta.getId());
        }

        ArrayList<ClienteEntity> clientes = new ArrayList<ClienteEntity>(
            Arrays.asList(
                new ClienteEntity( "Juan", cuentasIds),
                new ClienteEntity( "Pedro", cuentasIds),
                new ClienteEntity( "Maria", cuentasIds),
                new ClienteEntity( "Jose", cuentasIds),
                new ClienteEntity( "Luis", cuentasIds),
                new ClienteEntity( "Andres", cuentasIds)
            )
        );

        mongoTemplate.insertAll(clientes);

    }
}
