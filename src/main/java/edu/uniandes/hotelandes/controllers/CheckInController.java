package edu.uniandes.hotelandes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkin")
public class CheckInController {

    @GetMapping
    public String index() {
        return "checkin";
    }
    
}