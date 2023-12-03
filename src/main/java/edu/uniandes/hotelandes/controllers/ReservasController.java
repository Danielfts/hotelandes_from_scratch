package edu.uniandes.hotelandes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uniandes.hotelandes.entities.ClienteEntity;
import edu.uniandes.hotelandes.entities.ReservaEntity;
import edu.uniandes.hotelandes.services.ReservaService;

@Controller
@RequestMapping("/reservas")
public class ReservasController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public String index() {
        return "reservas";
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
        this.reservaService.createReserva(clienteEntity);
        return "redirect:/reservas/new/login";
    }
    
}
