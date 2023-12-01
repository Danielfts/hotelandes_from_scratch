package edu.uniandes.hotelandes.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uniandes.hotelandes.entities.ServicioEntity;
import edu.uniandes.hotelandes.services.ServicioService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/servicios")
@Slf4j
public class ServiciosController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public String index(Model model) {
        List<ServicioEntity> servicios = this.servicioService.getAll();
        model.addAttribute("servicios", servicios);
        return "servicios";
    }

    @GetMapping("/delete")
    public String deleteAll(){
        this.servicioService.deleteAll();
        return "redirect:/servicios";
    }
    @GetMapping("/{id}/delete")
    public String deleteOne(@PathVariable String id){
        this.servicioService.deleteOne(id);;
        return "redirect:/servicios";
    }

    @GetMapping("/populate")
    public String populate(){
        try {
            this.servicioService.populate();    
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/servicios";
    }

    @GetMapping("/new")
    public String create(){

        return "newServicio";
    }

    @GetMapping("/{id}/productos")
    public String indexProducts(Model model, @PathVariable String id){
        ServicioEntity servicio = this.servicioService.getOne(id);
        model.addAttribute("servicioId", id);
        model.addAttribute("servicio", servicio);
        return "productos";
    }

    
    
}
