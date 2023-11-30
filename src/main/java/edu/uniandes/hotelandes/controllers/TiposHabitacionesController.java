package edu.uniandes.hotelandes.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
import edu.uniandes.hotelandes.errors.ErrorMessages;
import edu.uniandes.hotelandes.repositories.HabitacionRepository;
import edu.uniandes.hotelandes.services.HabitacionService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/tipos-habitaciones")
@Slf4j
public class TiposHabitacionesController {
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
        List<TipoHabitacionEntity> data = habitacionService.getHabitacionTypes();
        // List<TipoHabitacionEntity> data = habitacionRepository.findAllTypes();
        // System.out.println(data);
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
    public String populate() {
        this.habitacionService.insertDummies();
        return "redirect:/tipos-habitaciones";
    }

    @GetMapping(value = "/delete")
    public String deleteAll() {
        habitacionRepository.deleteAll();
        return "redirect:/tipos-habitaciones";
    }

    @GetMapping(value = "/{id}/delete")
    public String delete(@PathVariable String id) {
        habitacionRepository.deleteById(id);
        return "redirect:/tipos-habitaciones";
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(@PathVariable String id, Model model) {
        TipoHabitacionEntity tipo = habitacionRepository.findById(id).get().getTipoHabitacion();
        tipo.setHabitacionId(id);
        model.addAttribute("tipo", tipo);
        ArrayList<TipoHabitacion> tiposDisponibles = new ArrayList<TipoHabitacion>(Arrays.asList(TipoHabitacion.values()));
        model.addAttribute("tiposDisponibles", tiposDisponibles);
        model.addAttribute("habId", id);
        myData currentTipo = new myData();
        currentTipo.setSelectedOption(tipo.getTipo().toString());
        model.addAttribute("currentTipo", currentTipo);
        return "editTiposHabitaciones";
    }

    @PostMapping(value = "/{id}/edit/save")
    public String saveEdit( @PathVariable String id,
            @ModelAttribute TipoHabitacionEntity tipo, @ModelAttribute myData currentTipo) {
        
        tipo.setTipo(TipoHabitacion.valueOf(currentTipo.getSelectedOption()));
        HabitacionEntity habitacion = habitacionRepository.findById(id).get();
        habitacion.setTipoHabitacion(tipo);
        // System.out.println(tipo);
        try {
            this.habitacionService.saveDocument(habitacion);
        } catch (DuplicateKeyException e) {
            log.error(ErrorMessages.DUPLICATEKEY.message);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/tipos-habitaciones";
    }
}
