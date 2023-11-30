package edu.uniandes.hotelandes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uniandes.hotelandes.controllers.HabitacionController.myData;
import edu.uniandes.hotelandes.entities.HabitacionEntity;
import edu.uniandes.hotelandes.entities.TipoHabitacion;
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

    @GetMapping(value = "/new")
    public String newTipoHabitacion(Model model) {
        model.addAttribute("newTipo", new TipoHabitacionEntity());
        model.addAttribute("title", "Crear tipo de habitacion");
        return "newTipoHabitacion";
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

    @GetMapping(value = "/{id}/delete")
    public String delete(@PathVariable String id) {
        habitacionRepository.deleteById(id);
        return "redirect:/tipos-habitaciones";
    }
    
    @GetMapping(value = "/{id}/edit")
    public String edit(@PathVariable String id) {
        
        return "editTiposHabitaciones";
    }

     @PostMapping(value = "/{id}/edit/save")
    public String saveEdit(@ModelAttribute String habitacionId,
            @ModelAttribute TipoHabitacionEntity newTipo, @ModelAttribute myData data) {
        

        return "redirect:/tipos-habitaciones";
    }
}
