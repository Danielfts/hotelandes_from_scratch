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
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    public ClienteEntity getOneByIdentificacion(String identificacion){
        ClienteEntity result =  clienteRepository.findByIdentificacion(identificacion);

        return result;

    }

    public List<ClienteEntity> findAll(){
        return clienteRepository.findAll();
    }

    public void insertDummies(){

        // cuentaService.insertDummies();

        int cantUsuarios = 1000;

        ArrayList<ClienteEntity> clientes = new ArrayList<ClienteEntity>();

        ClienteEntity me = new ClienteEntity("Daniel Felipe", "d.trivino@uniandes.edu.co", "1001349793");
        clientes.add(me);
        for (int i = 0; i < cantUsuarios; i++){
            String nombre = faker.name().fullName();
            String email = faker.internet().emailAddress();
            String identificacion = faker.number().digits(10);
            ClienteEntity clienteEntity = new ClienteEntity(nombre, email, identificacion);
            
            // Añadir cuenta dummy
            CuentaEntity cuentaEntity = new CuentaEntity();
            clienteEntity.setCuentas(new ArrayList<CuentaEntity>(Arrays.asList(cuentaEntity)));

            clientes.add(clienteEntity);
        }

        for (ClienteEntity clienteEntity : clientes) {
            // System.out.println(clienteEntity);
            try {
                this.clienteRepository.insert(clienteEntity);
                
            } catch (Exception e) {
                log.error("Error al insertar cliente: " + clienteEntity);
                e.printStackTrace();
            }
        }

    }
}
