package edu.uniandes.hotelandes.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uniandes.hotelandes.entities.Bar;
import edu.uniandes.hotelandes.entities.HabitacionEntity;
import edu.uniandes.hotelandes.entities.TipoHabitacion;
import edu.uniandes.hotelandes.entities.TipoHabitacionEntity;
import edu.uniandes.hotelandes.repositories.HabitacionRepository;
import edu.uniandes.hotelandes.services.HabitacionService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/habitaciones")
public class HabitacionController {

    @Data
    public class myData {
        private String selectedOption;

    }

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private HabitacionService habitacionService;

    @GetMapping
    public String index(Model model) {
        ArrayList<TipoHabitacion> tipos = new ArrayList<TipoHabitacion>(Arrays.asList(TipoHabitacion.values()));
        model.addAttribute("tiposDisponibles", tipos);
        myData data = new myData();
        model.addAttribute("data", data);

        model.addAttribute("habitaciones", habitacionRepository.findAll());
        model.addAttribute("newHab", new HabitacionEntity());
        model.addAttribute("newTipo", new TipoHabitacionEntity());
        model.addAttribute("tipo", "");
        model.addAttribute("title", "Crear habitacion");
        return "habitaciones";
    }

    @PostMapping("/new/save")
    public String habitacionGuardar(@ModelAttribute HabitacionEntity newHab,
            @ModelAttribute TipoHabitacionEntity newTipo, @ModelAttribute myData data) {
        newTipo.setTipo(TipoHabitacion.valueOf(data.getSelectedOption()));
        newHab.setTipoHabitacion(newTipo);
        System.out.println(newHab);
        
        try {
            habitacionService.insertDocument(newHab);
        } catch (Exception e) {
            log.error("Se ha producido un error:", e.getMessage());
        }
        return "redirect:/habitaciones";
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(@PathVariable String id, Model model) {
        HabitacionEntity habitacion = habitacionRepository.findById(id).get();
        System.out.println("Editar habitacion");
        ArrayList<TipoHabitacion> tipos = new ArrayList<TipoHabitacion>(Arrays.asList(TipoHabitacion.values()));
        model.addAttribute("tiposDisponibles", tipos);
        myData data = new myData();
        data.setSelectedOption(habitacion.getTipoHabitacion().getTipo().toString());
        model.addAttribute("data", data);
        model.addAttribute("title", "Editar habitacion");
        model.addAttribute("tipo", habitacion.getTipoHabitacion());
        model.addAttribute("hab", habitacion);
        return "editHabitaciones";
    }

    @PostMapping(value = "/{id}/edit/save")
    public String saveEdit(@ModelAttribute HabitacionEntity newHab,
            @ModelAttribute TipoHabitacionEntity newTipo, @ModelAttribute myData data) {
        newTipo.setTipo(TipoHabitacion.valueOf(data.getSelectedOption()));
        newHab.setTipoHabitacion(newTipo);

        try {
            HabitacionEntity result = habitacionService.saveDocument(newHab);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e);
        }

        return "redirect:/habitaciones";
    }

    @GetMapping(value = "/{id}/delete")
    public String delete(@PathVariable String id) {
        habitacionRepository.deleteById(id);
        return "redirect:/habitaciones";
    }

    @GetMapping(value = "/delete")
    public String deleteAll() {
        habitacionRepository.deleteAll();
        return "redirect:/habitaciones";
    }

    @GetMapping(value = "/populate")
    public String populate() {
        this.habitacionService.insertDummies();
        return "redirect:/habitaciones";
    }
}
