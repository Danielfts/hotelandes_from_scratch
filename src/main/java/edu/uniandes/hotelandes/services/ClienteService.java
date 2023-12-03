package edu.uniandes.hotelandes.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import edu.uniandes.hotelandes.entities.ClienteEntity;
import edu.uniandes.hotelandes.entities.CuentaEntity;
import edu.uniandes.hotelandes.repositories.ClienteRepository;
import edu.uniandes.hotelandes.repositories.CuentaRepository;

@Service
public class ClienteService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    CuentaService cuentaService;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    Faker faker = new Faker();

    public void insertDocumentWithoutId(ClienteEntity document) {
        mongoTemplate.insert(document);
    }

    public void insertDummies(){

        // cuentaService.insertDummies();

        int cantUsuarios = 10;

        ArrayList<ClienteEntity> clientes = new ArrayList<ClienteEntity>();

        for (int i = 0; i < cantUsuarios; i++){
            String nombre = faker.name().fullName();
            String email = faker.internet().emailAddress();
            String identificacion = faker.idNumber().ssnValid();
            ClienteEntity clienteEntity = new ClienteEntity(nombre, email, identificacion);
            
            // Añadir cuenta dummy
            CuentaEntity cuentaEntity = new CuentaEntity();
            cuentaEntity.setIdHabitacion("10000");
            clienteEntity.setCuentas(new ArrayList<CuentaEntity>(Arrays.asList(cuentaEntity)));

            clientes.add(clienteEntity);
        }

        this.clienteRepository.insert(clientes);

    }
}
