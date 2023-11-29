package edu.uniandes.hotelandes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uniandes.hotelandes.repositories.HabitacionRepository;
import edu.uniandes.hotelandes.services.HabitacionService;

@Controller
@RequestMapping("/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private HabitacionService habitacionService;
    
    @GetMapping
    public String index(Model model){
        model.addAttribute("habitaciones", habitacionRepository.findAll());
        return "habitaciones";
    }

    @GetMapping(value = "/{id}/delete")
    public String delete( @PathVariable String id){
        habitacionRepository.deleteById(id);
        return "redirect:/habitaciones";
    }

    @GetMapping(value = "/delete")
    public String deleteAll(){
        habitacionRepository.deleteAll();
        return "redirect:/habitaciones";
    }
    
    @GetMapping(value = "/populate")
    public String populate(){
        this.habitacionService.insertDummies();
        return "redirect:/habitaciones";
    }
}
