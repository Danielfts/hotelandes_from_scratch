package edu.uniandes.hotelandes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uniandes.hotelandes.entities.ClienteEntity;
import edu.uniandes.hotelandes.errors.ErrorMessages;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReservaService {

    @Autowired
    private ClienteService clienteService;

    public void createReserva(ClienteEntity clienteEntity){
        try {
            ClienteEntity foundClienteEntity =  this.clienteService.getOneByIdentificacion(clienteEntity.getIdentificacion());
            System.out.println(foundClienteEntity);
            
        } catch (Exception e) {
            log.error(ErrorMessages.ENTITYNOTFOUND.message);
        }
        
    }
    
}
