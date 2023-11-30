package edu.uniandes.hotelandes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uniandes.hotelandes.services.ServicioService;

@Controller
@RequestMapping("/servicios")
public class ServiciosController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public String index() {
        return "servicios";
    }

    @GetMapping("/delete")
    public String deleteAll(){
        this.servicioService.deleteAll();
        return "redirect:/servicios";
    }

    @GetMapping("/populate")
    public String populate(){
        this.servicioService.populate();    
        return "redirect:/servicios";
    }

    @GetMapping("/new")
    public String create(){

        return "newServicio";
    }

    
    
}
