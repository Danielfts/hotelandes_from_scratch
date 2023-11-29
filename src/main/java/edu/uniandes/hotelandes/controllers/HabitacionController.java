package edu.uniandes.hotelandes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/habitaciones")
public class HabitacionController {
    
    @GetMapping
    public String index(){
        return "habitaciones";
    }
}
