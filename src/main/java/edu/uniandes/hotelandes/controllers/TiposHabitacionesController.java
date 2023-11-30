package edu.uniandes.hotelandes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uniandes.hotelandes.entities.HabitacionEntity;
import edu.uniandes.hotelandes.entities.TipoHabitacionEntity;
import edu.uniandes.hotelandes.repositories.HabitacionRepository;
import edu.uniandes.hotelandes.services.HabitacionService;

@Controller
@RequestMapping("/tipos-habitaciones")
public class TiposHabitacionesController {
    
    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private HabitacionService habitacionService;

    @GetMapping
    public String index(Model model) {
        List<TipoHabitacionEntity> data = habitacionService.getHabitacionTypes();
        // List<TipoHabitacionEntity> data = habitacionRepository.findAllTypes();
        System.out.println(data);
        model.addAttribute("tiposHabitaciones", data);
        return "tiposHabitaciones";
    }

    @GetMapping(value = "/populate")
    public String populate(){
        this.habitacionService.insertDummies();
        return "redirect:/tipos-habitaciones";
    }
    
    @GetMapping(value = "/delete")
    public String deleteAll(){
        habitacionRepository.deleteAll();
        return "redirect:/tipos-habitaciones";
    }
}
