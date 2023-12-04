package edu.uniandes.hotelandes.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.uniandes.hotelandes.entities.ClienteEntity;
import edu.uniandes.hotelandes.entities.HabitacionEntity;
import edu.uniandes.hotelandes.entities.Huesped;
import edu.uniandes.hotelandes.entities.ReservaEntity;
import edu.uniandes.hotelandes.entities.TipoHabitacion;
import edu.uniandes.hotelandes.repositories.ReservaRepository;
import edu.uniandes.hotelandes.services.HabitacionService;
import edu.uniandes.hotelandes.services.ReservaService;
import lombok.Data;

@Controller
@RequestMapping("/reservas")
public class ReservasController {

    @Data
    class TipoSelect{
        private String selectedOption;
    }

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HabitacionService habitacionService;

    @GetMapping
    public String index(Model model, @RequestParam(required = false) String numHabitacion) {
        if (numHabitacion == null){
            ArrayList<ReservaEntity> topReservas = this.reservaService.getTop();
            model.addAttribute("reservas", topReservas);
        }
        else {
            model.addAttribute("numHabitacion", numHabitacion);
            if (numHabitacion.equals("")){
                ArrayList<ReservaEntity> topReservas = this.reservaService.getTop();
                model.addAttribute("reservas", topReservas);
            }
            else {
                ArrayList<ReservaEntity> reservas = this.reservaService.findByNumHabitacion(numHabitacion);
                model.addAttribute("reservas", reservas);
            }
        }
        return "reservas";
    }

    @GetMapping("/populate")
    public String populate(){
        this.reservaService.populate();
        return "redirect:/reservas";
    }

    @GetMapping("/delete")
    public String delete(){
        this.reservaService.delete();
        return "redirect:/reservas";
    }

    @GetMapping("/new/login")
    public String createLogin(Model model){
        ClienteEntity clienteEntity = new ClienteEntity();
        model.addAttribute("cliente", clienteEntity);
        return "newReservaLogin";
    }

    @PostMapping("/new/login")
    public String createLoginUser(@ModelAttribute ClienteEntity clienteEntity){
        System.out.println(clienteEntity);
        ClienteEntity cliente = this.reservaService.findCliente(clienteEntity);
        if (cliente != null){
            return "redirect:/reservas/new/login/" + cliente.getId();
        }
        else {
            return "redirect:/reservas/new/login";
        }
    }

    @GetMapping("/new/login/{id}")
    public String createReserva(Model model, @PathVariable String id, @RequestParam(required = false) String tipo, @RequestParam(required = false) String desde, @RequestParam(required = false) String hasta){
        ArrayList<TipoHabitacion> tiposDisponibles = new ArrayList<TipoHabitacion>(Arrays.asList(TipoHabitacion.values()));
        TipoSelect tipoSelect = new TipoSelect();
        List<HabitacionEntity> habitaciones = new ArrayList<>();
        Boolean puedeReservar = false;
        if (tipo != null){
            if (tipo.equals("") || tipo.equals("TODAS")){
                tipoSelect.setSelectedOption(tipo);
                habitaciones =  this.habitacionService.findAllAvailable(desde, hasta);
            } else {
                tipoSelect.setSelectedOption(tipo);
                habitaciones =  this.habitacionService.findAllByTypeAvailable(tipo, desde, hasta);
            }
        } else if (tipo == null ){
            habitaciones =  this.habitacionService.findAllAvailable(desde, hasta);
        }

        if (tipo != null && desde != null && hasta != null){
            puedeReservar = true;
        }

        model.addAttribute("puedeReservar", puedeReservar);
        model.addAttribute("desde", desde);
        model.addAttribute("hasta", hasta);
        model.addAttribute("id", id);
        model.addAttribute("tiposDisponibles", tiposDisponibles);
        model.addAttribute("tipoSelect", tipoSelect);
        model.addAttribute("habitaciones", habitaciones);
        return "newReservaCrear";
    }
    
    @GetMapping("/{id}/huespedes")
    public String huespedes(@PathVariable String id, Model model){
        ReservaEntity reservaEntity = this.reservaRepository.findById(id).get();
        // ArrayList<ReservaEntity> overlaps = this.reservaService.findOverlapping(reservaEntity.getFechaInicio(), reservaEntity.getFechaFin(), reservaEntity.getNumeroHabitacion(), reservaEntity.getId());
        // System.out.println(overlaps);
        ArrayList<Huesped> huespedes = reservaEntity.getHuespedes();
        model.addAttribute("id", id);
        model.addAttribute("huespedes", huespedes);
        return "reservasHuespedes";
    }
}
