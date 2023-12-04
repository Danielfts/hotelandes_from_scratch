package edu.uniandes.hotelandes.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uniandes.hotelandes.entities.ClienteEntity;
import edu.uniandes.hotelandes.entities.HabitacionEntity;
import edu.uniandes.hotelandes.entities.Huesped;
import edu.uniandes.hotelandes.entities.ReservaEntity;
import edu.uniandes.hotelandes.errors.ErrorMessages;
import edu.uniandes.hotelandes.repositories.ReservaRepository;
import lombok.extern.slf4j.Slf4j;
import com.github.javafaker.Faker;

@Slf4j
@Service
public class ReservaService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private HabitacionService habitacionService;

    @Autowired
    private ReservaRepository reservaRepository;

    Faker faker = new Faker();

    private Random rand = new Random();

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public void populate() {
        ArrayList<ClienteEntity> clientes = (ArrayList<ClienteEntity>) clienteService.findAll();
        // System.out.println(clientes);
        ArrayList<HabitacionEntity> habitaciones = (ArrayList<HabitacionEntity>) habitacionService.findAll();
        // System.out.println(habitaciones);

        ArrayList<ReservaEntity> reservas = new ArrayList<>();

        for (ClienteEntity clienteEntity : clientes) {
            HabitacionEntity habitacion = habitaciones.get(rand.nextInt(habitaciones.size()));

            ArrayList<Huesped> huespedes = new ArrayList<>();
            Integer capacidad = habitacion.getTipoHabitacion().getCapacidad();
            for (int i = 0; i < capacidad; i++) {
                huespedes.add(new Huesped(faker.name().fullName(), faker.number().digits(10)));
            }

            LocalDate today = LocalDate.now();
            LocalDate monthFromToday = today.plusDays(30);
            LocalDate yeaerFromToday = monthFromToday.plusYears(1);

            ReservaEntity reservaEntity = new ReservaEntity();
            reservaEntity.setIdClienteTitular(clienteEntity.getId());
            reservaEntity.setNombreClienteTitular(clienteEntity.getNombre() );
            reservaEntity.setIdHabitacion(habitacion.getId());
            reservaEntity.setNumeroHabitacion(habitacion.getNumero());
            reservaEntity.setFechaInicio(convertToLocalDateViaInstant(faker.date()
                    .between(convertToDateViaSqlDate(monthFromToday), convertToDateViaSqlDate(yeaerFromToday))));
            reservaEntity.setFechaFin(reservaEntity.getFechaInicio().plusDays(rand.nextInt(2, 21)));
            reservaEntity.setHuespedes(huespedes);

            reservas.add(reservaEntity);
        }

        try {
            reservaRepository.insert(reservas);

        } catch (Exception e) {
            log.error("Error cargando reservas", e);
        }

    }

    public void delete(){
        this.reservaRepository.deleteAll();
    }

    public ArrayList<ReservaEntity> getTop(){
        return this.reservaRepository.findTopResults();
    }

    public ArrayList<ReservaEntity> findByNumHabitacion(String numHabitacion){
        return this.reservaRepository.findByNumeroHabitacion(numHabitacion);
    }

    public ClienteEntity findCliente(ClienteEntity clienteEntity) {
        try {
            ClienteEntity foundClienteEntity = this.clienteService
                    .getOneByIdentificacion(clienteEntity.getIdentificacion());
            System.out.println(foundClienteEntity);
            return foundClienteEntity;

        } catch (Exception e) {
            log.error(ErrorMessages.ENTITYNOTFOUND.message);
            return null;
        }

    }

}
